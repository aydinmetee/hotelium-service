package tr.com.metea.hotelium.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Mete Aydin
 * <p>
 * 11.12.2022
 */
public final class MockDataGenerator {
    private MockDataGenerator() {
    }

    public static <T> T generate(T entity) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final var fieldList = getFields(entity);
        for (Field element : fieldList) {
            element.setAccessible(true);
            final var type = element.getType();
            if (type.equals(String.class)) {
                element.set(entity, RandomGenerator.generateString());
            } else if (type.equals(Long.class)) {
                element.set(entity, RandomGenerator.generateLong());
            } else if (type.equals(Date.class)) {
                element.set(entity, new Date());
            } else if (type.equals(Boolean.class)) {
                element.set(entity, RandomGenerator.generateBoolean());
            } else if (type.getSuperclass().equals(Enum.class)) {
                element.set(entity, Arrays.stream(type.getEnumConstants()).findAny().get());
            } else if (type.equals(BigDecimal.class)) {
                element.set(entity, RandomGenerator.generateBigDecimal());
            } else {
                for (Constructor<?> ctor : type.getConstructors()) {
                    Class<?>[] paramTypes = ctor.getParameterTypes();
                    if (paramTypes.length == 0) {
                        element.set(entity, ctor.newInstance());
                    }
                }
            }
        }
        return entity;
    }

    private static <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
