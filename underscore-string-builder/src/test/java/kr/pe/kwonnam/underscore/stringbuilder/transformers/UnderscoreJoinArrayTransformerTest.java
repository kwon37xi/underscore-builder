package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import org.junit.Before;
import org.junit.Test;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.joinArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnderscoreJoinArrayTransformerTest extends AbstractTransformerTest {
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
        assertThat(underscoreStringBuilder.__((String[]) null, joinArray(",")).toString(), is("null"));
    }

    @Test
    public void transform_empty() throws Exception {
        assertThat(underscoreStringBuilder.__(new Object[]{}, joinArray(",")).toString(), is(""));
    }

    @Test
    public void transform_without_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(nullValues, joinArray(",")).toString(), is("null,nonnull,null"));
    }

    @Test
    public void transform_with_nullValue() throws Exception {
        assertThat(underscoreStringBuilder.__(nullValues, joinArray(",", "NULL")).toString(), is("NULL,nonnull,NULL"));
    }

    @Test
    public void transform_one() throws Exception {
        assertThat(underscoreStringBuilder.__(one, joinArray(",")).toString(), is("one"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(one, joinArray(" | ")).toString(), is("one\none"));
    }

    @Test
    public void transform_strings() throws Exception {
        assertThat(underscoreStringBuilder.__(strings, joinArray(",")).toString(), is("underscore,string,builder"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(strings, joinArray(" | ")).toString(), is("underscore,string,builder\nunderscore | string | builder"));
    }

    @Test
    public void transform_objects() throws Exception {
        assertThat(underscoreStringBuilder.__(objects, joinArray(",")).toString(), is("1,2,3.3,Hello,World"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(objects, joinArray(" | ")).toString(), is("1,2,3.3,Hello,World\n1 | 2 | 3.3 | Hello | World"));
    }

    @Test
    public void transform_separator_null() throws Exception {
        assertThat(underscoreStringBuilder.__(objects, joinArray(null)).toString(), is("123.3HelloWorld"));
        underscoreStringBuilder.__("\n");
        assertThat(underscoreStringBuilder.__(strings, joinArray(null)).toString(), is("123.3HelloWorld\nunderscorestringbuilder"));
    }
}
