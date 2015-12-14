package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UnderscoreQlParamsTransformerTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlParamsTransformerTest.class);

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
    public void params_appendee_null() throws Exception {
        try {
            underscoreStringBuilder.__((String) null, underscoreQlParams.params("hello", "world"));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("appendee must not be null."));
        }
    }

    @Test
    public void params_params_null() throws Exception {
        try {
            underscoreStringBuilder.__("user_id = %s", underscoreQlParams.params((Object[]) null));
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("params must not be null or empty."));
        }
    }

    @Test
    public void params_params_empty() throws Exception {
        try {
            underscoreStringBuilder.__("user_id = %s", underscoreQlParams.params());
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("params must not be null or empty."));
        }
    }

    @Test
    public void params() throws Exception {
        underscoreStringBuilder.__("AND (user_id = %s OR user_id = %s) AND age > %s", underscoreQlParams.params("scott", "tiger", 30));

        assertThat(underscoreStringBuilder.toString(), is("AND (user_id = ? OR user_id = ?) AND age > ?"));

        assertThat(underscoreQlParams.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) "scott", "tiger", 30));
    }

    @Test
    public void params_withPositionalIndex() throws Exception {
        underscoreStringBuilder.__("AND (user_id = %s OR user_id = %s) AND age > %s", underscoreQlParamsWithPositionalIndex.params("tiger", "scott", 20));

        assertThat(underscoreStringBuilder.toString(), is("AND (user_id = ?1 OR user_id = ?2) AND age > ?3"));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParamsWithPositionalIndex.getQueryParameters(), hasItems((Object) "tiger", "scott", 20));
    }
}
