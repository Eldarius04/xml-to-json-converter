package example;

public class StringUtils {

    public String concat(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("Arguments cannot be null");
        }
        return str1 + str2;
    }
}