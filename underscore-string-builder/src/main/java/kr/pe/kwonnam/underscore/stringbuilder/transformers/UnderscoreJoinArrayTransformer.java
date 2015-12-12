package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

/**
 * Iterable(Collection) to String transformer with a separator.
 */
public class UnderscoreJoinArrayTransformer implements UnderscoreTransformer<Object[]> {

    private String separator = "";

    private String nullValue = "";

    public UnderscoreJoinArrayTransformer(String separator, String nullValue) {
        this.separator = separator == null ? "" : separator;
        this.nullValue = nullValue == null ? "" : nullValue;
    }

    @Override
    public CharSequence transform(Object[] appendee) {
        if (appendee == null) {
            return "";
        }

        final int arrayLength = appendee.length;
        if (arrayLength == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arrayLength; i++) {
            if (i > 0) {
                stringBuilder.append(separator);
            }
            stringBuilder.append(appendee[i] == null ? nullValue : appendee[i]);
        }
        return stringBuilder.toString();
    }
}
