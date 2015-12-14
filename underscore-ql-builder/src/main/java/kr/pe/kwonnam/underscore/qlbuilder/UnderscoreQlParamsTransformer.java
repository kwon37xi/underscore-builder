package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

/**
 * Transform object array to query parameters and generate a query string with {@link String#format(String, Object...)}.
 */
public class UnderscoreQlParamsTransformer implements UnderscoreTransformer<CharSequence> {
    private UnderscoreQlParams underscoreQlParams;

    private Object[] queryParams;

    public UnderscoreQlParamsTransformer(UnderscoreQlParams underscoreQlParamses, Object[] queryParams) {
        if (queryParams == null || queryParams.length == 0) {
            throw new IllegalArgumentException("params must not be null or empty.");
        }
        this.underscoreQlParams = underscoreQlParamses;
        this.queryParams = queryParams;
    }

    @Override
    public CharSequence transform(CharSequence appendee) {
        if (appendee == null) {
            throw new IllegalArgumentException("appendee must not be null.");
        }
        Object[] positionalParameters = new Object[queryParams.length];
        for (int i = 0; i < queryParams.length; i++) {
            positionalParameters[i] = underscoreQlParams.addParam(queryParams[i]);
        }
        return String.format(appendee.toString(), positionalParameters);
    }
}
