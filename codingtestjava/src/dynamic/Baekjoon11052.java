package dynamic;


/*
 -1 동적 계획법
 N으로 전체의 문제를 어떻게 부분의 문제로 만들 것인가
 계속 카드를 새롭게 가져가면 아무 의미가 없다
 D[N] 카드가 N개일 때 최댓값을 저장해서 계속 불러와서 활용했음
 */
/*
n 1 2 3 4
c 1 5 6 7
D[N] : 카드가 N장일 떄 최대 비용
D[1]  : P[1]   1
D[2]  : P[1] + P[1] , P[2]  2 , 1 + 1 
D[3]  : P[2] + P[1], P[3],    D[3] =  D[2] + P[1] = P[2] + P[1]  1 + 1 + 1, 2 + 1, 3
D[4]  : D[3] + P[1] , P[4], P[2] + P[2],  D[4]=  Math.max(D[3] + P[1], P[4])   4 3 + 1 2 + 2
                                                                                D[3] + P[1] = D[2] + P[1] + P[1]

카드 팩의 가격이 주어졌을 때, N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값을 구하는 프로그램을 작성하시오.

for(int i = 2; i < N + 1; i++){
    if(P[i-1] < P[i]){
        D[i] = P[i];
    } else{
        D[i] = D[i - 1] + P[1];
        for(int j = 1; j < i + 1; j++)[
            D[i] = Math.max(D[i], D[j] + D[N-j]);
        ]
    }
}
D[2] = for(int = 1; i < 3; i++){
    if(P[1] < P[2]){
        D[2] = P[2];
    } else{

    }
}
 */


import java.util.*;
import java.io.*;

public class Baekjoon11052 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        //카드의 가격
        int P[] = new int[N + 1];
        String cards[] = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            P[i + 1] = Integer.valueOf(cards[i]);
        }
        
        //제일 비싸게 카드를 사는 법
        int D[] = new int[N+1];

        P[0] = 0;
        D[1] = P[1];
        for(int i = 2; i < N + 1; i++){
            for(int j = 0; j < i + 1; j++){
                D[i] = Math.max(D[i], D[j] + P[i-j]);
            }
        }

        System.out.println(D[N]);
    }
}