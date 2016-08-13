package bytetroll.woa2016.devcli.annot;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Inherited
public @interface woDevCLI {
}
