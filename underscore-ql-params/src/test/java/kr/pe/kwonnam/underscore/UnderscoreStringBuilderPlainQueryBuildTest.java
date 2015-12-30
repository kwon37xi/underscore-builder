package kr.pe.kwonnam.underscore;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;

import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.*;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.slf4j.LoggerFactory.getLogger;

public class UnderscoreStringBuilderPlainQueryBuildTest extends AbstractQueryBuildTest {
    private Logger log = getLogger(UnderscoreStringBuilderPlainQueryBuildTest.class);

    private UnderscoreStringBuilder usb;

    @Before
    public void setUpBuilder() throws Exception {
        usb = new UnderscoreStringBuilder();
    }

    @Test
    public void buildWithUnderscoreStringBuilder() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        usb
            .__("SELECT ").suffixNewLine()
            .__(", ", join(User.COLUMNS))
            .__("FROM users as u")
            .sub(user != null, new UnderscoreSubBuild() {
                @Override
                public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
                    underscoreSubBuilder.prefix("\n    ")
                        .__(user.getUserId() != null, "AND user_id = %d", format(user.getUserId()))
                        .__(isNotEmpty(user.getName()), "AND name = '%s'", format(user.getName()))
                        .__(user.getBirthday() != null, "AND birthday = '%s'", format(sdf.format(user.getBirthday())))
                        .__(CollectionUtils.isNotEmpty(zipCodes), ", ", join(zipCodes), wrap("AND zip_code in (", ")"));

                }
            }, trim(trimOpts().prefix("WHERE ").prefixOverrides("AND ", "OR ")))
            .__("LIMIT 10");
        log.info("UnderscoreStringBuilder : {}", usb.toString());
    }
}
