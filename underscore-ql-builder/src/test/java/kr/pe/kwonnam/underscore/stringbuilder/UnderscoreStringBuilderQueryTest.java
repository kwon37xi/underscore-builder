package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscoreSubBuild;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.trim;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 */
public class UnderscoreStringBuilderQueryTest {
    private final Logger log = getLogger(UnderscoreStringBuilderQueryTest.class);

    private UnderscoreStringBuilder underscoreStringBuilder;

    @Before
    public void setUp() throws Exception {
        underscoreStringBuilder = new UnderscoreStringBuilder();
    }

    @Test
    public void buildWithStringBuilder() throws Exception {
        underscoreStringBuilder
            .sub(new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("   AND Hello   ");
                }
            }, trim(trimOpts().prefix("WHERE ").prefixOverrides("AND ")));

        log.info("Underscore query : {}", underscoreStringBuilder.toString());
    }
}
