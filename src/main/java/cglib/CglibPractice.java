package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/**
 *  jdk  需要接口  执行速度慢（通过反射） 生成代理类的速度快
 *   目标类是否为最终类没有关系
 *   代理类依赖目标类
 *
 *  cglib 不需要接口  执行速度快  生成慢   目标类不能是可最终类
 *   代理类与目标类之间是继承关系
 *
 *   说spring
 *
 */
public class CglibPractice {

    static Object createProxy(Object object){
        Enhancer enhancer = new Enhancer();
        MethodInterceptor interceptor = new MethodInterceptor() {
            /**
             * Object为由CGLib动态生成的代理类实例，
             * Method为上文中实体类所调用的被代理的方法引用，
             * Object[]为参数值列表，
             * MethodProxy为生成的代理类对方法的代理引用。
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("nihao");
                return method.invoke(object,objects);
            }
        };
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }
}
