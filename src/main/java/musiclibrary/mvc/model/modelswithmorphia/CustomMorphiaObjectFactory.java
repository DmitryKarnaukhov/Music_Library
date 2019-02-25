package musiclibrary.mvc.model.modelswithmorphia;

import org.mongodb.morphia.mapping.DefaultCreator;
import org.mongodb.morphia.mapping.MappingException;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;

public class CustomMorphiaObjectFactory extends DefaultCreator {
    @Override
    protected ClassLoader getClassLoaderForClass() {
        return ClassLoader.getSystemClassLoader();
    }

    @Override
    public Object createInstance(Class clazz) {
        try {
            final Constructor constructor = getNoArgsConstructor(clazz);
            if(constructor != null) {
                return constructor.newInstance();
            }
            try {
                return ReflectionFactory.getReflectionFactory().newConstructorForSerialization(clazz, Object.class.getDeclaredConstructor(null)).newInstance(null);
            } catch (Exception e) {
                throw new MappingException("Failed to instantiate " + clazz.getName(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor getNoArgsConstructor(final Class ctorType) {
        try {
            Constructor ctor = ctorType.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
