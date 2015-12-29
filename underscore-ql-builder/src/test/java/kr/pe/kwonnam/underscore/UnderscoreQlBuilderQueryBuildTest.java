package kr.pe.kwonnam.underscore;

import kr.pe.kwonnam.underscore.qlbuilder.UnderscoreQlBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.trim;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.slf4j.LoggerFactory.getLogger;

public class UnderscoreQlBuilderQueryBuildTest  extends AbstractQueryBuildTest {
    private Logger log = getLogger(UnderscoreQlBuilderQueryBuildTest.class);

    @Before
    public void setUpBuilder() throws Exception {
    }

    @Test
    public void buildWithUnderscoreQlBuilder() throws Exception {
        final UnderscoreQlBuilder underscoreQlBuilder = new UnderscoreQlBuilder() {{
            __("SELECT ").suffixNewLine();
            __(",\n    ", join(User.COLUMNS));
            __("FROM users as u");
            sub(user != null, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder
                        .prefix("\n  ")
                        .__(user.getUserId() != null, "AND user_id = %s", params(user.getUserId()))
                        .__(isNotEmpty(user.getName()), "AND name = %s", params(user.getName()))
                        .__(user.getBirthday() != null, "AND birthday %s", params(user.getBirthday()))
                        .__(CollectionUtils.isNotEmpty(zipCodes), "AND zip_code IN (%s)", inParams(zipCodes));
                }
            }, trim(trimOpts().prefix("WHERE ").prefixOverrides("AND ", "OR ")));
            __("LIMIT %s", params(10));
        }};

        log.info("UnderscoreQlBuilder : {}", underscoreQlBuilder.toString());
        log.info("Query Parameters : {}", underscoreQlBuilder.getQueryParameters());
    }
}
