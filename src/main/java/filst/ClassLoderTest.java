package filst;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
       //自定义一个类的加载器
        ClassLoader classLoader = new ClassLoader(){
            //重写一个加载方法
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                byte[] b=null;
                try {//对传入的类名进行切分，获取到类名
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    //查找指定名称的类的源文件，返回一个输入流，
                    // 如果找不到这个源文件则返回null
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    //is.available()返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）
                    // 的估计字节数。其实就是返回文件大小，但是如果用于网络的话会有误差，网络时推荐
                    /*URLConnection openConnection = new URL("http://www.apache.org").openConnection();
                    System.out.println(openConnection.getContentLength());*/
                    //获取文件大小的方法
                    b = new byte[is.available()];

                    is.read(b);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        //使用指定的类加载器加载名称为"filst.ClassLoderTest"的对象，使用返回的Class对象创建一个新实例
        Object obj = classLoader.loadClass("filst.ClassLoderTest").newInstance();
        System.out.println(obj.getClass());
        //判断此对象是否是filst.ClassLoderTest类的一个实例
        System.out.println(obj instanceof filst.ClassLoderTest);

    }
}
