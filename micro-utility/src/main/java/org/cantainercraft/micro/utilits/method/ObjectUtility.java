package org.cantainercraft.micro.utilits.method;
import java.lang.reflect.Field;

public class ObjectUtility {
    public static <T> void updateObject(T updateObject, T object) throws Exception {
        Class<?> updateClass = updateObject.getClass();

        Field[] fields = updateClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object resultGet = field.get(object);

            if (resultGet != null) {
                if (field.getType().isPrimitive()) {
                    // Обработка примитивных типов
                    field.set(updateObject, resultGet);
                } else {
                    field.set(updateObject, resultGet);
                }
            }
        }
    }
}
