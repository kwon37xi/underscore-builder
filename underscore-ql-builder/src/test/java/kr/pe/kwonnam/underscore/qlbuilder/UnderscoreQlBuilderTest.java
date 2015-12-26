package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscorePredicate;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.Arrays;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.*;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.multiply;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UnderscoreQlBuilderTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreQlBuilderTest.class);

    private UnderscoreQlBuilder underscoreQlBuilder;
    private UnderscoreQlBuilder underscoreIndexedQlBuilder;

    @Mock
    private PreparedStatement preparedStatement;

    @Before
    public void setUp() throws Exception {
        underscoreQlBuilder = new UnderscoreQlBuilder();
        underscoreQlBuilder.setUnderscoreStringBuilder(new UnderscoreStringBuilder());
        underscoreQlBuilder.setUnderscoreQlParams(new UnderscoreQlParams(false));

        underscoreIndexedQlBuilder = new UnderscoreQlBuilder();
        underscoreIndexedQlBuilder.setUnderscoreQlParams(UnderscoreQlParams.withPositionalIndex());
    }

    @Test
    public void isWithPositionalIndex() throws Exception {
        assertThat(underscoreQlBuilder.isWithPositionalIndex(), is(false));
        assertThat(underscoreIndexedQlBuilder.isWithPositionalIndex(), is(true));
    }

    @Test
    public void __() throws Exception {
        underscoreQlBuilder.__("Hello");
        assertThat(underscoreQlBuilder.toString(), is("Hello"));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasSize(0));
    }

    @Test
    public void __and_appendable() throws Exception {
        underscoreQlBuilder.__(true, "Hello");
        underscoreQlBuilder.__(false, "World");

        assertThat(underscoreQlBuilder.toString(), is("Hello"));
    }

    @Test
    public void __and_transformers() throws Exception {
        underscoreQlBuilder.__("@", multiply(3), wrap("[", "]"));
        assertThat(underscoreQlBuilder.toString(), is("[@@@]"));
    }

    @Test
    public void __and_appendable_and_transformers() throws Exception {
        underscoreQlBuilder.__(false, "@", multiply(3), wrap("[", "]"));
        underscoreQlBuilder.__(true, "#", multiply(3), wrap("[", "]"));
        assertThat(underscoreQlBuilder.toString(), is("[###]"));
    }

    @Test
    public void __and_predicate() throws Exception {
        underscoreQlBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, "Hello");
        underscoreQlBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, "World");

        assertThat(underscoreQlBuilder.toString(), is("World"));
    }

    @Test
    public void __and_predicate_and_transformers() throws Exception {
        underscoreQlBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, "@", multiply(3), wrap("[", "]"));
        underscoreQlBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, "#", multiply(3), wrap("[", "]"));
        assertThat(underscoreQlBuilder.toString(), is("[@@@]"));
    }

    @Test
    public void sub() throws Exception {
        underscoreQlBuilder.sub(new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("Sub build!");
            }
        });

        assertThat(underscoreQlBuilder.toString(), is("Sub build!"));
    }

    @Test
    public void sub_and_transformers() throws Exception {
        underscoreQlBuilder.sub(new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("  \t Sub build!  \t ");
            }
        }, UnderscoreStringBuilderTransformers.trim(), wrap("{", "}"));
        assertThat(underscoreQlBuilder.toString(), is("{Sub build!}"));
    }

    @Test
    public void sub_and_appendable() throws Exception {
        underscoreQlBuilder.sub(true, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("First sub build!");
            }
        });
        underscoreQlBuilder.sub(false, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("Second sub build!");
            }
        });

        assertThat(underscoreQlBuilder.toString(), is("First sub build!"));
    }

    @Test
    public void sub_and_appendable_and_transformers() throws Exception {
        underscoreQlBuilder.sub(true, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("First sub build!");
            }
        }, multiply(2), wrap("[", "]"));
        underscoreQlBuilder.sub(false, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("Second sub build!");
            }
        }, multiply(2), wrap("[", "]"));

        assertThat(underscoreQlBuilder.toString(), is("[First sub build!First sub build!]"));
    }

    @Test
    public void sub_and_predicate() throws Exception {
        underscoreQlBuilder.sub(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("First sub build!");
            }
        });
        underscoreQlBuilder.sub(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("Second sub build!");
            }
        });

        assertThat(underscoreQlBuilder.toString(), is("Second sub build!"));
    }

    @Test
    public void sub_and_predicate_and_transformers() throws Exception {
        underscoreQlBuilder.sub(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("First sub build!");
            }
        }, multiply(2), wrap("{", "}"));
        underscoreQlBuilder.sub(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("Second sub build!");
            }
        }, multiply(2), wrap("{", "}"));

        assertThat(underscoreQlBuilder.toString(), is("{Second sub build!Second sub build!}"));
    }

    @Test
    public void prefix_prefixOff() throws Exception {
        underscoreQlBuilder.__("Hello");
        underscoreQlBuilder.prefixNewLine();
        underscoreQlBuilder.__("World");
        underscoreQlBuilder.prefix("-");
        underscoreQlBuilder.__("and ");
        underscoreQlBuilder.prefixOff();
        underscoreQlBuilder.__("everyone!");

        assertThat(underscoreQlBuilder.toString(), is("Hello" + UnderscoreStringBuilder.LINE_SEPARATOR + "World-and everyone!"));
    }

    @Test
    public void suffix_suffixOff() throws Exception {
        underscoreQlBuilder.__("Hello");
        underscoreQlBuilder.suffixNewLine();
        underscoreQlBuilder.__("World");
        underscoreQlBuilder.suffix("-");
        underscoreQlBuilder.__("and ");
        underscoreQlBuilder.suffixOff();
        underscoreQlBuilder.__("everyone!");

        assertThat(underscoreQlBuilder.toString(), is("HelloWorld" + UnderscoreStringBuilder.LINE_SEPARATOR + "and -everyone!"));
    }

    @Test
    public void length() throws Exception {
        assertThat(underscoreQlBuilder.length(), is(0));
        underscoreQlBuilder.__("Hello");
        assertThat(underscoreQlBuilder.length(), is(5));
    }

    @Test
    public void charAt() throws Exception {
        underscoreQlBuilder.__("hello world");
        assertThat(underscoreQlBuilder.charAt(0), is('h'));
        assertThat(underscoreQlBuilder.charAt(5), is(' '));
        assertThat(underscoreQlBuilder.charAt(10), is('d'));
    }

    @Test
    public void subSequence() throws Exception {
        underscoreQlBuilder.__("hello world");

        assertThat(underscoreQlBuilder.subSequence(0, 5), is((CharSequence) "hello"));
        assertThat(underscoreQlBuilder.subSequence(6, 11), is((CharSequence) "world"));
    }

    @Test
    public void append_CharSequence() throws Exception {
        underscoreQlBuilder.append("Hello");
        underscoreQlBuilder.append(" ");
        underscoreQlBuilder.append("World");

        assertThat(underscoreQlBuilder.toString(), is("Hello World"));
    }

    @Test
    public void append_CharSequence_start_end() throws Exception {
        underscoreQlBuilder.append("Hi~ Hello World! EveryOne!", 4, 16);
        assertThat(underscoreQlBuilder.toString(), is("Hello World!"));
    }

    @Test
    public void append() throws Exception {
        underscoreQlBuilder.append('a');
        underscoreQlBuilder.append('b');
        underscoreQlBuilder.append('c');

        assertThat(underscoreQlBuilder.toString(), is("abc"));
    }

    @Test
    public void params_and_bindParameters() throws Exception {
        underscoreQlBuilder.__("user_name = %s and user_type = %s", underscoreQlBuilder.params("ql", "string"));
        assertThat(underscoreQlBuilder.toString(), is("user_name = ? and user_type = ?"));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasSize(2));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasItems((Object) "ql", "string"));
        assertThat(Arrays.asList(underscoreQlBuilder.getQueryParameterArray()), hasItems((Object) "ql", "string"));

        underscoreQlBuilder.bindParameters(preparedStatement);
        verify(preparedStatement).setObject(1, "ql");
        verify(preparedStatement).setObject(2, "string");
    }

    @Test
    public void params_indexed_and_bindParameters() throws Exception {
        underscoreIndexedQlBuilder.__("user_name = %s and user_type = %s", underscoreIndexedQlBuilder.params("ql", "string"));
        assertThat(underscoreIndexedQlBuilder.toString(), is("user_name = ?1 and user_type = ?2"));
        assertThat(underscoreIndexedQlBuilder.getQueryParameters(), hasSize(2));
        assertThat(underscoreIndexedQlBuilder.getQueryParameters(), hasItems((Object) "ql", "string"));
        assertThat(Arrays.asList(underscoreIndexedQlBuilder.getQueryParameterArray()), hasItems((Object) "ql", "string"));

        underscoreIndexedQlBuilder.bindParameters(preparedStatement);
        verify(preparedStatement).setObject(1, "ql");
        verify(preparedStatement).setObject(2, "string");
    }

    @Test
    public void inParams_object_list() throws Exception {
        underscoreQlBuilder.__("user_name IN (%s) ", underscoreQlBuilder.inParams(Arrays.asList("hello", "world")));
        underscoreQlBuilder.__("user_type IN (%s)", underscoreQlBuilder.inParams(Arrays.asList(1, 5, 8)));

        assertThat(underscoreQlBuilder.toString(), is("user_name IN (?, ?) user_type IN (?, ?, ?)"));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasSize(5));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasItems((Object) "hello", "world", 1, 5, 8));
    }

    @Test
    public void inParams_object_list_indexed() throws Exception {
        underscoreIndexedQlBuilder.__("user_name IN (%s) ", underscoreIndexedQlBuilder.inParams(Arrays.asList("hello", "world")));
        underscoreIndexedQlBuilder.__("user_type IN (%s)", underscoreIndexedQlBuilder.inParams(Arrays.asList(1, 5, 8)));

        assertThat(underscoreIndexedQlBuilder.toString(), is("user_name IN (?1, ?2) user_type IN (?3, ?4, ?5)"));
        assertThat(underscoreIndexedQlBuilder.getQueryParameters(), hasSize(5));
        assertThat(underscoreIndexedQlBuilder.getQueryParameters(), hasItems((Object) "hello", "world", 1, 5, 8));
    }


    @Test
    public void inParams_array() throws Exception {
        underscoreQlBuilder.__("user_name IN (%s) ", underscoreQlBuilder.inParams(new String[]{"hello", "world"}));
        underscoreQlBuilder.__("user_type IN (%s)", underscoreQlBuilder.inParams(new Integer[] {1, 5, 8}));

        assertThat(underscoreQlBuilder.toString(), is("user_name IN (?, ?) user_type IN (?, ?, ?)"));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasSize(5));
        assertThat(underscoreQlBuilder.getQueryParameters(), hasItems((Object) "hello", "world", 1, 5, 8));
    }
}
