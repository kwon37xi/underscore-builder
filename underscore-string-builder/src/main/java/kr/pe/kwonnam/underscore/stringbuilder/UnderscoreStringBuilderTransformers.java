package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.UnderscoreFormatTransformer;

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
    public static UnderscoreFormatTransformer format(Object... args) {
        return new UnderscoreFormatTransformer(args);
    }

    /**
     * {@link String#format(String, Object...)} with {@link Locale}.
     * @param locale locale
     * @param args format arguments
     * @return formatted string
     */
    public static UnderscoreFormatTransformer format(Locale locale, Object... args) {
        return new UnderscoreFormatTransformer(locale, args);
    }
}
