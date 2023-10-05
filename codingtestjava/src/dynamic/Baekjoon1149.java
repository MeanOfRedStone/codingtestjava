package dynamic;

/*
<문제> RGB 거리
(1) 계단 수 알고리즘과 비슷한 유형

-1 동적 계획법 : 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
변수 값이 값이 문제를 부분 문제로 나누는 역할을 해야함

D[N][color] : N번째의 집을 color로 지을 때의 최솟값

-2 조건의 개수만큼 행렬을 만들어 준다


1번 집의 색은 2번 집의 색과 같지 않아야 한다.
N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 R  G  B
 26 40 83  비용
 49 60 57
 13 89 99

 앞 뒤로 현재 집과 색깔이 같지 않아야 한다.

 D[N][1] : N번째 집을 빨간색으로 칠할 때 이때 까지 칠한 비용의 최소 합
 D[N][2] : N번째 집을 초록색으로 칠할 때 이때 까지 칠한 비용의 최소 합
 D[N][3] : N번째 집을 파란색으로 칠할 때 이때 까지 칠한 비용의 최소 합
 
 D[1][1] = 26
 D[1][2] = 40 
 D[1][3] = 83

 D[2][1] = 49 + Math.min(D[1][2], D[1][3])
 D[2][2] = 40 + Math.min(D[1][1], D[1][3])
 D[2][3] = 64 + Math.min(D[1][1], D[1][2])

 D[1][1] = home[1][1];
 D[1][2] = home[1][2];
 D[1][3] = home[1][3];
 for(int i = 2; i < N + 1; i++){
    D[i][1] = home[i][1] + Math.min(D[i-1][2], D[i-1][3]);
    D[i][2] = home[i][2] + Math.min(D[i-1][1], D[i-1][3]);
    D[i][3] = home[i][3] + Math.min(D[i-1][1], D[i-1][2]);
 }
 */


import java.util.*;
public class Baekjoon1149 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int home[][] = new int[N + 1][4];

        for(int i = 1; i < N + 1; i++){
            for(int j = 1 ; j < 4 ; j++){
                home[i][j] = sc.nextInt();
            }
        }
        
        int D[][] = new int[N+1][4];

        D[1][1] = home[1][1];
        D[1][2] = home[1][2];
        D[1][3] = home[1][3];
        for(int i = 2; i < N + 1; i++){
            D[i][1] = home[i][1] + Math.min(D[i-1][2], D[i-1][3]);
            D[i][2] = home[i][2] + Math.min(D[i-1][1], D[i-1][3]);
            D[i][3] = home[i][3] + Math.min(D[i-1][1], D[i-1][2]);
        }

        int min = D[N][1];
        for(int i = 2; i < 4; i++){
            min = Math.min(min, D[N][i]);
        }

        System.out.println(min);
    }
}
