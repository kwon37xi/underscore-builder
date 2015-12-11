package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscorePredicate;
import kr.pe.kwonnam.underscore.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.io.IOException;

/**
 * UnderscoreStringBuilder builds string.
 */
public class UnderscoreStringBuilder implements CharSequence, Appendable {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private StringBuilder stringBuilder;

    private String prefix;
    private String suffix;

    public UnderscoreStringBuilder() {
        this(new StringBuilder());
    }

    public UnderscoreStringBuilder(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            throw new NullPointerException("stringBuilder must not be null.");
        }
        this.stringBuilder = stringBuilder;
    }

    public <A> UnderscoreStringBuilder __(A appendee) {
        return __(true, appendee);
    }

    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee) {
        return __(appendable, appendee, null);
    }

    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<A> transformer) {
        return __(true, appendee, transformer);
    }

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

    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee) {
        return __(predicate, appendee, null);
    }

    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(predicate.evaluate(), appendee, transformer);
    }

    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild) {
        return sub(true, subBuild);
    }

    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreStringBuilder> transformer) {
        return sub(true, subBuild, transformer);
    }

    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild) {
        return sub(appendable, subBuild, null);
    }

    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreStringBuilder> transformer) {
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

    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return sub(predicate, subBuild, null);
    }

    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreStringBuilder> transformer) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return sub(predicate.evaluate(), subBuild, transformer);
    }

    public UnderscoreStringBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public UnderscoreStringBuilder prefixOff() {
        return prefix(null);
    }

    public UnderscoreStringBuilder prefixNewLine() {
        return prefix(LINE_SEPARATOR);
    }

    public UnderscoreStringBuilder suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public UnderscoreStringBuilder suffixOff() {
        return suffix(null);
    }

    public UnderscoreStringBuilder suffixNewLine() {
        return suffix(LINE_SEPARATOR);
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
