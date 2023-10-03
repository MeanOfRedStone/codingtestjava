package greedy;
import java.util.*;

/*
 30 -> 3, 0

 30의 배수 -> 2 * 3 * 5

 
 */

public class Beakjoon10610 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String N[] = sc.next().split("");
        ArrayList<Integer> numbers = new ArrayList<>(); 
        //총 자릿수
        int count = N.length;
        for(int i = 0; i < count; i++){
            numbers.add(Integer.valueOf(N[i]));
        }
        Collections.sort(numbers);
        Collections.reverse(numbers);

        //0이 없으면 바로 종료
        int findZero = 0;
        for(int x : numbers){
            if(x == 0){
                findZero += 1;
            }
        }

        if(findZero == 0){
            System.out.println(-1);
            return ;
        }


        
        int sum = 0;
        for(int i = 0; i < count; i++){
            sum += numbers.get(i);
        }

        if(sum % 3 == 0){
            for(int x : numbers){
                System.out.print(x);
            }
        } else{
            System.out.println(-1);
        }
        
        
    }
    
}
