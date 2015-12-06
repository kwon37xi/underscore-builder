package kr.pe.kwonnam.underscore;

/**
 *
 * @param <B> Builder Class Type
 */
public interface UnderscoreBuilder<B extends UnderscoreBuilder<B>> extends CharSequence {
    <A> UnderscoreBuilder<B> __(A appendee);

    <A> UnderscoreBuilder<B> __(A appendee, UnderscoreTransformer<B, A> transformer);

    <A> UnderscoreBuilder<B> __(boolean appendable, A appendee);

    <A> UnderscoreBuilder<B> __(boolean appendable, A appendee, UnderscoreTransformer<B, A> transformer);

    <A> UnderscoreBuilder<B> __(UnderscorePredicate predicate, A appendee);

    <A> UnderscoreBuilder<B> __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<B, A> transformer);

    UnderscoreBuilder<B> __(boolean appendable, UnderscoreSubBuild<B> subBuild);

    UnderscoreBuilder<B> __(boolean appendable, UnderscoreSubBuild<B> subBuild, UnderscoreTransformer<B, B> transformer);

    UnderscoreBuilder<B> __(UnderscorePredicate predicate, UnderscoreSubBuild<B> subBuild);

    UnderscoreBuilder<B> __(UnderscorePredicate predicate, UnderscoreSubBuild<B> subBuild, UnderscoreTransformer<B, B> transformer);
}
