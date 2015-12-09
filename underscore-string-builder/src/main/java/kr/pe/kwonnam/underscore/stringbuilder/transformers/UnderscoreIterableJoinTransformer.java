package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.util.Iterator;

/**
 * List/Array to String transformer with a separator.
 */
public class UnderscoreIterableJoinTransformer implements UnderscoreTransformer<Iterable<?>> {
    private String separator = "";

    public UnderscoreIterableJoinTransformer(String separator) {
        if (separator == null) {
            this.separator = "";
            return;
        }
        this.separator = separator;
    }

    @Override
    public CharSequence transform(Iterable<?> appended) {
        if (appended == null) {
            return null;
        }
        final Iterator<?> iterator = appended.iterator();

        StringBuilder stringBuilder = new StringBuilder();

        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) {
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }
}
