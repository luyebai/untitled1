package cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class OneClazzCglib {
    public static void main(String[] args) {
        BookFa fa = new BookFa();
        CglibDemo demo = new CglibDemo();
        BookFa obj = (BookFa) demo.getObj(fa);
        obj.addBook();
    }
}
//被代理类
class BookFa{
    public void addBook(){
        System.out.println("新增图书");
    }
}

class CglibDemo implements MethodInterceptor{
    private Object obj;

    public Object getObj(Object obj) {
        this.obj = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        //methodInterceptor接口继承了callback接口
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理方法");
        return method.invoke(obj,objects);
    }
}
