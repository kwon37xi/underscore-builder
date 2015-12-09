package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

public class UnderscoreMultiplyTransformer implements UnderscoreTransformer<Object> {

    private int factor;

    private String separator = "";

    public UnderscoreMultiplyTransformer(int factor, String separator) {
        this.factor = factor;
        this.separator = separator == null ? "" : separator;
    }

    @Override
    public CharSequence transform(Object appended) {
        if (appended == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < factor; i++) {
            if (i > 0) {
                stringBuilder.append(separator);
            }
            stringBuilder.append(appended);

        }
        return stringBuilder.toString();
    }
}
