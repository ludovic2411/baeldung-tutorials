package com.example.baeldung_tutorials.config;

import org.springframework.test.context.junit.jupiter.DisabledIf;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE})
@DisabledIf(
        expression = "#{environment.acceptsProfiles('ci')}",
        reason = "Test only need to works locally",
        loadContext = true
)
public @interface EnabledLocalTest {
}
