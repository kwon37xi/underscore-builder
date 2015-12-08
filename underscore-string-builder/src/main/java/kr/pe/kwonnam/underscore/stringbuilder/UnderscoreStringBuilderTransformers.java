package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.*;

import java.util.Locale;

/**
 * {@link UnderscoreStringBuilder}'s default {@link UnderscoreTransformer}s.
 */
public class UnderscoreStringBuilderTransformers {
    private UnderscoreStringBuilderTransformers() {
        // disable object creation.
    }

    /**
     * {@link String#format(String, Object...)} without {@link Locale}.
     * @param args format arguments
     * @return formatted string
     */
    public static UnderscoreStringFormatTransformer format(Object... args) {
        return new UnderscoreStringFormatTransformer(args);
    }

    /**
     * {@link String#format(String, Object...)} with {@link Locale}.
     * @param locale locale
     * @param args format arguments
     * @return formatted string
     */
    public static UnderscoreStringFormatTransformer format(Locale locale, Object... args) {
        return new UnderscoreStringFormatTransformer(locale, args);
    }

    public static UnderscoreDateFormatTransformer dateFormat(String dateFormat) {
        return null;
    }

    public static UnderscoreStringJoinTransformer join(Iterable<?> args, String seperator) {
        return null;
    }

    public static UnderscoreWrapTransformer wrap(String left, String right) {
        return null;
    }

    public static UnderscoreMultiplyTransformer multiply(int factor) {
        return null;
    }

    public static UnderscoreMultiplyTransformer multiply(int factor, String separator) {
        return null;
    }

    public static UnderscoreTrimTransformer trim() {
        return null;
    }

    public static UnderscoreTrimTransformer trim(TrimOpts trimOps) {
        return null;
    }

    public static UnderscoreLeftPadTransformer padLeft(int numberOfChars) {
        return null;
    }

    public static UnderscoreRightPadTransformer padRight(int numberOfChars) {
        return null;
    }

    public static UnderscoreDefaultsTransformer defaults(Object defaultValue) {
        return null;
    }
}
