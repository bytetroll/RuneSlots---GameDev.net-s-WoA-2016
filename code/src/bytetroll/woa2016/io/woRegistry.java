package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Based off code found on Stackoverflow:
// http://stackoverflow.com/questions/190629/what-is-the-easiest-way-to-parse-an-ini-file-in-java

public class woRegistry {
    public static void Load(String path) {
        try(BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            String section = null;

            while((line = bReader.readLine()) != null) {
                Matcher match = secPattern.matcher(line);

                if(match.matches()) {
                    section = match.group(1).trim();
                } else if(section != null) {
                    match = kvPattern.matcher(line);

                    if(match.matches()) {
                        final String key = match.group(1).trim();
                        final String value = match.group(2).trim();

                        Map<String, String> kvPair = entries.get(section);
                        if(kvPair == null) {
                            entries.put(section, (kvPair = new HashMap<>()));
                        }

                        kvPair.put(key, value);
                    }
                }
            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public static String FindString(String section, String key, String defaultValue) {
        final Map<String, String> entry = entries.get(section);

        if(entry == null) {
            return defaultValue;
        }

        return entry.get(key);
    }

    public static int FindInt(String section, String key, int defaultValue) {
        final Map<String, String> entry = entries.get(section);

        if(entry == null) {
            return defaultValue;
        }

        return Integer.parseInt(entry.get(key));
    }

    public static float FindFloat(String section, String key, float defaultValue) {
        final Map<String, String> entry = entries.get(section);

        if(entry == null) {
            return defaultValue;
        }

        return Float.parseFloat(entry.get(key));
    }

    public static double FindDouble(String section, String key, double defaultValue) {
        final Map<String, String> entry = entries.get(section);

        if(entry == null) {
            return defaultValue;
        }

        return Double.parseDouble(entry.get(key));
    }

    private static Pattern secPattern = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
    private static Pattern kvPattern = Pattern.compile("\\s*([^=]*)=(.*)");

    private static Map<String, Map<String, String>> entries = new HashMap<>();
}
