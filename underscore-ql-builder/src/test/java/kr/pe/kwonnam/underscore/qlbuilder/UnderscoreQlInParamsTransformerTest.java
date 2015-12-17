package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UnderscoreQlInParamsTransformerTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlInParamsTransformerTest.class);

    private UnderscoreStringBuilder underscoreStringBuilder;

    private UnderscoreQlParams underscoreQlParams;

    private UnderscoreQlParams underscoreQlParamsWithPositionalIndex;

    @Before
    public void setUp() throws Exception {
        underscoreStringBuilder = new UnderscoreStringBuilder();
        underscoreQlParams = new UnderscoreQlParams();
        underscoreQlParamsWithPositionalIndex = UnderscoreQlParams.withPositionalIndex();
    }

    @Test
    public void inParams_appendee_null() throws Exception {
        try {
            underscoreStringBuilder.__((String) null, underscoreQlParams.inParams(new Object[]{"hello", "world"}));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("appendee must not be null."));
        }
    }

    @Test
    public void inParams_array_inParams_null() throws Exception {
        try {
            underscoreStringBuilder.__("user_id in (%s)", underscoreQlParams.inParams((Object[]) null));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("inParams must not be null or empty."));
        }
    }

    @Test
    public void inParams_array_inParams_empty() throws Exception {
        try {
            underscoreStringBuilder.__("user_id in (%s)", underscoreQlParams.inParams(new Object[]{}));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("inParams must not be null or empty."));
        }
    }

    @Test
    public void inParams_iterable_inParams_null() throws Exception {
        try {
            underscoreStringBuilder.__("user_id in (%s)", underscoreQlParams.inParams((List<String>) null));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("inParams must not be null or empty."));
        }
    }

    @Test
    public void inParams_iterable_inParams_empty() throws Exception {
        try {
            underscoreStringBuilder.__("user_id in (%s)", underscoreQlParams.inParams(new ArrayList<String>()));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("inParams must not be null or empty."));
        }
    }

    @Test
    public void inParams_array() throws Exception {
        underscoreStringBuilder.__("user_id IN (%s)", underscoreQlParams.inParams(new String[]{"scott", "tiger", "jane"}));

        assertThat(underscoreStringBuilder.toString(), is("user_id IN (?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) "scott", "tiger", "jane"));
    }

    @Test
    public void inParams_array_withPositionalIndex() throws Exception {
        underscoreStringBuilder.__("user_id IN (%s)", underscoreQlParamsWithPositionalIndex.inParams(new String[]{"scott", "tiger", "jane"}));

        assertThat(underscoreStringBuilder.toString(), is("user_id IN (?1, ?2, ?3)"));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasItems((Object) "scott", "tiger", "jane"));
    }

    @Test
    public void inParams_iterable() throws Exception {
        underscoreStringBuilder.__("user_id IN (%s)", underscoreQlParams.inParams(new ArrayList<String>(Arrays.asList("scott", "tiger", "jane"))));

        assertThat(underscoreStringBuilder.toString(), is("user_id IN (?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) "scott", "tiger", "jane"));
    }

    @Test
    public void inParams_iterable_withPositionalIndex() throws Exception {
        underscoreStringBuilder.__("user_id IN (%s)", underscoreQlParamsWithPositionalIndex.inParams(new ArrayList<String>(Arrays.asList("scott", "tiger", "jane"))));

        assertThat(underscoreStringBuilder.toString(), is("user_id IN (?1, ?2, ?3)"));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasItems((Object) "scott", "tiger", "jane"));

    }
}
