package kr.pe.kwonnam.underscore.qlbuilder;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class UnderscoreQlParamsTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlParamsTest.class);

    private UnderscoreQlParams underscoreQlParams;

    @Before
    public void setUp() throws Exception {
        underscoreQlParams = new UnderscoreQlParams();
    }

    @Test
    public void constructor_default() throws Exception {
        assertThat(underscoreQlParams.isWithPositionalIndex(), is(false));
        assertThat(underscoreQlParams.getQueryParameters().size(), is(0));
        assertThat(underscoreQlParams.getQueryParameterArray().length, is(0));
    }

    @Test
    public void constructor_withPositionalIndex() throws Exception {
        UnderscoreQlParams underscoreQlParams = new UnderscoreQlParams(true);
        assertThat(underscoreQlParams.isWithPositionalIndex(), is(true));
        assertThat(underscoreQlParams.getQueryParameters().size(), is(0));
        assertThat(underscoreQlParams.getQueryParameterArray().length, is(0));
    }

    @Test
    public void withPositionalIndex() throws Exception {
        UnderscoreQlParams underscoreQlParams = UnderscoreQlParams.withPositionalIndex();
        assertThat(underscoreQlParams.isWithPositionalIndex(), is(true));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(0));
        assertThat(underscoreQlParams.getQueryParameterArray().length, is(0));
    }

    @Test
    public void addParam() throws Exception {
        underscoreQlParams.addParam("contents");
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(1));
        assertThat(underscoreQlParams.getQueryParameters(), hasItem("contents"));
        assertThat(underscoreQlParams.getQueryParameterArray().length, is(1));
        assertThat(underscoreQlParams.getQueryParameterArray(), hasItemInArray((Object) "contents"));
        assertThat(underscoreQlParams.getCurrentPositionalIndex(), is(1));

        underscoreQlParams.addParam(null);
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(2));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) "contents", null));
        assertThat(underscoreQlParams.getQueryParameterArray().length, is(2));
        assertThat(underscoreQlParams.getQueryParameterArray()[0], is((Object) "contents"));
        assertThat(underscoreQlParams.getQueryParameterArray()[1], nullValue());
        assertThat(underscoreQlParams.getCurrentPositionalIndex(), is(2));

        underscoreQlParams.addParam(1234567);
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) "contents", null, 1234567));
        assertThat(underscoreQlParams.getCurrentPositionalIndex(), is(3));
    }

    @Test
    public void bindParameters_PreparedStatement_null() throws Exception {
        try {
            underscoreQlParams.bindParameters(null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("preparedStatement must not be null."));
        }

    }

    @Test
    public void bindParameters_no_params() throws Exception {
        PreparedStatement pstmt = mock(PreparedStatement.class);
        underscoreQlParams.bindParameters(pstmt);

        verifyZeroInteractions(pstmt);
    }

    @Test
    public void bindParameters() throws Exception {
        PreparedStatement pstmt = mock(PreparedStatement.class);

        final Date now = new Date();
        underscoreQlParams.addParam("Hello");
        underscoreQlParams.addParam("World");
        underscoreQlParams.addParam(now);
        underscoreQlParams.addParam(12345);
        underscoreQlParams.addParam(3.141592);

        underscoreQlParams.bindParameters(pstmt);

        verify(pstmt).setObject(1, "Hello");
        verify(pstmt).setObject(2, "World");
        verify(pstmt).setObject(3, now);
        verify(pstmt).setObject(4, 12345);
        verify(pstmt).setObject(5, 3.141592);
    }
}
