package bytetroll.woa2016.idoms;

import bytetroll.woa2016.runtime.woRuntime;

public class woReadOnly<T> {
    public woReadOnly(T value) {
        this.value = value;
    }

    public woReadOnly(T value, boolean verifyBeforeGet) {
        this.value = value;
        this.verifyBeforeGet = verifyBeforeGet;
    }

    public T Get() {
        try {
            if(verifyBeforeGet) {
                if(value.equals(null)) {
                    throw new Exception("woProperty instance attempted to access a null property value with " +
                            "verifyBeforeGet=true passed to the constructor");
                }
            }
        } catch(Exception except){
            woRuntime.HandleException(except);
        }

        return value;
    }

    private final T value;
    private boolean verifyBeforeGet = false;
}
