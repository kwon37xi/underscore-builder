package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.util.Locale;

public class UnderscoreFormatTransformer implements UnderscoreTransformer<String> {
    private Locale locale;

    private Object[] args;

    public UnderscoreFormatTransformer(Object... args) {
        this(null, args);
    }

    public UnderscoreFormatTransformer(Locale locale, Object... args) {
        this.locale = locale;
        this.args = args;
    }

    @Override
    public String transform(String appended) {
        if (locale != null) {
            return String.format(locale, appended, args);
        }
        return String.format(appended, args);
    }
}
