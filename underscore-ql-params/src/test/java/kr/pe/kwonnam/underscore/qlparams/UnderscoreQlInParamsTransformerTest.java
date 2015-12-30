package kr.pe.kwonnam.underscore.qlparams;

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
    public void inParams_primitive_booleans() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new boolean[]{true, false}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(2));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) Boolean.TRUE, Boolean.FALSE));
    }

    @Test
    public void inParams_primitive_bytes() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new byte[]{1, 2, 3}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(3));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) (byte) 1, (byte) 2, (byte) 3));
    }

    @Test
    public void inParams_primitive_chars() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new char[]{'a', 'b', '가', '나'}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(4));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) 'a', 'b', '가', '나'));
    }

    @Test
    public void inParams_primitive_shorts() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new short[]{100, 101, 0, -101}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(4));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) (short) 100, (short) 101, (short) 0, (short) -101));
    }

    @Test
    public void inParams_primitive_ints() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new int[]{-500, 5000, 50000, 0}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(4));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) (-500), 5000, 50000, 0));
    }

    @Test
    public void inParams_primitive_longs() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new long[]{-500L, 5000L, Long.MAX_VALUE, 0L}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?, ?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(4));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) (-500L), 5000L, Long.MAX_VALUE, 0L));
    }

    @Test
    public void inParams_primitive_floats() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new float[]{3.14f, 109.1f}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(2));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) 3.14f, 109.1f));
    }

    @Test
    public void inParams_primitive_doubles() throws Exception {
        underscoreStringBuilder.__("some_value IN (%s)", underscoreQlParams.inParams(new double[]{3.14, 109.1}));

        assertThat(underscoreStringBuilder.toString(), is("some_value IN (?, ?)"));
        assertThat(underscoreQlParams.getQueryParameters(), hasSize(2));
        assertThat(underscoreQlParams.getQueryParameters(), hasItems((Object) 3.14, 109.1));
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
