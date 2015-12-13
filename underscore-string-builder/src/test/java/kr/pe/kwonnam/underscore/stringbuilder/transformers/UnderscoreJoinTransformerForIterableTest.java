package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UnderscoreJoinTransformerForIterableTest extends AbstractTransformerTest {
    private List<String> nullValues;

    private List<String> one;

    private Collection<String> strings;

    private List<Object> objects;

    @Before
    public void setUp() throws Exception {
        nullValues = new ArrayList<String>();
        nullValues.add(null);
        nullValues.add("nonnull");
        nullValues.add(null);

        one = new ArrayList<String>();
        one.add("one");

        strings = new ArrayList<String>();
        strings.add("underscore");
        strings.add("string");
        strings.add("builder");

        objects = new ArrayList<Object>();
        objects.add(1);
        objects.add(2L);
        objects.add(3.3);
        objects.add("Hello");
        objects.add("World");
    }

    @Test
    public void transform_null() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join((List<String>) null)).toString(), is(""));
    }

    @Test
    public void transform_empty() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(Collections.emptyList())).toString(), is(""));
    }

    @Test
    public void transform_without_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(nullValues)).toString(), is(",nonnull,"));
    }

    @Test
    public void transform_with_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(nullValues, "NULL")).toString(), is("NULL,nonnull,NULL"));
    }

    @Test
    public void transform_one() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(one)).toString(), is("one"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", join(one)).toString(), is("one\none"));
    }

    @Test
    public void transform_strings() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(strings)).toString(), is("underscore,string,builder"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", join(strings)).toString(), is("underscore,string,builder\nunderscore | string | builder"));
    }

    @Test
    public void transform_objects() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(objects)).toString(), is("1,2,3.3,Hello,World"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", join(objects)).toString(), is("1,2,3.3,Hello,World\n1 | 2 | 3.3 | Hello | World"));
    }

    @Test
    public void transform_separator_null() throws Exception {
        assertThat(underscoreStringBuilder.__((String) null, join(objects)).toString(), is("123.3HelloWorld"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__((String) null, join(strings)).toString(), is("123.3HelloWorld\nunderscorestringbuilder"));
    }
}
