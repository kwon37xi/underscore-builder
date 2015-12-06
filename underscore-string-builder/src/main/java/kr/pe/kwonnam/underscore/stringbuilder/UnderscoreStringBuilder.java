package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.*;

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
    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<UnderscoreStringBuilder, A> transformer) {
        return __(true, appendee, transformer);
    }

    @Override
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<UnderscoreStringBuilder, A> transformer) {
        if (!appendable) {
            return this;
        }

        if (transformer != null) {
            transformer.transform(this, appendee);
            return this;
        }

        stringBuilder.append(appendee);
        return this;
    }

    @Override
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee) {
        return __(predicate, appendee, null);
    }

    @Override
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<UnderscoreStringBuilder, A> transformer) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(predicate.evaluate(), appendee, transformer);
    }

    @Override
    public UnderscoreStringBuilder __(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return __(predicate.evaluate(), subBuild);
    }

    @Override
    public UnderscoreStringBuilder __(boolean appendable, UnderscoreSubBuild subBuild) {
        return null;
    }

    /**
     * @see StringBuilder#length()
     */
    @Override
    public int length() {
        return stringBuilder.length();
    }

    /**
     * @see StringBuilder#charAt(int)
     */
    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    /**
     * @see StringBuilder#subSequence(int, int)
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
