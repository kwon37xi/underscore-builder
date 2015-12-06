package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscorePredicate;
import kr.pe.kwonnam.underscore.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.UnderscoreTransformer;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
        underscoreStringBuilder.__(123, new UnderscoreTransformer<UnderscoreStringBuilder, Integer>() {
            @Override
            public void transform(UnderscoreStringBuilder underscoreBuilder, Integer appended) {
                int i = appended * 2;
                underscoreBuilder.__(i);
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
        underscoreStringBuilder.__(false, new Date(), new UnderscoreTransformer<UnderscoreStringBuilder, Date>() {
            @Override
            public void transform(UnderscoreStringBuilder underscoreBuilder, Date appended) {
                underscoreBuilder.__(appended);
            }
        });

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true_and_transformer() throws Exception {
        underscoreStringBuilder.__(true, new Date(), new UnderscoreTransformer<UnderscoreStringBuilder, Date>() {
            @Override
            public void transform(UnderscoreStringBuilder underscoreBuilder, Date appended) {
                underscoreBuilder.__(appended);
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
        }, -123, new UnderscoreTransformer<UnderscoreStringBuilder, Integer>() {
            @Override
            public void transform(UnderscoreStringBuilder underscoreBuilder, Integer appended) {
                underscoreBuilder.__(Math.abs(appended));
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
    public void __and_subBuild() throws Exception {
        underscoreStringBuilder
            .__("-")
            .__(new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("*");
                }
            })
            .__("-");

        assertThat(underscoreStringBuilder.toString(), is("-*-"));
    }

    @Test
    public void __and_subBuild_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("-")
            .__(new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("*");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder, UnderscoreStringBuilder>() {
                @Override
                public void transform(UnderscoreStringBuilder underscoreBuilder, UnderscoreStringBuilder appended) {
                    for (int i = 0; i < 5; i++) {
                        underscoreBuilder.__(appended.toString());
                    }
                }
            })
            .__("-");

        assertThat(underscoreStringBuilder.toString(), is("-*****-"));
    }


    @Test
    public void __and_appendable_false_and_subBuild() throws Exception {
        underscoreStringBuilder
            .__("[")
            .__(false, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("]");

        assertThat(underscoreStringBuilder.toString(), is("[]"));
    }

    @Test
    public void __and_appendable_true_and_subBuild() throws Exception {
        underscoreStringBuilder
            .__("[")
            .__(true, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
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
    public void __and_appendable_false_and_subBuild_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("(")
            .__(false, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("world!");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder, UnderscoreStringBuilder>() {
                @Override
                public void transform(UnderscoreStringBuilder underscoreBuilder, UnderscoreStringBuilder appended) {
                    underscoreBuilder.__(appended.toString().toUpperCase());
                }
            })
            .__(")");

        assertThat(underscoreStringBuilder.toString(), is("()"));
    }

    @Test
    public void __and_appendable_true_and_subBuild_and_transformer() throws Exception {
        underscoreStringBuilder
            .__("(")
            .__(true, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("world!");
                }
            }, new UnderscoreTransformer<UnderscoreStringBuilder, UnderscoreStringBuilder>() {
                @Override
                public void transform(UnderscoreStringBuilder underscoreBuilder, UnderscoreStringBuilder appended) {
                    underscoreBuilder.__(appended.toString().toUpperCase());
                }
            })
            .__(")");

        assertThat(underscoreStringBuilder.toString(), is("(WORLD!)"));
    }

    @Test
    public void __and_appendable_true_and_subBuild_null() throws Exception {
        try {
            underscoreStringBuilder
                .__(true, (UnderscoreSubBuild<UnderscoreStringBuilder>) null, (UnderscoreTransformer<UnderscoreStringBuilder, UnderscoreStringBuilder>) null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("subBuild must not be null."));
        }
    }

    @Test
    public void __and_predicate_false_and_subBuild() throws Exception {
        underscoreStringBuilder
            .__("{")
            .__(new UnderscorePredicate() {
                @Override
                public boolean evaluate() {
                    return false;
                }
            }, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("}");
        assertThat(underscoreStringBuilder.toString(), is("{}"));
    }

    @Test
    public void __and_predicate_true_and_subBuild() throws Exception {
        underscoreStringBuilder
            .__("{")
            .__(new UnderscorePredicate() {
                @Override
                public boolean evaluate() {
                    return true;
                }
            }, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.__("Hello");
                }
            })
            .__("}");
        assertThat(underscoreStringBuilder.toString(), is("{Hello}"));
    }

    @Test
    public void __and_predicate_null_and_subBuild() throws Exception {
        try {
            underscoreStringBuilder
                .__("{")
                .__(null, new UnderscoreSubBuild<UnderscoreStringBuilder>() {
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
}
