package com.example.baeldung_tutorials.config;

import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.springframework.test.context.junit.jupiter.DisabledIf;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE})
@DisabledIfEnvironmentVariable(named = "CI", matches = "true",
        disabledReason = "Test only need to works locally"
)
public @interface EnabledLocalTest {
}
