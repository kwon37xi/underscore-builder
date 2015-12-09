package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.util.Iterator;

/**
 * List/Array to String transformer with a separator.
 */
public class UnderscoreJoinIterableTransformer implements UnderscoreTransformer<Iterable<?>> {

    private String separator = "";

    private String nullValue = null;

    public UnderscoreJoinIterableTransformer(String separator, String nullValue) {
        this.separator = separator == null ? "" : separator;
        this.nullValue = nullValue;
    }

    @Override
    public CharSequence transform(Iterable<?> appended) {
        if (appended == null) {
            return null;
        }
        final Iterator<?> iterator = appended.iterator();

        StringBuilder stringBuilder = new StringBuilder();

        while (iterator.hasNext()) {
            final Object value = iterator.next();
            stringBuilder.append(value == null ? nullValue : value);

            if (iterator.hasNext()) {
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }
}
