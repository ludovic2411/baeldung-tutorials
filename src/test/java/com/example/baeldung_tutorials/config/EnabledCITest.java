package com.example.baeldung_tutorials.config;

import org.springframework.test.context.junit.jupiter.EnabledIf;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE})
@EnabledIf(
        expression = "#{environment.acceptsProfiles('ci')}",
        reason = "test needs to work only in a CI/CD envronment",
        loadContext = false
)
public @interface EnabledCITest {
}
