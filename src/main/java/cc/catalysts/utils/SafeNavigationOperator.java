package cc.catalysts.utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation of a <a href="https://en.wikipedia.org/wiki/Safe_navigation_operator">Safe Navigation Operator</a>
 * behavior for use in Java. <br>
 * It imitates the behavior or "real" Safe Navigation Operators like in Groovy, Objective-C, C# by catching a
 * {@link NullPointerException} around the get result method of a {@link Supplier} function. <br>
 * <br>
 * Provides methods for evaluating an expression to
 * <li>an {@link Optional} or
 * <li>directly to value or <code>null</code>.</li><br>
 * <b>Usages:</b></br>
 * </br>
 * Groovy - def foo = outer?.nested?.inner?.foo</br>
 * Java - String foo = resolveToOptional(() -> outer.getNested().getInner().getFoo()).orElse(null);</br>
 * Java - String foo = resolve(() -> outer.getNested().getInner().getFoo());</br>
 * </br>
 * Groovy - if (outer?.nested?.inner?.foo != null) {....}</br>
 * Java - if (resolveToOptional(() -> outer.getNested().getInner().getFoo()).orElse(null) != null) {....};</br>
 * Java - if (resolve(() -> outer.getNested().getInner().getFoo()) != null) {....};</br>
 * </br>
 * Check if a value is present <br>
 * Java - if (isValuePresent(() -> outer.getNested().getInner().getFoo())) {....};</br>
 * </br>
 * Get value if present or a default value if it is not <br>
 * Java - String foo = resolveToOptional(() -> outer.getNested().getInner().getFoo()).orElse('Fallback Value');</br>
 * </br>
 *
 * @author Stefan Starke (stefan.starke@catalysts.cc)
 * @author Nikolaus Trixner (nikolaus.trixner@catalysts.cc)
 */
public final class SafeNavigationOperator {
    /**
     * Hidden constructor
     */
    private SafeNavigationOperator() {
// prevent instantiation
    }

    /**
     * Evaluate the supplier and return the result as an {@link Optional}<T>
     *
     * @param <T>      the type of the result given by this supplier
     * @param supplier {@link Supplier} to evaluate
     * @return Optional<T> the evaluated value, may be an empty Optional<T>.
     */
    public static <T> Optional<T> resolveToOptional(final Supplier<T> supplier) {
        try {
            final T result = supplier.get();
            return Optional.ofNullable(result);
        } catch (final NullPointerException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    /**
     * Evaluate the supplier and return the result value
     *
     * @param <T>      the type of the result given by this supplier
     * @param supplier {@link Supplier} to evaluate
     * @return <T> the evaluated value, may be null.
     */
    public static <T> T resolve(final Supplier<T> supplier) {
        return resolveToOptional(supplier).orElse(null);
    }

    /**
     * Evaluate the supplier and return the result. If the result is null, an empty list is returned
     *
     * @param <T>      the type of the result given by this supplier, must be an implementation of {@link List}
     * @param supplier {@link Supplier} to evaluate
     * @return <T> the evaluated value, may be an empty List.
     */
    public static <T> List<T> resolveToList(final Supplier<List<T>> supplier) {
        return resolveToOptional(supplier).orElse(Collections.emptyList());
    }

    /**
     * Checks if a given value is present.
     *
     * @param <T>      the type of the result given by this supplier
     * @param supplier {@link Supplier} to evaluate
     * @return boolean True if the value is present, false otherwise
     */
    public static <T> boolean isValuePresent(final Supplier<T> supplier) {
        return resolveToOptional(supplier).isPresent();
    }
}

