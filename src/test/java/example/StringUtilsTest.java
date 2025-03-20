package example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testConcat_validArguments_success() {
        // given:
        String str1 = "Hello";
        String str2 = "World";
        StringUtils stringUtils = new StringUtils();

        // when:
        String result = stringUtils.concat(str1, str2);

        // then:
        assertEquals("HelloWorld", result);
    }

    @Test
    public void testConcat_emptyArguments_success() {
        // given:
        String str1 = "";
        String str2 = "";
        StringUtils stringUtils = new StringUtils();

        // when:
        String result = stringUtils.concat(str1, str2);

        // then:
        assertEquals("", result);
    }

    @Test
    public void testConcat_nullArguments_throwsException() {
        // given:
        String str1 = null;
        String str2 = "World";
        StringUtils stringUtils = new StringUtils();

        // when:
        Exception exception = assertThrows(NullPointerException.class, () -> {
            stringUtils.concat(str1, str2);
        });

        // then:
        assertNotNull(exception);
        assertEquals("Arguments cannot be null", exception.getMessage());
    }
}