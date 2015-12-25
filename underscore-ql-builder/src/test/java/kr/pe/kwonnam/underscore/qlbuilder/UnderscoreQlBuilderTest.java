package kr.pe.kwonnam.underscore.qlbuilder;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnderscoreQlBuilderTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlBuilderTest.class);

    private UnderscoreQlBuilder underscoreQlBuilder;

    @Before
    public void setUp() throws Exception {
        underscoreQlBuilder = new UnderscoreQlBuilder();
    }
}
