package kr.pe.kwonnam.underscore.stringbuilder;

import java.io.IOException;

/**
 * UnderscoreStringBuilder builds string.
 */
public final class UnderscoreStringBuilder implements CharSequence, Appendable {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

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

    /**
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(A appendee) {
        return __(true, appendee);
    }

    /**
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee) {
        return __(appendable, appendee, null, (UnderscoreTransformer<? super CharSequence>[]) null);
    }

    /**
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return __(true, appendee, transformer, extraTransformers);
    }

    /**
     * Appends <code>appendee</code> if <code>appendable</code>is true and transforms the appendee with transformer and extranTransformers.
     *
     * @param appendable whether append the appendee or not.
     * @param appendee an object that will be converted to string
     * @param transformer transformer to transform the appendee
     * @param extraTransformers extra transformers to transform the result of the transformer.
     * @param <A> appendee's type
     * @return this
     */
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        if (!appendable) {
            return this;
        }

        doPrefix();
        Object finalAppendee = doTransform(appendee, transformer, extraTransformers);
        stringBuilder.append(finalAppendee);
        doSuffix();
        return this;
    }

    private <A> Object doTransform(A appendee, UnderscoreTransformer<A> transformer, UnderscoreTransformer<? super CharSequence>[] extraTransformers) {
        if (transformer == null) {
            return appendee;
        }

        CharSequence transformed = transformer.transform(appendee);

        if (extraTransformers != null && extraTransformers.length > 0) {
            for (UnderscoreTransformer<? super CharSequence> extraTransformer : extraTransformers) {
                transformed = extraTransformer.transform(transformed);
            }
        }

        return transformed;
    }

    private void doPrefix() {
        if (prefix != null) {
            stringBuilder.append(prefix);
        }
    }

    private void doSuffix() {
        if (suffix != null) {
            stringBuilder.append(suffix);
        }
    }

    /**
     * <code>predicate</code>'s result will be <code>appendable</code> value.
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee) {
        return __(predicate, appendee, null, (UnderscoreTransformer<? super CharSequence>[]) null);
    }

    /**
     * <code>predicate</code>'s result will be <code>appendable</code> value.
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(predicate.evaluate(), appendee, transformer, extraTransformers);
    }


    /**
     * @see #sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild) {
        return sub(true, subBuild);
    }

    /**
     * @see #sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return sub(true, subBuild, transformer, extraTransformers);
    }

    /**
     * @see #sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild) {
        return sub(appendable, subBuild, null, (UnderscoreTransformer<? super CharSequence>[]) null);
    }

    /**
     * Executes <code>subBuild</code> then add the result to this character sequence.
     * @see #__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        if (!appendable) {
            return this;
        }

        if (subBuild == null) {
            throw new IllegalArgumentException("subBuild must not be null.");
        }

        UnderscoreStringBuilder subBuilder = new UnderscoreStringBuilder();
        subBuild.subbuild(subBuilder);

        return __(subBuilder, transformer, extraTransformers);
    }

    /**
     * <code>predicate</code>'s result will be <code>appendable</code> value.
     * @see #sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return sub(predicate, subBuild, null, (UnderscoreTransformer<? super CharSequence>[]) null);
    }

    /**
     * <code>predicate</code>'s result will be <code>appendable</code> value.
     * @see #sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        if (predicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return sub(predicate.evaluate(), subBuild, transformer, extraTransformers);
    }

    /**
     * prefix appendees after this method called.
     *
     * @param prefix prefix string
     * @return this
     */
    public UnderscoreStringBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * disable prefixing
     *
     * @return this
     */
    public UnderscoreStringBuilder prefixOff() {
        return prefix(null);
    }

    /**
     * prefix appendees with new line(line separator) after this method called
     *
     * @return this
     */
    public UnderscoreStringBuilder prefixNewLine() {
        return prefix(LINE_SEPARATOR);
    }

    /**
     * suffix appendees after this method called
     *
     * @param suffix suffix string
     * @return this
     */
    public UnderscoreStringBuilder suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * disable suffixing
     *
     * @return this
     */
    public UnderscoreStringBuilder suffixOff() {
        return suffix(null);
    }

    /**
     * suffix appendees with new line(line separator) after this method called
     *
     * @return this
     */
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

    /**
     * {@inheritDoc}
     * <p>prefix/suffix does not work with append methods.</p>
     */
    @Override
    public UnderscoreStringBuilder append(CharSequence csq) throws IOException {
        stringBuilder.append(csq);
        return this;
    }

    /**
     * {@inheritDoc}
     * <p>prefix/suffix does not work with append methods.</p>
     */
    @Override
    public UnderscoreStringBuilder append(CharSequence csq, int start, int end) throws IOException {
        stringBuilder.append(csq, start, end);
        return this;
    }

    /**
     * {@inheritDoc}
     * <p>prefix/suffix does not work with append methods.</p>
     */
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
