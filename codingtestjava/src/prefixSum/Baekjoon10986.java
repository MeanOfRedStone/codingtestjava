package prefixSum;

/*
 <부분합>
 -> 나머지를 어떻게 처리할 것인가 0, 다른 수의 차이
 -> 자료형 주의
 */

import java.util.*;

public class Baekjoon10986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //수의 개수(<=1,000,000)
        int N = sc.nextInt();
        //나누는 수(<=1,000)
        int M = sc.nextInt();

        //수열
        Long[] sum = new Long[N + 1];
        sum[0] = (long) 0;

        for(int i = 1; i < N+1; i++){
            sum[i] = sum[i-1] + sc.nextInt();
        }

        //나머지 저장 배열
        Long[] rest = new Long[M];
        for(int i = 0; i < M; i++){
            rest[i] = (long) 0;
        }
        //연속합
        //Big-O : N
        for(int i = 1; i < N + 1; i++){
            int remainder = (int) (sum[i] % M);
            rest[remainder]++;
        }
   
        Long answer = rest[0];
        //Bio-O M^2
        for(int i = 0; i < M; i++){
            if(rest[i] > 1){
                answer += rest[i] * (rest[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
