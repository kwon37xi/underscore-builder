package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.trim;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnderscoreTrimTransformerTest extends AbstractTransformerTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreTrimTransformerTest.class);


    @Test
    public void transform_appendee_null() throws Exception {
        assertThat(underscoreStringBuilder.__((String) null, trim()).toString(), is(""));
    }

    @Test
    public void trim_without_trimOpts() throws Exception {
        assertThat(underscoreStringBuilder.__("  \n\tHello World! \t\t\n \r\n ", trim()).toString(), is("Hello World!"));
    }

    @Test
    public void trim_with_trimOpts() throws Exception {
        assertThat(underscoreStringBuilder.__("    \t\t\r\nAND Hello World!, \r \t",
            trim(trimOpts().prefix("[").prefixOverrides("OR ", "AND ").suffix("]").suffixOverrides(".", ","))).toString(), is("[Hello World!]"));
    }
}
