package dynamic;


/*
-1 2 * n 타일링 문제랑 이어짐

피보나치 형태

이전 것은
D[n] = D[n-1] + D[n-2]

였음 D[n-1]에 D[n-1]의 타일을 이어붙임

하지만 이번에는 2개짜리 타일이 2개가 늘었기 때문에
2개 차이나는 D[n-2]의 생성 경우가 2배가 됨

-> 방식은 생각해냈는데 구현이 아쉽다
n -> n-1 차이나는건 하나만 이어붙일 수 잇음
n -> n-2 는 두 개 이어붙일 수 잇음
 
 */

import java.util.*;
public class Baekjoon11727 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int D[] = new int[N+1];

        D[1] = 1;

        if(N>=2){
            D[2] = 3;
        }
        
        for(int i =3; i < N + 1; i++){
            D[i] =( D[i-1] + 2 * D[i-2]) % 10007;
        }

        System.out.println(D[N]);
    }
    
}
