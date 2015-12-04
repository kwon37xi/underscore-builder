package kr.pe.kwonnam.underscore;

import java.io.IOException;

public abstract class AbstractUnderscoreBuilder<T extends UnderscoreBuilder<T>> implements UnderscoreBuilder<T> {

    @Override
    public UnderscoreBuilder __(Object appendee) {
        return __(true, appendee);
    }

    @Override
    public UnderscoreBuilder __(Object appendee, UnderscoreFilter<T> filter) {
        return __(true, appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(boolean appendable, Object appendee) {
        return __(appendable, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, Object appendee) {
        return __(underscorePredicate, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter<T> filter) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }

        return __(underscorePredicate.evaluate(), appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(String appendee) {
        return __(true, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(boolean appendable, String appendee) {
        return __(appendable, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(String appendee, UnderscoreFilter<T> filter) {
        return __(true, appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee) {
        return __(underscorePredicate, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee, UnderscoreFilter<T> filter) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(underscorePredicate.evaluate(), appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(underscorePredicate.evaluate(), underscoreSubBuild);
    }
}
