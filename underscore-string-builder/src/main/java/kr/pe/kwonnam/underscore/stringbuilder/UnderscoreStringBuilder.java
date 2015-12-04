package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.*;

import java.io.IOException;
import java.util.Map;

/**
 * UnderscoreStringBuilder builds string.
 *
 */
public class UnderscoreStringBuilder extends AbstractUnderscoreBuilder<UnderscoreStringBuilder> {
    private StringBuilder stringBuilder;

    public UnderscoreStringBuilder() {
        this(new StringBuilder());
    }

    public UnderscoreStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public UnderscoreStringBuilder __(Object appendee) {
        super.__(appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, Object appendee) {
        super.__(appendable, appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(Object appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        super.__(appendee, filter);
        return this;
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
        super.__(underscorePredicate, appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        super.__(underscorePredicate, appendee, filter);
        return this;
    }


    @Override
    public UnderscoreStringBuilder __(String appendee) {
        super.__(appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, String appendee) {
        super.__(appendable, appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(String appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        super.__(appendee, filter);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, String appendee) {
        super.__(underscorePredicate, appendee);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, String appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        super.__(underscorePredicate, appendee, filter);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild) {
        super.__(underscorePredicate, underscoreSubBuild);
        return this;
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, String appendee, UnderscoreFilter<UnderscoreStringBuilder> filter) {
        stringBuilder.append(appendee);
        return null;
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
