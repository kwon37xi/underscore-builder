package kr.pe.kwonnam.underscore.stringbuilder;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder.LINE_SEPARATOR;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UnderscoreStringBuilderTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreStringBuilderTest.class);

    private UnderscoreStringBuilder underscoreStringBuilder;

    @Before
    public void setUp() throws Exception {
        underscoreStringBuilder = new UnderscoreStringBuilder();
    }

    @Test
    public void constructor_null() throws Exception {
        try {
            new UnderscoreStringBuilder(null);
            fail("Must throw an exception - NullPointerException");
        } catch (NullPointerException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), is("stringBuilder must not be null."));
        }
    }

    @Test
    public void append_char() throws Exception {
        underscoreStringBuilder
            .append('a')
            .append('b')
            .append('C')
            .append('D');

        assertThat(underscoreStringBuilder.toString(), is("abCD"));
    }

    @Test
    public void append_charSequence() throws Exception {
        underscoreStringBuilder
            .append("Hello ")
            .append(new StringBuilder("World"));

        assertThat(underscoreStringBuilder.toString(), is("Hello World"));
    }

    @Test
    public void append_charSequence_start_end() throws Exception {
        underscoreStringBuilder.append("Hello World!", 4, 7);

        assertThat(underscoreStringBuilder.toString(), is("o W"));
    }

    @Test
    public void __() throws Exception {
        underscoreStringBuilder
            .__("hello! ")
            .__(1)
            .__(true)
            .__(10.99);

        assertThat(underscoreStringBuilder.toString(), is("hello! 1true10.99"));
    }

    @Test
    public void __and_transformer() throws Exception {
        underscoreStringBuilder.__(123, new UnderscoreTransformer<Integer>() {
            @Override
            public String transform(Integer appendee) {
                int i = appendee * 2;
                return Integer.toString(i);
            }
        });

        assertThat(underscoreStringBuilder.toString(), is("246"));
    }

    @Test
    public void __and_appendable_false() throws Exception {
        underscoreStringBuilder.__(false, 123);

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true() throws Exception {
        underscoreStringBuilder.__(true, 123.456);

        assertThat(underscoreStringBuilder.toString(), is("123.456"));
    }

    @Test
    public void __and_appendable_false_and_transformer() throws Exception {
        underscoreStringBuilder.__(false, new Date(), new UnderscoreTransformer<Date>() {
            @Override
            public CharSequence transform(Date appendee) {
                return String.valueOf(appendee);
            }
        });

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true_and_transformer() throws Exception {
        underscoreStringBuilder.__(true, new Date(), new UnderscoreTransformer<Date>() {
            @Override
            public CharSequence transform(Date appendee) {
                return String.valueOf(appendee);
            }
        });

        assertThat(underscoreStringBuilder.toString(), not(isEmptyString()));
    }

    @Test
    public void __and_predicate_false() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, 123456.789);

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_predicate_true() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, 123456.789);

        assertThat(underscoreStringBuilder.toString(), is("123456.789"));
    }

    @Test
    public void __and_predicate_true_and_transformer() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, -123, new UnderscoreTransformer<Integer>() {
            @Override
            public CharSequence transform(Integer appendee) {
                return String.valueOf(Math.abs(appendee));
            }
        });

        assertThat(underscoreStringBuilder.toString(), is("123"));
    }

    @Test
    public void __and_predicate_null_and_transformer_null() throws Exception {
        try {
            underscoreStringBuilder.__(null, 123, null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("underscorePredicate must not be null."));
        }
    }

    @Test
    public void sub() throws Exception {
        underscoreStringBuilder
            .__("-")
            .sub(new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("*");
                }
            })
            .__("-");

        assertThat(underscoreStringBuilder.toString(), is("-*-"));
    }

    @Test
    public void sub_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("-")
            .sub(new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("*");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder>() {
                @Override
                public CharSequence transform(UnderscoreStringBuilder appendee) {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < 5; i++) {
                        stringBuilder.append(appendee.toString());
                    }
                    return stringBuilder;
                }
            })
            .__("-");

        assertThat(underscoreStringBuilder.toString(), is("-*****-"));
    }


    @Test
    public void sub_and_appendable_false() throws Exception {
        underscoreStringBuilder
            .__("[")
            .sub(false, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("]");

        assertThat(underscoreStringBuilder.toString(), is("[]"));
    }

    @Test
    public void sub_and_appendable_true() throws Exception {
        underscoreStringBuilder
            .__("[")
            .sub(true, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder
                        .__(123)
                        .__("Hello")
                        .__("|");
                }
            })
            .__("]");

        assertThat(underscoreStringBuilder.toString(), is("[123Hello|]"));
    }

    @Test
    public void sub_and_appendable_false_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("(")
            .sub(false, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("world!");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder>() {
                @Override
                public CharSequence transform(UnderscoreStringBuilder appendee) {
                    return appendee.toString().toUpperCase();
                }
            })
            .__(")");

        assertThat(underscoreStringBuilder.toString(), is("()"));
    }

    @Test
    public void sub_and_appendable_true_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("(")
            .sub(true, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("world!");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder>() {
                @Override
                public CharSequence transform(UnderscoreStringBuilder appendee) {
                    return appendee.toString().toUpperCase();
                }
            })
            .__(")");

        assertThat(underscoreStringBuilder.toString(), is("(WORLD!)"));
    }

    @Test
    public void sub_and_appendable_true_null() throws Exception {
        try {
            underscoreStringBuilder
                .sub(true, null, null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("subBuild must not be null."));
        }
    }

    @Test
    public void sub_and_predicate_false() throws Exception {
        underscoreStringBuilder
            .__("{")
            .sub(new UnderscorePredicate() {
                @Override
                public boolean evaluate() {
                    return false;
                }
            }, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("}");
        assertThat(underscoreStringBuilder.toString(), is("{}"));
    }

    @Test
    public void sub_and_predicate_true() throws Exception {
        underscoreStringBuilder
            .__("{")
            .sub(new UnderscorePredicate() {
                @Override
                public boolean evaluate() {
                    return true;
                }
            }, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("}");
        assertThat(underscoreStringBuilder.toString(), is("{Hello}"));
    }

    @Test
    public void sub_and_predicate_null() throws Exception {
        try {
            underscoreStringBuilder
                .__("{")
                .sub(null, new UnderscoreSubBuild() {
                    @Override
                    public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                        underscoreSubBuilder.__("Hello");
                    }
                })
                .__("}");
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("underscorePredicate must not be null."));
        }
    }

    @Test
    public void length() throws Exception {
        underscoreStringBuilder.__("hello");
        assertThat(underscoreStringBuilder.length(), is(5));

        underscoreStringBuilder.__("12345");
        assertThat(underscoreStringBuilder.length(), is(10));
    }

    @Test
    public void charAt() throws Exception {
        underscoreStringBuilder.__("hello");
        assertThat(underscoreStringBuilder.charAt(0), is('h'));
        assertThat(underscoreStringBuilder.charAt(4), is('o'));
    }

    @Test
    public void subSequence() throws Exception {
        underscoreStringBuilder.__("hello world");

        assertThat(underscoreStringBuilder.subSequence(0, 5), is((CharSequence) "hello"));
        assertThat(underscoreStringBuilder.subSequence(6, 11), is((CharSequence) "world"));
    }

    @Test
    public void prefix() throws Exception {
        underscoreStringBuilder.__("start=>");
        assertThat(underscoreStringBuilder.toString(), is("start=>"));

        underscoreStringBuilder.prefix("-")
            .__("p");
        assertThat(underscoreStringBuilder.toString(), is("start=>-p"));

        underscoreStringBuilder.__(false, "not appear");
        assertThat(underscoreStringBuilder.toString(), is("start=>-p"));

        underscoreStringBuilder.__(true, "not appear", new UnderscoreTransformer<String>() {
            @Override
            public CharSequence transform(String appendee) {
                return "transformed";
            }
        });
        assertThat(underscoreStringBuilder.toString(), is("start=>-p-transformed"));

        underscoreStringBuilder.sub(new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("sub")
                    .__(":anothersub");
            }
        });
        assertThat(underscoreStringBuilder.toString(), is("start=>-p-transformed-sub:anothersub"));

        underscoreStringBuilder.prefixNewLine()
            .__("after new line");
        assertThat(underscoreStringBuilder.toString(), is("start=>-p-transformed-sub:anothersub" + LINE_SEPARATOR + "after new line"));

        underscoreStringBuilder.prefixOff()
            .__(":after off");
        assertThat(underscoreStringBuilder.toString(), is("start=>-p-transformed-sub:anothersub" + LINE_SEPARATOR + "after new line:after off"));
    }

    @Test
    public void suffix() throws Exception {
        underscoreStringBuilder.__("start=>");
        assertThat(underscoreStringBuilder.toString(), is("start=>"));

        underscoreStringBuilder.suffix("!")
            .__("s");
        assertThat(underscoreStringBuilder.toString(), is("start=>s!"));

        underscoreStringBuilder.__(false, "not appear");
        assertThat(underscoreStringBuilder.toString(), is("start=>s!"));

        underscoreStringBuilder.__(true, "not appear", new UnderscoreTransformer<String>() {
            @Override
            public CharSequence transform(String appendee) {
                return "transformed";
            }
        });
        assertThat(underscoreStringBuilder.toString(), is("start=>s!transformed!"));

        underscoreStringBuilder.sub(new UnderscoreSubBuild() {
            @Override
            public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                underscoreSubBuilder.__("sub")
                    .__(":anothersub");
            }
        });
        assertThat(underscoreStringBuilder.toString(), is("start=>s!transformed!sub:anothersub!"));

        underscoreStringBuilder.suffixNewLine()
            .__("after new line");
        assertThat(underscoreStringBuilder.toString(), is("start=>s!transformed!sub:anothersub!after new line" + LINE_SEPARATOR));

        underscoreStringBuilder.suffixOff()
            .__(":after off");
        assertThat(underscoreStringBuilder.toString(), is("start=>s!transformed!sub:anothersub!after new line" + LINE_SEPARATOR + ":after off"));
    }

    @Test
    public void prefix_and_suffix() throws Exception {
        underscoreStringBuilder
            .__("Start => ")
            .prefix("[").suffix("]")
            .__("Hello")
            .prefixOff().suffixOff()
            .__(" <= End");
        assertThat(underscoreStringBuilder.toString(), is("Start => [Hello] <= End"));
    }

    @Test
    public void __and_extraTransformers() throws Exception {
        underscoreStringBuilder
            .__(", ", join(new Object[]{1, 2, 3}), wrap(" \n  [", "]   \t"), trim());

        assertThat(underscoreStringBuilder.toString(), is("[1, 2, 3]"));
    }

    @Test
    public void __and_extraTransformers_null() throws Exception {
        underscoreStringBuilder
            .__(", ", join(new Object[]{1, 2, 3}), (UnderscoreTransformer<? super CharSequence>[]) null);

        assertThat(underscoreStringBuilder.toString(), is("1, 2, 3"));
    }

    @Test
    public void sub_and_extraTransformers() throws Exception {
        underscoreStringBuilder
            .sub(new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder
                        .__("Hello ")
                        .__("World!");
                }
            }, multiply(3, " | "), wrap(" \r\n  --+ ", " +--  \t\r"), trim());
        assertThat(underscoreStringBuilder.toString(), is("--+ Hello World! | Hello World! | Hello World! +--"));
    }

    @Test
    public void __and_extraTransformers_prefix_and_suffix() throws Exception {
        underscoreStringBuilder
            .prefix(" [").suffix("] ")
            .__(", ", join(new Object[]{1, 2, 3}), wrap(" \n  (", ")   \t"), trim())
            .__("=", multiply(5, "-"), wrap("--+ ", " +--"));

        assertThat(underscoreStringBuilder.toString(), is(" [(1, 2, 3)]  [--+ =-=-=-=-= +--] "));
    }
}
