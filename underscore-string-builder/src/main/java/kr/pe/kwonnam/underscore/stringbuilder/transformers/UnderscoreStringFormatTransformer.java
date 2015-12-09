package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.util.Locale;

public class UnderscoreStringFormatTransformer implements UnderscoreTransformer<String> {
    private Locale locale;

    private Object[] args;

    public UnderscoreStringFormatTransformer(Object... args) {
        this(null, args);
    }

    public UnderscoreStringFormatTransformer(Locale locale, Object... args) {
        this.locale = locale;
        this.args = args;
    }

    @Override
    public String transform(String appendee) {
        if (locale != null) {
            return String.format(locale, appendee, args);
        }
        return String.format(appendee, args);
    }
}
