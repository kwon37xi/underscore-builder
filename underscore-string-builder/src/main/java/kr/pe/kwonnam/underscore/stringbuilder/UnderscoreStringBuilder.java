package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.*;

import java.io.IOException;
import java.util.Map;

/**
 * UnderscoreStringBuilder builds string.
 */
public class UnderscoreStringBuilder implements UnderscoreBuilder<UnderscoreStringBuilder> {
    private StringBuilder stringBuilder;

    public UnderscoreStringBuilder() {
        this(new StringBuilder());
    }

    public UnderscoreStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public UnderscoreStringBuilder __(Object appendee) {
        return __(true, appendee);
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, Object appendee) {
        return __(appendable, appendee, null);
    }

    @Override
    public UnderscoreStringBuilder __(Object appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        return __(true, appendee, filter);
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, Object appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        if (!appendable) {
            return this;
        }

        if (filter != null) {
            filter.filter(this, appendee);
            return this;
        }

        stringBuilder.append(appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, Object appendee) {
        return __(underscorePredicate, appendee, null);
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(underscorePredicate.evaluate(), appendee, filter);
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild) {
        return __(underscorePredicate.evaluate(), underscoreSubBuild);
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, UnderscoreSubBuild underscoreSubBuild) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
