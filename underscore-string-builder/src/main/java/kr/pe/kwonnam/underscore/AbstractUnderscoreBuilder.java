package kr.pe.kwonnam.underscore;

import java.io.IOException;

public abstract class AbstractUnderscoreBuilder implements UnderscoreBuilder {

    @Override
    public UnderscoreBuilder __(Object appendee) {
        return __(true, appendee);
    }

    @Override
    public UnderscoreBuilder __(Object appendee, UnderscoreFilter filter) {
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
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, Object appendee, UnderscoreFilter filter) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }

        return __(underscorePredicate.evaluate(this), appendee, filter);
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
    public UnderscoreBuilder __(String appendee, UnderscoreFilter filter) {
        return __(true, appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee) {
        return __(underscorePredicate, appendee, null);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, String appendee, UnderscoreFilter filter) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(underscorePredicate.evaluate(this), appendee, filter);
    }

    @Override
    public UnderscoreBuilder __(UnderscorePredicate underscorePredicate, UnderscoreSubBuild underscoreSubBuild) {
        if (underscorePredicate == null) {
            throw new IllegalArgumentException("underscorePredicate must not be null.");
        }
        return __(underscorePredicate.evaluate(this), underscoreSubBuild);
    }

    @Override
    public UnderscoreBuilder append(CharSequence csq) throws IOException {
        return __(csq);
    }

    @Override
    public UnderscoreBuilder append(char c) throws IOException {
        return __(c);
    }
}
