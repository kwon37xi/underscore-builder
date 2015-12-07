package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.*;

import java.io.IOException;

/**
 * UnderscoreStringBuilder builds string.
 */
public class UnderscoreStringBuilder implements UnderscoreBuilder {
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
    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<A> transformer) {
        return __(true, appendee, transformer);
    }

    @Override
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer) {
        if (!appendable) {
            return this;
        }

        if (transformer != null) {
            __(transformer.transform(appendee));
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
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(predicate.evaluate(), appendee, transformer);
    }

    @Override
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild) {
        return sub(true, subBuild);
    }

    @Override
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer) {
        return sub(true, subBuild, transformer);
    }

    @Override
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild) {
        return sub(appendable, subBuild, null);
    }

    @Override
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer) {
        if (!appendable) {
            return this;
        }

        if (subBuild == null) {
            throw new IllegalArgumentException("subBuild must not be null.");
        }

        UnderscoreStringBuilder subBuilder = new UnderscoreStringBuilder();
        subBuild.subbuild(subBuilder);

        if (transformer != null) {
            __(transformer.transform(subBuilder));
            return this;
        }

        __(subBuilder.toString());
        return this;
    }

    @Override
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return sub(predicate, subBuild, null);
    }

    @Override
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return sub(predicate.evaluate(), subBuild, transformer);
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
    public UnderscoreStringBuilder append(CharSequence csq) throws IOException {
        stringBuilder.append(csq);
        return this;
    }

    @Override
    public UnderscoreStringBuilder append(CharSequence csq, int start, int end) throws IOException {
        stringBuilder.append(csq, start, end);
        return this;
    }

    @Override
    public UnderscoreStringBuilder append(char c) throws IOException {
        stringBuilder.append(c);
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
