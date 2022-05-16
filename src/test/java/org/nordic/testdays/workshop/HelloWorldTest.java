package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class HelloWorldTest {
    private final HelloWorld helloWorld = new HelloWorld();
    private final String name = "Your name";

    @Test
    void send_helloWorld_ownName() {
        String result = helloWorld.send("Your name");
        assertThat(result, is(name));
    }
}