package tr.com.metea.hotelium.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mete Aydin
 * <p>
 * 11.12.2022
 */
public final class ReflectionUtil {
    private ReflectionUtil() {

    }

    public static <T> T copy(T entity) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = entity.getClass();
        T newEntity = (T) entity.getClass().newInstance();

        while (clazz != null) {
            copyFields(entity, newEntity, clazz);
            clazz = clazz.getSuperclass();
        }

        return newEntity;
    }

    public static <T> T copyFields(T entity, T newEntity, Class<?> clazz) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(newEntity, field.get(entity));
        }
        return newEntity;
    }
}
