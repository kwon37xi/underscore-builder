package kr.pe.kwonnam.underscore.qlparams;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

import java.util.Iterator;

/**
 * Transform object array or iterable to IN cluase's query parameters and generate a query string with {@link String#format(String, Object...)}.
 */
public class UnderscoreQlInParamsTransformer implements UnderscoreTransformer<CharSequence> {
    private UnderscoreQlParams underscoreQlParams;

    private Object[] inQueryParamsArray;

    private Iterable<?> inQueryParamsIterable;

    public UnderscoreQlInParamsTransformer(UnderscoreQlParams underscoreQlParams, Object[] inQueryParamsArray) {
        if (inQueryParamsArray == null || inQueryParamsArray.length == 0) {
            throw new IllegalArgumentException("inParams must not be null or empty.");
        }

        this.underscoreQlParams = underscoreQlParams;
        this.inQueryParamsArray = inQueryParamsArray;
    }

    public UnderscoreQlInParamsTransformer(UnderscoreQlParams underscoreQlParams, Iterable<?> inQueryParamsIterable) {
        if (inQueryParamsIterable == null || !inQueryParamsIterable.iterator().hasNext()) {
            throw new IllegalArgumentException("inParams must not be null or empty.");
        }

        this.underscoreQlParams = underscoreQlParams;
        this.inQueryParamsIterable = inQueryParamsIterable;
    }

    @Override
    public CharSequence transform(CharSequence appendee) {
        if (appendee == null) {
            throw new IllegalArgumentException("appendee must not be null.");
        }

        if (inQueryParamsArray != null) {
            return processInQueryParamsArray(appendee);
        }
        return processInQueryParamsIterable(appendee);
    }

    private CharSequence processInQueryParamsArray(CharSequence appendee) {
        StringBuilder positionalParameterBuilder = new StringBuilder();

        for (int i = 0; i < inQueryParamsArray.length; i++) {
            if (i > 0) {
                positionalParameterBuilder.append(", ");
            }
            final String positionalParameter = underscoreQlParams.addParam(inQueryParamsArray[i]);
            positionalParameterBuilder.append(positionalParameter);
        }
        return String.format(appendee.toString(), positionalParameterBuilder.toString());
    }

    private CharSequence processInQueryParamsIterable(CharSequence appendee) {
        StringBuilder positionalParameterBuilder = new StringBuilder();
        final Iterator<?> iterator = inQueryParamsIterable.iterator();
        while (iterator.hasNext()) {
            final String positionalParameter = underscoreQlParams.addParam(iterator.next());
            positionalParameterBuilder.append(positionalParameter);

            if (iterator.hasNext()) {
                positionalParameterBuilder.append(", ");
            }
        }
        return String.format(appendee.toString(), positionalParameterBuilder.toString());
    }
}
