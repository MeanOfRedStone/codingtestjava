package dynamic;

/*
 10 20 15 25 10 20
 -1 동적 계획법
 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
 N 값이 문제를 부분 문제로 나누는 역할을 해야함
 D[N] : N번 째 위치를 마지막으로 밟을 때 가질 수 있는 최대 합 <-> 2156번 문제의 문제 조건 N번째를 선택할 수 있을 대 가장 많이 포도주를 마실 수있는 경우
-> 점화식에서 의미를 잘 구분해함 그냥 최댓값인지, 마지막을 거쳐야 하는지!!

 -2 N 값의 범위에 따라 에러 안나는지 잘 살피자
 
 D[1] = stairs[1] = 10
 D[2] = stairs[1] + stairs[2] = 100 + 200
 D[3] = D[1] + stairs[3]  | D[0] + stairs[2] + stairs[3] | D[2](stairs[1] + stairs[2])
            100 + 15       |     0 + 215 D[2]
 D[4] = D[2] + D[4] | D[1] + stiars[3] + stairs[4]  |  D[3]   -> D[3](stair1 , stair2)

 D[5] = D[4]    |  D[3] + stairs[5]  | D[2] + stairs[4] + stair[5]
 */
import java.util.*;
public class Baekjoon2579 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int stairs[] = new int[N+1];
        int D[] = new int[N+1];
        for(int i = 1; i < N + 1; i++){
            stairs[i] = sc.nextInt();
        }
        D[0] = 0;
        D[1] = stairs[1];
        if(N == 2) {
            D[2] = stairs[1] + stairs[2];
        }
        if(N > 2){ 
            D[2] = stairs[1] + stairs[2];
            for(int i = 3; i < N + 1; i++){
                D[i] = Math.max(D[i-2] + stairs[i], D[i-3] + stairs[i-1] + stairs[i]);
            }
        }
        System.out.println(D[N]);
    }
}
