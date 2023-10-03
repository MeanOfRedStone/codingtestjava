package greedy;
import java.util.*;

/*
-1 사실 그리디인 지는 모르겠음
자리수중에 0이 있고 모두 더해서 3의 배수인지를 확인하는 문제였다.
오히려 구현 / 수학에 가깝다고 생각

-2 문제를 잘 못 이해했음
모둔 자리수를 섞어서 최대의 수를 만드는 것이었음
그렇기 때문에 각 자리수를 더해서 복잡하게 3의배수인지 찾을 필요는 없었음
-> 문제 읽는 것 좀 더 신경쓰자

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
