package yuekao225;


import java.util.HashMap;

/**
 *编写一个程序将d:/java目录下所有.java文件复制到d:/jad目录下，
 * 并将原文件扩展名从.java改为.jad
 */
public class TestYK {
    public static void main(String[] args) {
        System.out.println(castUpper(3688.23));
    }

    static String castUpper(double many){
        char[] ch2={'零','拾','佰','仟','万'};
        char[] ch3={'角','分'};
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('0','零');
        hashMap.put('1','壹');
        hashMap.put('2','贰');
        hashMap.put('3','叁');
        hashMap.put('4','肆');
        hashMap.put('5','伍');
        hashMap.put('6','陆');
        hashMap.put('7','柒');
        hashMap.put('8','捌');
        hashMap.put('9','玖');

        Double aDouble = Double.valueOf(many);
        //切分整数和小树
        String[] split = aDouble.toString().split("\\.");
        //整数数组
        char[] chars = split[0].toCharArray();
        int s=chars.length;
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            if(s==1){
                builder.append(hashMap.get(aChar).toString());
            }else{
                builder.append(hashMap.get(aChar).toString()).append(ch2[--s]);
            }
        }
        builder.append("元点");
        chars=split[1].toCharArray();
        s=chars.length;
        for (char aChar : chars) {
            builder.append(hashMap.get(aChar).toString()).append(ch3[--s]);
        }
        return builder.toString();
    }
}
