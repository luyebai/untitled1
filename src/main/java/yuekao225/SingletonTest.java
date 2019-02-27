package yuekao225;

import org.apache.poi.util.SystemOutLogger;

/**
 *
 */
public class  SingletonTest {
   public static void main(String[] args) {
      System.out.println(computeAge(8));
   }

   public static int computeAge(int n){
      if(n==1) {return 10;}
      return computeAge(n-1)+2;
   }
   public static void toBinary(int n,StringBuffer result){
      if(n/2 != 0){
            toBinary(n/2,result);
      }
      result.append(n%2);
   }
}
