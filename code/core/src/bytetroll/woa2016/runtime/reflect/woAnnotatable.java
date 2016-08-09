package bytetroll.woa2016.runtime.reflect;

import bytetroll.woa2016.runtime.reflect.annot.woReadOnly;

import java.lang.annotation.Annotation;

public class woAnnotatable {
    public Annotation FindAnnotation(Class<?> checkingClass, Class<?> annoClass) {
        for(Annotation anno : checkingClass.getDeclaredAnnotations()) {

            // This is a shitty way of having to do this...
            if(anno instanceof woReadOnly) {
                return anno;
            }
        }

        return null;
    }
}
