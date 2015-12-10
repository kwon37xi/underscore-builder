package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.joinArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UnderscoreJoinIterableTransformerTest extends AbstractTransformerTest {
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
        assertThat(underscoreStringBuilder.__((List<String>) null, join(",")).toString(), is(""));
    }

    @Test
    public void transform_empty() throws Exception {
        assertThat(underscoreStringBuilder.__(Collections.emptyList(), join(",")).toString(), is(""));
    }

    @Test
    public void transform_without_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(nullValues, join(",")).toString(), is(",nonnull,"));
    }

    @Test
    public void transform_with_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(nullValues, join(",", "NULL")).toString(), is("NULL,nonnull,NULL"));
    }

    @Test
    public void transform_one() throws Exception {
        assertThat(underscoreStringBuilder.__(one, join(",")).toString(), is("one"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(one, join(" | ")).toString(), is("one\none"));
    }

    @Test
    public void transform_strings() throws Exception {
        assertThat(underscoreStringBuilder.__(strings, join(",")).toString(), is("underscore,string,builder"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(strings, join(" | ")).toString(), is("underscore,string,builder\nunderscore | string | builder"));
    }

    @Test
    public void transform_objects() throws Exception {
        assertThat(underscoreStringBuilder.__(objects, join(",")).toString(), is("1,2,3.3,Hello,World"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(objects, join(" | ")).toString(), is("1,2,3.3,Hello,World\n1 | 2 | 3.3 | Hello | World"));
    }

    @Test
    public void transform_separator_null() throws Exception {
        assertThat(underscoreStringBuilder.__(objects, join(null)).toString(), is("123.3HelloWorld"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(strings, join(null)).toString(), is("123.3HelloWorld\nunderscorestringbuilder"));
    }
}
