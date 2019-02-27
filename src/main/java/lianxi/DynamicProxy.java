package lianxi;

import sun.applet.Main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Datac datac = new Datac();
        Datacc bind = new MyProxy().bind(datac);
        bind.sayHello();

        Datacc o = (Datacc)Proxy.newProxyInstance(datac.getClass().getClassLoader(), datac.getClass().getInterfaces(), (proxy, method, arg) -> {
            System.out.println("lanmd表达式");
            return method.invoke(datac, arg);
        });
        o.sayHello();
        Datacc bind2= (Datacc)Proxy.newProxyInstance(datac.getClass().getClassLoader(), datac.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("niminglei");
                return method.invoke(datac, args);
            }
        });
        bind2.sayHello();
    }

    interface Datacc{
        void sayHello();
    }
    static class Datac implements Datacc{

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class MyProxy implements InvocationHandler {
        private Object obj;


        public Datacc bind(Object obj){
            this.obj=obj;
           return (Datacc)Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("jiuzheyang");
            return method.invoke(obj,args);
        }
    }

}
