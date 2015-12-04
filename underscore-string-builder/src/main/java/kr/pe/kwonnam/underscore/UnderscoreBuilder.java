package kr.pe.kwonnam.underscore;

public interface UnderscoreBuilder<B extends UnderscoreBuilder<B>> extends CharSequence {
    UnderscoreBuilder<B> __(Object appendee);

    UnderscoreBuilder<B> __(Object appendee, UnderscoreFilter<B> filter);

    UnderscoreBuilder<B> __(boolean appendable, Object appendee);

    UnderscoreBuilder<B> __(boolean appendable, Object appendee, UnderscoreFilter<B> filter);

    UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, Object appendee);

    UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter<B> filter);

    UnderscoreBuilder<B> __(boolean appendable, UnderscoreSubBuild underscoreSubBuild);

    UnderscoreBuilder<B> __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild);
}
