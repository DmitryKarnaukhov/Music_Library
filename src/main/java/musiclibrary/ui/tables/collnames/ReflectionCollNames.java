package musiclibrary.ui.tables.collnames;

import java.lang.reflect.Field;

public final class ReflectionCollNames {
    private ReflectionCollNames() {
    }

    public static String[] getEntityFieldNames(Class entity){
        Field[] fields = entity.getDeclaredFields();
        String[] collNames = new String[fields.length];
        for (int fieldPointer = 0; fieldPointer < fields.length; fieldPointer++){
            collNames[fieldPointer] = fields[fieldPointer].getName();
        }
        return collNames;
    }
}
