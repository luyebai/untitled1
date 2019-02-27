package dataStutas.queue;

import dataStutas.stack.StackSLinked;

public class StackTest {
    public static void main(String[] args) {
        baseConversion(100001);

        System.out.println(bracketMatch("{123+(3-4)}"));
    }
    //括号匹配
    public static boolean bracketMatch(String str){
        StackSLinked s = new StackSLinked();
        for (int i = 0; i <str.length() ; i++) {
            char c=str.charAt(i);//获取每一个字符
            switch (c){
                case '{':
                case '[':
                case '(':s.push(Integer.valueOf(c));break;
                case '}':if(!s.isEmpty()&&((Integer)s.pop()).intValue()=='{'){
                        break;
                        }else{
                            return false;
                        }
                case ')':
                    if(!s.isEmpty()&&((Integer)s.pop()).intValue()=='('){
                        break;
                    }else{
                        return false;
                    }

            }
        }
        if(s.isEmpty())return true;
        else return false;
    }
    //一个十进制转八进制的方法
    public static void baseConversion(int i){
        StackSLinked linked = new StackSLinked();
        while(i>0){
            linked.push(i%8+"");//将余数入栈
            i=i/8;//值i/8得到除数
        }
        //如果栈中还有值就输出（清空栈）
        while(!linked.isEmpty()) System.out.print((String) linked.pop());
    }

}
