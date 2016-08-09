package bytetroll.woa2016.idoms;

import bytetroll.woa2016.runtime.woRuntime;

public class woProperty<T> {
    public woProperty(T value) {
        this.value = value;
    }

    public woProperty(T value, boolean verifyBeforeGet) {
        this.value = value;
        this.verifyBeforeGet = verifyBeforeGet;
    }

    //public woProperty(T[] initList) {
    //    ProcessInitializerList(initList);
    //}

    //public woProperty(T[] initList,boolean verifyBeforeGet) {
    //    this.verifyBeforeGet = verifyBeforeGet;
    //    ProcessInitializerList(initList);
    //}

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

    public void Set(T value) {
        this.value = value;
    }

    //private void ProcessInitializerList(T[] initList) {
    //    assert initList.length > 0 : "woProperty instance received an initializer list with more than one " +
    //            "element";
    //
    //    value = initList[0];
    //}


    private T value = null;
    private boolean verifyBeforeGet = false;
}
