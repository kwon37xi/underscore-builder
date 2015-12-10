package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Test;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.wrap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UnderscoreWrapTransformerTest extends AbstractTransformerTest {

    @Test
    public void transform_null() throws Exception {
        assertThat(underscoreStringBuilder.__((String)null, wrap("[", "]")).toString(), is(""));
    }

    @Test
    public void transform() throws Exception {
        assertThat(underscoreStringBuilder.__("Hello", wrap("[", "]")).toString(), is("[Hello]"));
    }

    @Test
    public void transform_another() throws Exception {
        assertThat(underscoreStringBuilder.__("World", wrap("===> ", " <===")).toString(), is("===> World <==="));
    }
}
