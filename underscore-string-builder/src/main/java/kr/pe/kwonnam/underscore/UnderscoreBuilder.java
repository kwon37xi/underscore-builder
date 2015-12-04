package kr.pe.kwonnam.underscore;

public interface UnderscoreBuilder<T extends UnderscoreBuilder<T>> extends Appendable, CharSequence {
    UnderscoreBuilder __(Object appendee);

    UnderscoreBuilder __(Object appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(boolean appendable, Object appendee);

    UnderscoreBuilder __(boolean appendable, Object appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(UnderscorePredicate underscorePredicate, Object appendee);

    UnderscoreBuilder __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(String appendee);

    UnderscoreBuilder __(boolean appendable, String appendee);

    UnderscoreBuilder __(String appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(boolean appendable, String appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee);

    UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee, UnderscoreFilter<T> filter);

    UnderscoreBuilder __(boolean appendable, UnderscoreSubBuild underscoreSubBuild);

    UnderscoreBuilder __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild);
}
