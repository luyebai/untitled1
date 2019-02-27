package cglib;

public class CglibTest {
    public static void main(String[] args) {
        CglibDataClazz dataClazz = new CglibDataClazz();
        CglibDataClazz proxy = (CglibDataClazz) CglibPractice.createProxy(dataClazz);
        proxy.seyHello();

    }
}
