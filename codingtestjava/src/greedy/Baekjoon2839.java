package greedy;

/*
<설탕 배달>

 최대한 적은 봉지 -> 그리디
 최적해로 하나씩 증가해가며 푼다.
 5키로 , 3키로
 18
 18 / 5 = 3  + 3

 4 / 3 1 + 1 
 1

 6
 3 + 5

 3 + 10 = 13

 N
 
 while(q < 0)
    (1)
    int q = N / 5  몫
    int r = N % 3  나머지

    if(r % 3 == 0)
        System.out.println(q + r/3);
    else
        System.out.println(-1);

    (2)
    q - 1
    r + 5

    if(r % 3 == 0)
        S

 */

import java.util.*;
public class Baekjoon2839 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int q = N / 5;
        int r = N % 5;
        int X = -1;
        while(q >= 0){
            if(r % 3 == 0){
                X = q + r/3;
                break;
            } else{
                
                q -= 1;
                r += 5;

            }
        }
        System.out.println(X);
    }
}
