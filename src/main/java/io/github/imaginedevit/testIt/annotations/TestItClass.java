package io.github.imaginedevit.testIt.annotations;

import org.junit.platform.commons.annotation.Testable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Testable
@Documented
public @interface TestItClass {
}
