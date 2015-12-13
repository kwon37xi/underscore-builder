package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

import java.util.Iterator;

/**
 * Join Array or List with appendee.
 */
public class UnderscoreJoinTransformer implements UnderscoreTransformer<CharSequence> {
    private Iterable<?> iterable;

    private Object[] objectArray;

    private String nullValue;

    public UnderscoreJoinTransformer(Iterable<?> iterable, String nullValue) {
        this.iterable = iterable;
        this.nullValue = refineNullValue(nullValue);
    }

    private String refineNullValue(String nullValue) {
        return nullValue == null ? "" : nullValue;
    }

    public UnderscoreJoinTransformer(Object[] objects, String nullValue) {
        this.objectArray = objects;
        this.nullValue = refineNullValue(nullValue);
    }

    @Override
    public CharSequence transform(CharSequence appendee) {
        CharSequence separator =  appendee == null ? "" : appendee;

        if (iterable != null) {
            return joinIterable(separator);
        }

        if (objectArray != null) {
            return joinArray(separator);
        }

        return "";
    }

    private CharSequence joinIterable(CharSequence separator) {
        final Iterator<?> iterator = iterable.iterator();

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

    private CharSequence joinArray(CharSequence separator) {
        final int arrayLength = objectArray.length;
        if (arrayLength == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arrayLength; i++) {
            if (i > 0) {
                stringBuilder.append(separator);
            }
            stringBuilder.append(objectArray[i] == null ? nullValue : objectArray[i]);
        }
        return stringBuilder.toString();
    }
}
