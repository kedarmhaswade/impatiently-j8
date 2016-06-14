package tmp;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 *
 *
 * <a href="http://stackoverflow.com/questions/36504671/how-can-i-determine-the-unerased-type-of-a-field> SO question </a>
 * Created by kmhaswade on 4/8/16.
 */
public class GenericTypeReflect {

    private static class Response <T> {
        T response;
    }
    private static class ServiceResponse {
        String someField;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Response<ServiceResponse> response = new Response<>();
        Field field = response.getClass().getDeclaredField("response");
        Type type = field.getGenericType();
        System.out.println(field.getGenericType() + ", " + field.getGenericType());
    }
}

