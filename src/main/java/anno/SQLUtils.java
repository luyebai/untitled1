package anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SQLUtils {
    public static String insterSql(Object obj) throws IllegalAccessException {
        String tableName=null;
        String primaryKey=null;
        List<String> list=new ArrayList<>();
        StringBuilder builder = new StringBuilder("SELECT ");
        Class clazz = obj.getClass();
        Annotation[] type = clazz.getDeclaredAnnotations();
        for (Annotation annotation : type) {
            if(annotation instanceof TableName){
                TableName tn=(TableName)annotation;
                tableName=tn.value();
            }
        }
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.get(obj));
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation instanceof PrimaryKey){
                    PrimaryKey tn=(PrimaryKey)annotation;
                    primaryKey=tn.value();
                }
                if(annotation instanceof Column){
                    Column tn=(Column)annotation;
                    list.add(tn.value());
                }
            }
        }
        String join = String.join(",", list);
        return builder.append(primaryKey).append(",").append(join).append(" FROM ").append(tableName).toString();
    }

}
