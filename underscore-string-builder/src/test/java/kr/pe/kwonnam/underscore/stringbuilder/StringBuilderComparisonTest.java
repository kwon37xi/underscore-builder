package kr.pe.kwonnam.underscore.stringbuilder;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.slf4j.LoggerFactory.getLogger;

public class StringBuilderComparisonTest {
    private Logger log = getLogger(StringBuilderComparisonTest.class);

    private StringBuilder sb;

    private User user;

    @Before
    public void setUp() throws Exception {
        sb = new StringBuilder();
        user = new User();

        user.setUserId("underscore");
        user.setName("String Builder");
        user.setBirthday(new SimpleDateFormat("yyyy/MM/dd").parse("2015/12/11"));
        user.setEmail("someone@email.com");
    }

    @Test
    public void buildWithStringBuilder() throws Exception {
        List<Object> params = new ArrayList<Object>();
        List<String> zipCodes = Arrays.asList("12345", "56789", "58391");

        sb
            .append("SELECT ")
            .append(join(User.COLUMNS, ", "))
            .append("\n")
            .append("FROM users as u\n")
            .append("WHERE 1 = 1"); // BUG!! no spaces.
        if (isNotEmpty(user.getUserId())) {
            sb.append("AND user_id = ? \n");
            params.add(user.getUserId());
        }
        if (isNotEmpty(user.getName())) {
            sb.append("AND name = ? \n");
            params.add(user.getName());
        }
        if (user.getBirthday() != null) {
            sb.append("AND birthday = ? \n");
            params.add(user.getBirthday());
        }
        if (zipCodes != null && !zipCodes.isEmpty()) {
            List<String> inParams = new ArrayList<String>(zipCodes.size());
            for (String zipCode : zipCodes) {
                inParams.add("?");
            }

            sb.append(String.format("AND zip_code IN (%s)", StringUtils.join(inParams, ",")));
            params.addAll(zipCodes);
        }

        log.info("StringBuilder : {}", sb.toString());
        log.info("Params : {}", params);
    }
}
