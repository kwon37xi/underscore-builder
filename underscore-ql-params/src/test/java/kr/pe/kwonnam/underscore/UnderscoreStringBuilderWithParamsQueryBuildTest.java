package kr.pe.kwonnam.underscore;

import kr.pe.kwonnam.underscore.qlparams.UnderscoreQlParams;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.trim;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.slf4j.LoggerFactory.getLogger;

public class UnderscoreStringBuilderWithParamsQueryBuildTest extends AbstractQueryBuildTest {
    private Logger log = getLogger(UnderscoreStringBuilderWithParamsQueryBuildTest.class);

    private UnderscoreStringBuilder usb;

    private UnderscoreQlParams qlParams;

    @Before
    public void setUpBuilder() throws Exception {
        usb = new UnderscoreStringBuilder();
        qlParams = new UnderscoreQlParams();
    }

    @Test
    public void buildWithUnderscoreStringBuilderWithParams() throws Exception {
        usb
            .__("SELECT ").suffixNewLine()
            .__(", ", join(User.COLUMNS))
            .__("FROM users as u")
            .sub(user != null, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.prefix("\n   ")
                        .__(user.getUserId() != null, "AND user_id = %s", qlParams.params(user.getUserId()))
                        .__(isNotEmpty(user.getName()), "AND name = %s", qlParams.params(user.getName()))
                        .__(user.getBirthday() != null, "AND birthday = %s", qlParams.params(user.getBirthday()))
                        .__(CollectionUtils.isNotEmpty(zipCodes), "AND zip_code in (%s)", qlParams.inParams(zipCodes));
                }
            }, trim(trimOpts().prefix("WHERE ").prefixOverrides("AND ", "OR ")))
            .__("LIMIT %s", qlParams.params(10));

        log.info("UnderscoreStringBuilder with UnderscoreQlParams : {}", usb.toString());
        log.info("Query Parameters : {}", qlParams.getQueryParameters());
    }
}
