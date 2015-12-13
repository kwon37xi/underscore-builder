package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers;
import org.junit.Before;
import org.junit.Test;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnderscoreJoinTransformerForArrayTest extends AbstractTransformerTest {
    private String[] nullValues;

    private String[] one;

    private String[] strings;

    private Object[] objects;

    @Before
    public void setUp() throws Exception {
        nullValues = new String[]{null, "nonnull", null};

        one = new String[]{"one"};

        strings = new String[]{"underscore", "string", "builder"};

        objects = new Object[]{1, 2L, 3.3, "Hello", "World"};
    }

    @Test
    public void transform_null() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join((Object[]) null)).toString(), is(""));
    }

    @Test
    public void transform_empty() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join(new Object[]{})).toString(), is(""));
    }

    @Test
    public void transform_without_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join(nullValues)).toString(), is(",nonnull,"));
    }

    @Test
    public void transform_with_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(",", join(nullValues, "NULL")).toString(), is("NULL,nonnull,NULL"));
    }

    @Test
    public void transform_one() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join(one)).toString(), is("one"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", UnderscoreStringBuilderTransformers.join(one)).toString(), is("one\none"));
    }

    @Test
    public void transform_strings() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join(strings)).toString(), is("underscore,string,builder"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", UnderscoreStringBuilderTransformers.join(strings)).toString(), is("underscore,string,builder\nunderscore | string | builder"));
    }

    @Test
    public void transform_objects() throws Exception {
        assertThat(underscoreStringBuilder.__(",", UnderscoreStringBuilderTransformers.join(objects)).toString(), is("1,2,3.3,Hello,World"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(" | ", UnderscoreStringBuilderTransformers.join(objects)).toString(), is("1,2,3.3,Hello,World\n1 | 2 | 3.3 | Hello | World"));
    }

    @Test
    public void transform_separator_null() throws Exception {
        assertThat(underscoreStringBuilder.__((CharSequence) null, UnderscoreStringBuilderTransformers.join(objects)).toString(), is("123.3HelloWorld"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__((CharSequence) null, UnderscoreStringBuilderTransformers.join(strings)).toString(), is("123.3HelloWorld\nunderscorestringbuilder"));
    }
}
