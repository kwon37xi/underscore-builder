package kr.pe.kwonnam.underscore;

/**
 *
 */
public interface UnderscoreBuilder extends CharSequence, Appendable {
    <A> UnderscoreBuilder __(A appendee);

    <A> UnderscoreBuilder __(A appendee, UnderscoreTransformer<A> transformer);

    <A> UnderscoreBuilder __(boolean appendable, A appendee);

    <A> UnderscoreBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer);

    <A> UnderscoreBuilder __(UnderscorePredicate predicate, A appendee);

    <A> UnderscoreBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer);

    UnderscoreBuilder sub(UnderscoreSubBuild subBuild);

    UnderscoreBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer);

    UnderscoreBuilder sub(boolean appendable, UnderscoreSubBuild subBuild);

    UnderscoreBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer);

    UnderscoreBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild);

    UnderscoreBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<UnderscoreBuilder> transformer);
}
