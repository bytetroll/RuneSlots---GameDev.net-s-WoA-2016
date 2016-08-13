package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.devcli.steam.woLogStream;
import bytetroll.woa2016.devcli.steam.woLogStreamCli;
import bytetroll.woa2016.devcli.steam.woLogStreamFile;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.runtime.woRuntime;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;

import java.util.ArrayList;
import java.util.List;

public abstract class woConsole implements woConsoleInterface, woDestructible {
    protected final woProperty<woExecutor> Executor = new woProperty<>(null);
    protected final woProperty<Array<woLogStream>> StreamHandles = new woProperty<>(new Array<>());
    protected final woProperty<Boolean> AlsoLogToFile = new woProperty<>(false);

    public woConsole(woExecutor exec, boolean alsoLogToFile) {
        Executor.Set(exec);
        Executor.Get().TargetConsole.Set(this);

        StreamHandles.Get().add(new woLogStreamCli());

        if(alsoLogToFile) {
            StreamHandles.Get().add(new woLogStreamFile());
        }

        AlsoLogToFile.Set(alsoLogToFile);
    }

    //----------------------------------------------------------------------------
    // BEGIN CONSOLE INTERFACE
    //----------------------------------------------------------------------------
    @Override
    public void Draw() {
    }

    @Override
    public boolean MouseInConsole(woVector2 mouseCoords) {
        return false;
    }

    @Override
    public void Execute(String cmdStr) {
        if(Disabled.Get()) {
            return;
        }

        String[] cmdComs = cmdStr.split(" ");
        String cmdName = cmdComs[0];
        List<String> passedArgs = new ArrayList<>();

        int index = 0;
        do {
            if(index == 1) {
                continue;
            }

            passedArgs.add(cmdComs[index]);
            ++index;
        } while(index != passedArgs.size());

        final Class<? extends woExecutor> cls = Executor.Get().getClass();
        final Method[] methods = ClassReflection.getMethods(cls);
        Array<Integer> declared = new Array<>();

        for(int i = 0; i < methods.length; i++) {
            final Method method = methods[i];

            if(method.getName().equalsIgnoreCase(cmdName) && woExecutorSandbox.ExecuteAllowed(this, method)) {
                declared.add(i);
            }
        }

        if(declared.size <= 0) {
            Write(new woConsolePacket(woMessageEncoder.Encode(woMessageType.INFO, String.format("Command %s not found", cmdName))));
            return;
        }

        index = 0;
        do {
            final Method callback = methods[declared.get(index)];
            final Class<?>[] callbackParamList = callback.getParameterTypes();

            if(passedArgs.size() == callbackParamList.length) {
                try {
                    Object[] expectedArgs = null;

                    try {
                        if(passedArgs != null) {
                            expectedArgs = new Object[passedArgs.size()];

                            for(int i = 0; i < callbackParamList.length; i++) {
                                final Class<?> callbackParam = callbackParamList[i];
                                final String value = passedArgs.get(index);

                                if(callback.equals(String.class)) {
                                    expectedArgs[i] = value;
                                } else if(callback.equals(Boolean.class) || callback.equals(boolean.class)) {
                                    expectedArgs[i] = Boolean.parseBoolean(value);
                                } else if(callback.equals(Byte.class) || callback.equals(byte.class)) {
                                    expectedArgs[i] = Byte.parseByte(value);
                                } else if(callback.equals(Short.class) || callback.equals(short.class)) {
                                    expectedArgs[i] = Short.parseShort(value);
                                } else if(callback.equals(Integer.class) || callback.equals(int.class)) {
                                    expectedArgs[i] = Integer.parseInt(value);
                                } else if(callback.equals(Long.class) || callback.equals(long.class)) {
                                    expectedArgs[i] = Long.parseLong(value);
                                } else if(callback.equals(Float.class) || callback.equals(float.class)) {
                                    expectedArgs[i] = Float.parseFloat(value);
                                } else if(callback.equals(Double.class) || callback.equals(double.class)) {
                                    expectedArgs[i] = Double.parseDouble(value);
                                }
                            }
                        }
                    } catch(Exception except) {
                        continue;
                    }

                    callback.setAccessible(true);
                    callback.invoke(Executor.Get(), expectedArgs);
                } catch(Exception except) {
                    woRuntime.HandleException(except);
                }
            }
            ++index;
        } while(index != declared.size);

        Write(new woConsolePacket(woMessageEncoder.Encode(woMessageType.INFO, String.format("Command %s was not passed the required arguments", cmdName))));
    }

    @Override
    public void Write(woConsolePacket packet) {
        for(woLogStream stream : StreamHandles.Get()) {
            stream.Write(packet);
        }
    }

    @Override
    public void PrintAllCVars() {
        final Method[] methods = ClassReflection.getDeclaredMethods(Executor.Get().getClass());

        for(int index1 = 0; index1 < methods.length; index1++) {
            final Method method = methods[index1];

            if(method.isPublic() && woExecutorSandbox.DisplayAllowed(this, method)) {
                String cmd = method.getName() + " : ";
                final Class<?>[] params = method.getParameterTypes();

                for(int index2 = 0; index2 < params.length; index2++) {
                    cmd += params[index2].getSimpleName();

                    if(index2 < (params.length - 1)) {
                        cmd += ", ";
                    }
                }

                Write(new woConsolePacket(woMessageEncoder.Encode(woMessageType.INFO,  cmd)));
            }
        }
    }

    @Override
    public void Clear() {
    }

    @Override
    public void Refresh() {
    }

    @Override
    public void FlushInputStream() {
    }
    //----------------------------------------------------------------------------
    // BEGIN CONSOLE INTERFACE
    //----------------------------------------------------------------------------

    //----------------------------------------------------------------------------
    // BEGIN DESTRUCTIBLE INTERFACE
    //----------------------------------------------------------------------------
    @Override
    public void Destruct() {
    }
    //----------------------------------------------------------------------------
    // BEGIN DESTRUCTIBLE INTERFACE
    //----------------------------------------------------------------------------
}
