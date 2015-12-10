package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.dateFormat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UnderscoreDateFormatTransformerTest extends AbstractTransformerTest {

    private Date date;

    @Before
    public void setUp() throws Exception {
        date = DateUtils.parseDate("2015/12/09 23:41:19", "yyyy/MM/dd HH:mm:ss");
    }

    @Test
    public void transform_format_appendee_null() throws Exception {
        try {
            underscoreStringBuilder.__((Date) null, dateFormat("yyyy/MM/dd"));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("appendee must not be null for dateFormat."));
        }
    }

    @Test
    public void transform_format_null() throws Exception {
        try {
            underscoreStringBuilder.__(date, dateFormat(null));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("dateFormat must not be null."));
        }
    }

    @Test
    public void transform() throws Exception {
        assertThat(underscoreStringBuilder.__(date, dateFormat("yyyy/MM/dd HH:mm:ss")).toString(), is("2015/12/09 23:41:19"));
    }

    @Test
    public void transform_date() throws Exception {
        assertThat(underscoreStringBuilder.__(date, dateFormat("yyyy/MM/dd")).toString(), is("2015/12/09"));
    }

    @Test
    public void transform_time() throws Exception {
        assertThat(underscoreStringBuilder.__(date, dateFormat("HH:mm:ss")).toString(), is("23:41:19"));
    }
}
