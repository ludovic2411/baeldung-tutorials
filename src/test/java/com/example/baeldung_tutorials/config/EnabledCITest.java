package com.example.baeldung_tutorials.config;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE})
@EnabledIfEnvironmentVariable(named = "CI_TEST", matches = "true",
        disabledReason = "test needs to work only in a CI/CD envronment"
                           )
public @interface EnabledCITest {
}
