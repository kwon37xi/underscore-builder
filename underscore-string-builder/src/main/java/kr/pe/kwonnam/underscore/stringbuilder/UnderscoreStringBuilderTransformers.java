package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.transformers.UnderscoreStringFormatTransformer;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;
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

    public static UnderscoreJoinIterableTransformer join(String seperator) {
        return join(seperator, null);
    }

    public static UnderscoreJoinIterableTransformer join(String seperator, String nullValue) {
        return new UnderscoreJoinIterableTransformer(seperator, nullValue);
    }

    public static UnderscoreJoinArrayTransformer joinArray(String separator) {
        return joinArray(separator, null);
    }

    public static UnderscoreJoinArrayTransformer joinArray(String separator, String nullValue) {
        return new UnderscoreJoinArrayTransformer(separator, nullValue);
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

    public static UnderscoreTrimTransformer trim(TrimOpts trimOps) {
        return new UnderscoreTrimTransformer(trimOps);
    }
}
