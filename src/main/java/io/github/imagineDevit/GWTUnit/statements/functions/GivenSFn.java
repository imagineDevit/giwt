package io.github.imagineDevit.GWTUnit.statements.functions;

import java.util.function.Supplier;


@FunctionalInterface
public interface GivenSFn<T> extends Supplier<T> {}
