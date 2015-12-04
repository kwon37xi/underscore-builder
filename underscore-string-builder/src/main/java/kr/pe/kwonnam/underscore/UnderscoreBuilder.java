package kr.pe.kwonnam.underscore;

/**
 *
 * @param <B> Builder Class Type
 */
public interface UnderscoreBuilder<B extends UnderscoreBuilder<B>> extends CharSequence {
    <A> UnderscoreBuilder<B> __(A appendee);

    <A> UnderscoreBuilder<B> __(A appendee, UnderscoreFilter<B, A> filter);

    <A> UnderscoreBuilder<B> __(boolean appendable, A appendee);

    <A> UnderscoreBuilder<B> __(boolean appendable, A appendee, UnderscoreFilter<B, A> filter);

    <A> UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, A appendee);

    <A> UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, A appendee, UnderscoreFilter<B, A> filter);

    UnderscoreBuilder<B> __(boolean appendable, UnderscoreSubBuild underscoreSubBuild);

    UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild);
}
