package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.transformers.UnderscoreStringFormatTransformer;

import kr.pe.kwonnam.underscore.stringbuilder.transformers.*;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts;

import java.util.Locale;

/**
 * {@link UnderscoreStringBuilder}'s default {@link UnderscoreTransformer}s.
 */
public abstract class UnderscoreStringBuilderTransformers {
    /**
     * {@link String#format(String, Object...)} without {@link Locale}.
     *
     * @param args format arguments
     * @return formatted string
     */
    public static UnderscoreStringFormatTransformer format(Object... args) {
        return new UnderscoreStringFormatTransformer(args);
    }

    /**
     * {@link String#format(String, Object...)} with {@link Locale}.
     *
     * @param locale locale
     * @param args   format arguments
     * @return formatted string
     */
    public static UnderscoreStringFormatTransformer format(Locale locale, Object... args) {
        return new UnderscoreStringFormatTransformer(locale, args);
    }

    public static UnderscoreDateFormatTransformer dateFormat(String dateFormat) {
        return new UnderscoreDateFormatTransformer(dateFormat);
    }

    public static UnderscoreJoinTransformer join(Iterable<?> iterable) {
        return join(iterable, null);
    }

    public static UnderscoreJoinTransformer join(Iterable<?> iterable, String nullValue) {
        return new UnderscoreJoinTransformer(iterable, nullValue);
    }

    public static UnderscoreJoinTransformer join(Object[] objectArray) {
        return join(objectArray, null);
    }

    public static UnderscoreJoinTransformer join(Object[] objectArray, String nullValue) {
        return new UnderscoreJoinTransformer(objectArray, nullValue);
    }

    public static UnderscoreWrapTransformer wrap(String left, String right) {
        return new UnderscoreWrapTransformer(left, right);
    }

    public static UnderscoreMultiplyTransformer multiply(int factor) {
        return multiply(factor, null);
    }

    public static UnderscoreMultiplyTransformer multiply(int factor, String separator) {
        return new UnderscoreMultiplyTransformer(factor, separator);
    }

    public static UnderscoreTrimTransformer trim() {
        return trim(null);
    }

    /**
     * Trims the appendee with {@link TrimOpts} options.
     * <br>
     * <pre>
     * import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
     *
     * underscoreStringBuilder.__("    \t\t\r\nAND Hello World!, \r \t",
     *     trim(trimOpts().prefix("[").prefixOverrides("OR ", "AND ").suffix("]").suffixOverrides(".", ",")))
     * </pre>
     *
     * @param trimOps trim options
     * @return trim transformer
     */
    public static UnderscoreTrimTransformer trim(TrimOpts trimOps) {
        return new UnderscoreTrimTransformer(trimOps);
    }
}
