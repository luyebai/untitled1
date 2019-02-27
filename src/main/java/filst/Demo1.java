package filst;



import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;



/**
 * 1. main函数快捷键 —— psvm
 * 2. System.out.println();输出快捷键 —— sout
 * 3. for(int i=0;i<;i++)for循环遍历快捷键 —— fori
 * 4. foreach循环遍历快捷键 —— iter
 * 5. itli遍历某集合
 */


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import static java.lang.invoke.MethodHandles.lookup;
public class Demo1 {
    public static void main(String[] args) throws Throwable {
        /**
         * public final Object invokeExact(Object... args)
         *                          throws Throwable调用方法句柄，允许任何调用者类型描述符，但需要确切的类型匹配。
         *                          invokeExact的呼叫站点上的符号类型描述invokeExact必须与此方法句柄type完全匹配。
         *                          参数或返回值不允许转换。
         * 参数
         *         args - 使用varargs静态表示的签名 - 多态参数列表
         *         结果
         *         签名多态结果，使用 Object
         */
        getPrintlnMH(System.out).invokeExact("小朋友");
    }
    private static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        //查找或创建给定方法类型的实例。
        MethodType type = MethodType.methodType(void.class, String.class);
        //为虚拟方法生成方法句柄。
        /**
         * public MethodHandle findVirtual(类<?> refc,
         *                                 String name,
         *                                 MethodType type)
         *                          throws NoSuchMethodException,
         *                                 IllegalAccessException为虚拟方法生成方法句柄。
         * refc - 访问该方法的类或接口
         * name - 方法的名称
         * type - 方法的类型，省略了接收方参数
         * 结果
         * 所需的方法句柄
         *
         *
         *
         * public MethodHandle bindTo(Object x)
         * 将值x绑定到方法句柄的第一个参数，而不调用它。
         * 新的方法句柄通过将其绑定到给定的参数来适配当前方法句柄作为其目标 。
         * 参数
         * x - 绑定到目标的第一个参数的值
         * 结果
         * 一个新的方法句柄，在调用原始方法句柄之前，将给定值添加到传入参数列表
         */
        return lookup().findVirtual(reveiver.getClass(),"println",type).bindTo(reveiver);
    }
}
