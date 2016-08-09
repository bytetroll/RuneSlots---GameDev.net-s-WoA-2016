package bytetroll.woa2016.runtime.reflect.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.FIELD,
    ElementType.LOCAL_VARIABLE,
    ElementType.METHOD
})
public @interface woReadOnly {
    // This has to be a damn string as we can't use type parameters or Objects.
    public String value() default "";
}