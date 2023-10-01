package greedy;


/*
 (1) 그리디라고 생각한 이유
 - 서로다른 N개 자연수의 합이 S. 자연수 N의 최댓값은??
 - 작은 것부터 키워가면서 크게 만들자. 최적해.

 (2) 범위 지정할 때는 항상 등호 생각하자!
 

 */
/*
 10
 1 -> 9
 2 -> 7
 3 -> 4

 200
 1 -> 199
 2 -> 197
 3 -> 194
 5 -> 189
 6 -> 183
 7 -> 176
 8 -> 168
 9 -> 159
 10 -> 149
 11 -> 138
 12 -> 126
 13 -> 113
 14 -> 99
 15 -> 84
 16 -> 68
 17 -> 51
 18 -> S :33
 19 -> 19
 */


import java.util.*;

public class Baekjoon1789 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long S = sc.nextInt();
        long N = 1;
        int count = 0;

        while(S != 0){
            S = S - N;
            count += 1;
            if(S < N + 1){
                break;
            }
            N += 1;
        }

        if(S == 0){
            System.out.println(count);
        } else{
            while(S <= N){
                // N: 19 S : 19 -> 이런 경우는 못빼는 거임  N 19 S : 38 count -1 해서 한 번에 38빼야하는데
                S = S + N;
                N = N -1;
                count = count -1;
            }
            System.out.println(count + 1);
        }
    }
}
