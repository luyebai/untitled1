package moni;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Moni7 {
    public static void main(String[] args) throws IllegalAccessException {
        //创建一个被注入的类对象
        ToInJector jector = new ToInJector();
        //调用注入方法
        InjectorTools.injector(jector);
        //打印测试结果
        System.out.println(jector.myBean1.id);
        System.out.println(jector.myBean2.id);
        System.out.println(jector.myBean3.id);
        System.out.println(jector.myBean1byname.id);
        System.out.println(jector.myBean2byname.id);
        System.out.println(jector.myBean3byname.id);
    }

}
//被注入的类
class ToInJector{//根据类型和根据name注入
    @MyAutoWiredByType
    MyBean1 myBean1;
    @MyAutoWiredByType
    MyBean2 myBean2;
    @MyAutoWiredByType
    MyBean3 myBean3;
    @MyAutoWiredByName("MyBean1")
    MyBean1 myBean1byname;
    @MyAutoWiredByName("MyBean2")
    MyBean2 myBean2byname;
    @MyAutoWiredByName("MyBean3")
    MyBean3 myBean3byname;
}

//注入类的实现
class InjectorTools{
    //两个map充当容器
   private static HashMap<String, Object> byName = new HashMap<>();
   private static HashMap<Class, Object> byTapy = new HashMap<>();
    static{
        //在map中添加入数据用来注入
        byName.put("MyBean1",new MyBean1());
        byName.put("MyBean2",new MyBean2());
        byName.put("MyBean3",new MyBean3());
        //根据类型
        byTapy.put(MyBean1.class,new MyBean1());
        byTapy.put(MyBean2.class,new MyBean2());
        byTapy.put(MyBean3.class,new MyBean3());
    }
    //实现注入的方法
    static void injector(Object obj) throws IllegalAccessException {
        /*
          思路：1、通过传入的类，获取类的Class对象
                2、获取属性集合
                3、遍历结合，并获取集合上的注解
                4、根据注解的类型来执行赋值操作
         */
        Class aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);//打破封装
            //获取注解集合，其实只有一个注解，但这样写更省代码
            Annotation[] annotations = field.getDeclaredAnnotations();
            if(annotations!=null){//代码健壮性的判断
                for (Annotation annotation : annotations) {
                    //如果是……则……
                    if (annotation instanceof MyAutoWiredByType ){
                        Class type = field.getType();//根据类型赋值
                        Object o = byTapy.get(type);
                        field.set(obj,o);
                    }
                    if (annotation instanceof MyAutoWiredByName ){
                        String value = ((MyAutoWiredByName) annotation).value();
                        //如果并没有在注解上给name则按照类型注入
                        if ("".equals(value)){
                            Class type = field.getType();//根据类型赋值
                            Object o = byTapy.get(type);
                            field.set(obj,o);
                        }else{//否则按照name注入
                            Object o = byName.get(value);//根据name赋值
                            field.set(obj,o);
                        }
                    }
                }
            }
        }
    }
}
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyAutoWiredByType{//定义ByType的注解

}
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyAutoWiredByName{
    String value() default "";//默认空值
}



//测试的数据
class MyBean1{
    String id="我是MyBean1";
}
class MyBean2{
    String id="我是MyBean2";
}
class MyBean3{
    String id="我是MyBean3";
}