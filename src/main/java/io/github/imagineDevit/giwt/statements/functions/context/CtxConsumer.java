package io.github.imagineDevit.giwt.statements.functions.context;

import io.github.imagineDevit.giwt.TestCaseContext;

import java.util.function.Consumer;

/**
 * TextCase context consumer.
 *
 * @param <R> type of the result value
 * @param <C> type of the context
 * @author Henri Joel SEDJAME
 * @version 0.1.2
 * @see TestCaseContext
 */
public interface CtxConsumer<R, C extends TestCaseContext<?, R>> extends Consumer<C> {
}