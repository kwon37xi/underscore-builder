package kr.pe.kwonnam.underscore;

import org.junit.Before;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractQueryBuildTest {
    protected User user;

    protected List<String> zipCodes;

    @Before
    public void setUp() throws Exception {
        user = new User();

        user.setUserId("underscore");
        user.setName("String Builder");
        user.setBirthday(new SimpleDateFormat("yyyy/MM/dd").parse("2015/12/11"));
        user.setEmail("someone@email.com");

        zipCodes  = Arrays.asList("12345", "56789", "58391");
    }
}
