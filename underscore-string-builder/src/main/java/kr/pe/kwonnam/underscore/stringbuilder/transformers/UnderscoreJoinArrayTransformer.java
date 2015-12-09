package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.util.Iterator;

/**
 * Iterable(Collection) to String transformer with a separator.
 */
public class UnderscoreJoinArrayTransformer implements UnderscoreTransformer<Object[]> {

    private String separator = "";

    private String nullValue = null;

    public UnderscoreJoinArrayTransformer(String separator, String nullValue) {
        this.separator = separator == null ? "" : separator;
        this.nullValue = nullValue;
    }

    @Override
    public CharSequence transform(Object[] appended) {
        if (appended == null) {
            return null;
        }

        final int arrayLength = appended.length;
        if (arrayLength == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arrayLength; i++) {
            if (i > 0) {
                stringBuilder.append(separator);
            }
            stringBuilder.append(appended[i] == null ? nullValue : appended[i]);
        }
        return stringBuilder.toString();
    }
}
