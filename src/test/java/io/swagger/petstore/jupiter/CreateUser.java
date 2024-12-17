package io.swagger.petstore.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(CreateUserExtension.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateUser {
    String userName() default "";
    String firstName() default "";
    String lastName() default "";
    String email() default "";
    String password() default "";
    String phone() default "";
    boolean cleanUp() default true;
}
