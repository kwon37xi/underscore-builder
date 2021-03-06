package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Locale;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UnderscoreStringFormatTransformerTest extends AbstractTransformerTest {
    @Test
    public void transform_appendee_null() throws Exception {
        try {
            underscoreStringBuilder.__((String) null, format("hello"));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception", ex.getMessage(), is("appendee must not be null for format."));
        }
    }

    @Test
    public void transform_format() throws Exception {
        assertThat(underscoreStringBuilder.__("Hello [%10s] [%7d]~", format("World", 12345)).toString(), is("Hello [     World] [  12345]~"));
    }

    @Test
    public void transform_format_without_Locale() throws Exception {
        Locale defaultLocale = Locale.getDefault();

        Date now = new Date();
        assertThat(underscoreStringBuilder.__("%tB %ta", format(now, now)).toString(), is(String.format(defaultLocale, "%tB %ta", now, now)));
    }

    @Test
    public void transform_format_with_locale() throws Exception {
        Date now = new Date();
        assertThat(underscoreStringBuilder.__("%tB %ta", format(Locale.US, now, now)).toString(), is(String.format(Locale.US, "%tB %ta", now, now)));
    }

    @Test
    public void transform_format_with_another_locale() throws Exception {
        Date now = new Date();
        assertThat(underscoreStringBuilder.__("%tB %ta", format(Locale.FRENCH, now, now)).toString(), is(String.format(Locale.FRENCH, "%tB %ta", now, now)));
    }
}
