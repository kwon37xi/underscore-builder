package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.multiply;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
