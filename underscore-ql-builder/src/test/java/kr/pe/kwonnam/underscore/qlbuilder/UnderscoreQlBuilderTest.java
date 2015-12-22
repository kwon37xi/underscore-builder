package kr.pe.kwonnam.underscore.qlbuilder;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class UnderscoreQlBuilderTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlBuilderTest.class);

    private UnderscoreQlBuilder underscoreQlBuilder;

    @Before
    public void setUp() throws Exception {
        underscoreQlBuilder = new UnderscoreQlBuilder();
    }

    @Test
    public void not() throws Exception {
        assertThat(underscoreQlBuilder.not(true), is(false));
        assertThat(underscoreQlBuilder.not(false), is(true));
    }

    @Test
    public void isNull() throws Exception {
        assertThat(underscoreQlBuilder.isNull(null), is(true));

        assertThat(underscoreQlBuilder.isNull(""), is(false));
        assertThat(underscoreQlBuilder.isNull(new ArrayList<Object>()), is(false));
        assertThat(underscoreQlBuilder.isNull(new Object[]{}), is(false));
    }

    @Test
    public void isEmpty_String() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((String) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(""), is(true));

        assertThat(underscoreQlBuilder.isEmpty("String"), is(false));
        assertThat(underscoreQlBuilder.isEmpty("  "), is(false));
        assertThat(underscoreQlBuilder.isEmpty("\t\r\n"), is(false));
    }

    @Test
    public void isEmpty_collection() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((Collection<Object>) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new ArrayList<Object>()), is(true));

        assertThat(underscoreQlBuilder.isEmpty(Arrays.asList("Hello")), is(false));
        assertThat(underscoreQlBuilder.isEmpty(new HashSet<String>(Arrays.asList("Hello"))), is(false));
    }

    @Test
    public void isEmpty_map() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((Map<String, Object>) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new HashMap<Object, Object>()), is(true));

        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("greeting", "hello");
        assertThat(underscoreQlBuilder.isEmpty(map), is(false));
    }

    @Test
    public void isEmpty_object_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((String[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty((Integer[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new String[]{}), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new Integer[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new String[]{"hello"}), is(false));
        assertThat(underscoreQlBuilder.isEmpty(new Integer[]{1}), is(false));
    }

    @Test
    public void isEmpty_boolean_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((boolean[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new boolean[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new boolean[]{true}), is(false));
    }

    @Test
    public void isEmpty_byte_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((byte[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new byte[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new byte[]{1}), is(false));
    }

    @Test
    public void isEmpty_char_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((char[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new char[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new char[]{'a'}), is(false));
    }

    @Test
    public void isEmpty_short_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((short[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new short[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new short[]{127}), is(false));
    }

    @Test
    public void isEmpty_int_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((int[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new int[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new int[]{Integer.MAX_VALUE}), is(false));
    }

    @Test
    public void isEmpty_long_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((long[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new long[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new long[]{Long.MAX_VALUE}), is(false));
    }

    @Test
    public void isEmpty_float_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((float[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new float[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new float[]{Float.MAX_VALUE}), is(false));
    }

    @Test
    public void isEmpty_double_array() throws Exception {
        assertThat(underscoreQlBuilder.isEmpty((double[]) null), is(true));
        assertThat(underscoreQlBuilder.isEmpty(new double[]{}), is(true));

        assertThat(underscoreQlBuilder.isEmpty(new double[]{Double.MAX_VALUE}), is(false));
    }

    @Test
    public void isBlank() throws Exception {
        assertThat(underscoreQlBuilder.isBlank(null), is(true));
        assertThat(underscoreQlBuilder.isBlank(""), is(true));
        assertThat(underscoreQlBuilder.isBlank("  "), is(true));
        assertThat(underscoreQlBuilder.isBlank("  \r \n \t  "), is(true));

        assertThat(underscoreQlBuilder.isBlank("1"), is(false));
        assertThat(underscoreQlBuilder.isBlank("  a  "), is(false));
    }

    @Test
    public void contains_String() throws Exception {
        assertThat(underscoreQlBuilder.contains((String) null, "hello"), is(false));
        assertThat(underscoreQlBuilder.contains("", "hello"), is(false));
        assertThat(underscoreQlBuilder.contains("world", "hello"), is(false));

        assertThat(underscoreQlBuilder.contains("world", "or"), is(true));
        assertThat(underscoreQlBuilder.contains("world", "w"), is(true));
        assertThat(underscoreQlBuilder.contains("world", "d"), is(true));
    }

    @Test
    public void contains_collection() throws Exception {
        assertThat(underscoreQlBuilder.contains((Collection<?>) null, "object"), is(false));
        assertThat(underscoreQlBuilder.contains(Collections.emptyList(), "object"), is(false));
        assertThat(underscoreQlBuilder.contains(Arrays.asList("Hello", "World", "Good", "Morning"), "Afternoon"), is(false));

        assertThat(underscoreQlBuilder.contains(Arrays.asList("Hello", "World", "Good", "Morning"), "World"), is(true));
        assertThat(underscoreQlBuilder.contains(Arrays.asList("Hello", "World", "Good", "Morning"), "Morning"), is(true));

    }
}
