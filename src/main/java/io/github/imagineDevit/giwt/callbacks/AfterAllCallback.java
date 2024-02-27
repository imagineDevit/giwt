package io.github.imagineDevit.giwt.callbacks;

/**
 * An interface that represents a callback method to be executed after all tests have run.
 */
@FunctionalInterface
public non-sealed interface AfterAllCallback extends Callback {
    void afterAll();
}
