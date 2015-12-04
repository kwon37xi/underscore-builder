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
    public <A> UnderscoreStringBuilder __(A appendee) {
        return __(true, appendee);
    }

    @Override
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee) {
        return __(appendable, appendee, null);
    }

    @Override
    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreFilter<UnderscoreStringBuilder, A> filter) {
        return __(true, appendee, filter);
    }

    @Override
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreFilter<UnderscoreStringBuilder, A> filter) {
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
    public <A> UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, A appendee) {
        return __(underscorePredicate, appendee, null);
    }

    @Override
    public <A> UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, A appendee, UnderscoreFilter<UnderscoreStringBuilder, A> filter) {
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
