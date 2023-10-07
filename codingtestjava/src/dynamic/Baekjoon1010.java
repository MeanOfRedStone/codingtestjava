package dynamic;

/*
 -1 동적 계획 법: N으로 전체 문제를 어떻게 부분의 문제로 만들 것인가
 -> 기본 동적 계획법은 주로 차례로 더하기 LIS, LCS 등이 있음

 -2 D[i][j] 의 위치에 다리를 지을 수 있는 경우의 수는 D[i-1][1] +... + D[i-1][j-1]

 -3 마지막 위치에서 모든 다리를 지을 수 있는 경우의 수를 더해야함 n - m 다리 연결된 경우만 있는 건 아니니깐
 */
/*
  
1 - 1  
2 - 2  
3 - 3  
4  4
   5
   6
   7
 D[1][1] = 1 D[1][2] = 1 D[1][3] = 1 D[1][4] =1 D[1][5] = 1 D[1][6] = 1 D[1][7] = 1


 D[2][2] = D[1][1]  D[2][3] = D[1][1] + D[1][2]  D[2][4] = D[1][1] + D[1][2] + D[1][3]


 D[3][2] = D[2][1]  D[3][3] = D[2][1] + D[2][2] D[3][4] = D[2][1] + D[2][2] + D[2][3]

 D[i][j] = D[i-1][1] + D[i-1][2] + ... + D[i-1][j]

 for(int i = 1; i < M + 1; i++){
    D[1][i] = 1;
 }

 for(int i = 2; i < N + 1){
    for(int j = 2; j < M + 1){
        D[i][j] = 0;
        for(int k = 1; k < j; k++){
            D[i][j] += D[i-1][k];
        }
    }
 }
 

 

 D[2][4] = D[1][1] + [1][2] + D[1][3] 

 D[3][3] = D[1]

 D[N][M]

  1 2 
  1 1
  2 2
  
  D[N] : N의 위치에서 지을 수 있는 경우

  D[1][3] : 1에서 3의 위치로 다리를 지
  D[1] = 1

 */
import java.util.*;

public class Baekjoon1010 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<Integer> answer = new ArrayList<>();
        for(int a = 0; a < T; a++){
            int N = sc.nextInt();
            int M = sc.nextInt();

            int D[][] = new int[N+1][M+1];

            for(int i = 1; i < M + 1; i++){
                D[1][i] = 1;
            }

            for(int i = 2; i < N + 1; i++){
                for(int j = 2; j < M + 1; j++){
                    D[i][j] = 0;
                    for(int k = 1; k < j; k++){
                        D[i][j] += D[i-1][k];
                    }
                }
            }

            int sum = 0;
            for(int i = 1; i < M + 1; i++){
                sum += D[N][i];
            }
            answer.add(sum);
        }
        
        for(int i : answer){
            System.out.println(i);
        }
    }
}
