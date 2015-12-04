package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscoreFilter;
import kr.pe.kwonnam.underscore.UnderscorePredicate;
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
    public void __and_filter() throws Exception {
        underscoreStringBuilder.__(123, new UnderscoreFilter<UnderscoreStringBuilder, Integer>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Integer appended) {
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
    public void __and_appendable_false_and_filter() throws Exception {
        underscoreStringBuilder.__(false, new Date(), new UnderscoreFilter<UnderscoreStringBuilder, Date>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Date appended) {
                underscoreBuilder.__(appended);
            }
        });

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true_and_filter() throws Exception {
        underscoreStringBuilder.__(true, new Date(), new UnderscoreFilter<UnderscoreStringBuilder, Date>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Date appended) {
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
    public void __and_predicate_true_and_filter() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, -123, new UnderscoreFilter<UnderscoreStringBuilder, Integer>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Integer appended) {
                underscoreBuilder.__(Math.abs(appended));
            }
        });

        assertThat(underscoreStringBuilder.toString(), is("123"));
    }

    @Test
    public void __and_predicate_null_and_filter_null() throws Exception {
        try {
            underscoreStringBuilder.__(null, 123, null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("underscorePredicate must not be null."));
        }
    }
}
