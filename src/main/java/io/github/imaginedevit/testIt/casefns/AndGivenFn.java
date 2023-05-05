package io.github.imaginedevit.testIt.casefns;

import java.util.Optional;
import java.util.function.Function;


@FunctionalInterface
public interface AndGivenFn<T> extends Function<Optional<T>,T> {}
