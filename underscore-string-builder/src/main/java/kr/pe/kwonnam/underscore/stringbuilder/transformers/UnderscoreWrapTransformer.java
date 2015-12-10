package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

/**
 * Wrap contents when contents is not null.
 */
public class UnderscoreWrapTransformer implements UnderscoreTransformer<Object> {
    private String left;

    private String right;

    public UnderscoreWrapTransformer(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public CharSequence transform(Object appendee) {
        if (appendee == null) {
            return "";
        }

        return left + appendee + right;
    }
}
