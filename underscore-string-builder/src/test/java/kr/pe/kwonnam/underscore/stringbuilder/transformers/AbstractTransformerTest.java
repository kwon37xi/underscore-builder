package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import org.junit.Before;

public abstract class AbstractTransformerTest {
    protected UnderscoreStringBuilder underscoreStringBuilder;

    @Before
    public void setup() throws Exception {
        this.underscoreStringBuilder = new UnderscoreStringBuilder();
    }
}
