package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Test;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.multiply;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UnderscoreMultiplyTransformerTest extends AbstractTransformerTest {

    @Test
    public void transform_null() throws Exception {
        assertThat(underscoreStringBuilder.__((String) null, multiply(3)).toString(), is(""));
    }

    @Test
    public void transform() throws Exception {
        assertThat(underscoreStringBuilder.__(123, multiply(3)).toString(), is("123123123"));
    }

    @Test
    public void transform_another() throws Exception {
        assertThat(underscoreStringBuilder.__("=", multiply(20)).toString(), is("===================="));
    }

    @Test
    public void transform_with_separator() throws Exception {
        assertThat(underscoreStringBuilder.__(123, multiply(3, ",")).toString(), is("123,123,123"));
    }

    @Test
    public void transform_with_separator_another() throws Exception {
        assertThat(underscoreStringBuilder.__("=", multiply(20, "-")).toString(), is("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
    }
}
