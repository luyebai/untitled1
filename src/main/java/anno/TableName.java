package anno;

import java.lang.annotation.*;

/**
 * 表名的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {
    String value();
}
