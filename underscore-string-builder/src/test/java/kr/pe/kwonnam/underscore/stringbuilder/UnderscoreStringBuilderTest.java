package kr.pe.kwonnam.underscore.stringbuilder;

import kr.pe.kwonnam.underscore.UnderscoreBuilder;
import kr.pe.kwonnam.underscore.UnderscoreFilter;
import kr.pe.kwonnam.underscore.UnderscorePredicate;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class UnderscoreStringBuilderTest {
    private Logger log = LoggerFactory.getLogger(UnderscoreStringBuilderTest.class);

    private UnderscoreStringBuilder underscoreStringBuilder;

    @Before
    public void setUp() throws Exception {
        underscoreStringBuilder = new UnderscoreStringBuilder();
    }

    @Test
    public void __and_Object() throws Exception {
        underscoreStringBuilder.__("Hello~")
            .__(1)
            .__(true)
            .__(10.99);

        assertThat(underscoreStringBuilder.toString(), is("Hello~1true10.99"));
    }

    @Test
    public void __and_Objec_filter() throws Exception {
        underscoreStringBuilder.__(123, new UnderscoreFilter<UnderscoreStringBuilder>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Object appended) {
                int i = ((Integer) appended) * 2;
                underscoreBuilder.__(i);
            }
        });

        assertThat(underscoreStringBuilder.toString(), is("246"));
    }

    @Test
    public void __and_appendable_false_and_object() throws Exception {
        underscoreStringBuilder.__(false, 123);

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true_and_object() throws Exception {
        underscoreStringBuilder.__(true, 123.456);

        assertThat(underscoreStringBuilder.toString(), is("123.456"));
    }

    @Test
    public void __and_appendable_false_and_object_and_filter() throws Exception {
        underscoreStringBuilder.__(false, new Date(), new UnderscoreFilter<UnderscoreStringBuilder>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Object appended) {
                underscoreBuilder.__(appended);
            }
        });

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_appendable_true_and_object_and_filter() throws Exception {
        underscoreStringBuilder.__(true, new Date(), new UnderscoreFilter<UnderscoreStringBuilder>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Object appended) {
                underscoreBuilder.__(appended);
            }
        });

        assertThat(underscoreStringBuilder.toString(), not(isEmptyString()));
    }

    @Test
    public void __and_predicate_false_and_object() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return false;
            }
        }, 123456.789);

        assertThat(underscoreStringBuilder.toString(), isEmptyString());
    }

    @Test
    public void __and_predicate_true_and_object() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, 123456.789);

        assertThat(underscoreStringBuilder.toString(), is("123456.789"));
    }

    @Test
    public void __and_predicate_true_and_object_and_filter() throws Exception {
        underscoreStringBuilder.__(new UnderscorePredicate() {
            @Override
            public boolean evaluate() {
                return true;
            }
        }, -123, new UnderscoreFilter<UnderscoreStringBuilder>() {
            @Override
            public void filter(UnderscoreStringBuilder underscoreBuilder, Object appended) {
                underscoreBuilder.__(Math.abs((Integer)appended));
            }
        });

        assertThat(underscoreStringBuilder.toString(), is("123"));
    }

    @Test
    public void __and_predicate_null_and_object_and_filter_null() throws Exception {
        try {
            underscoreStringBuilder.__(null, 123, null);
            fail("Must throw an exception - IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertThat("Must throw an exception",
                ex.getMessage(), CoreMatchers.is("underscorePredicate must not be null."));
        }
    }
}
