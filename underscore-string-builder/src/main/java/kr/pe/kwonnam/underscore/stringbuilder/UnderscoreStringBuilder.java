package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.AbstractUnderscoreBuilder;
import kr.pe.kwonnam.underscore.UnderscoreBuilder;
import kr.pe.kwonnam.underscore.UnderscoreFilter;
import kr.pe.kwonnam.underscore.UnderscoreSubBuild;

import java.io.IOException;
import java.util.Map;

/**
 * UnderscoreStringBuilder builds string.
 *
 */
public class UnderscoreStringBuilder extends AbstractUnderscoreBuilder {
    private StringBuilder stringBuilder;

    @Override
    public UnderscoreBuilder __(boolean appendable, Object appendee, UnderscoreFilter filter) {
        return null;
    }

    @Override
    public UnderscoreBuilder __(boolean appendable, String appendee, UnderscoreFilter filter) {
        return null;
    }

    @Override
    public UnderscoreBuilder __(boolean appendable, UnderscoreSubBuild underscoreSubBuild) {
        return null;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
