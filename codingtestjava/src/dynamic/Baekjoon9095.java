package dynamic;

/*
 >문제> 1, 2, 3 더하기

 (1, 2, 3) 이 고정인게 핵심 -> 피보나치 뭐 이런거 알아도 이 힌트에 민감하게 반응하지 못하면 절때 케이스 분류할 수 없다
 포도주 시식이날 계단 오르기는 단순히 합하기만 하면 됐잖아. 근데 이거는 이 규칙을 알아야만 합할 수 있다.

 D[5] = 4 + 1 3 + 2 2 + 3
 D[5] = D[i-1] + D[i-2] + D[i-3]
 D[1] = { 1 } -> 1
 D[2] = { 1 + 1, 2} -> 2   
 D[3] = {1 + 1 + 1, 1 + 2, 2 + 1, 3} -> 4
 D[4] = D[3] + D[1] , D[2] + D[2]
 D[i] = D[i-1] + D[i-3] + D[]


 */

import java.util.*;
public class Baekjoon9095 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> answer = new ArrayList<>();
        int T = sc.nextInt();
        for(int a = 0; a < T; a++){
            int n = sc.nextInt();
            int D[] = new int[n+1];
            if(n == 1){
                D[1] = 1;
                answer.add(D[n]);
            } else if(n == 2){
                D[1] = 1;
                D[2] = 2;
                answer.add(D[n]);
            } else if(n == 3){
                D[1] = 1;
                D[2] = 2;
                D[3] = 4;
                answer.add(D[n]);
            } else{
                D[1] = 1;
                D[2] = 2;
                D[3] = 4;
                for(int i = 4; i <= n; i++){
                    D[i] = D[i-1] + D[i-2] + D[i-3];
                }
                answer.add(D[n]);
            }
        }
        for(int x : answer){
            System.out.println(x);
        }
    }
}
