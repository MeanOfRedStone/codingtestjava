package dynamic;

/*
 <문제91 가장 큰 정사각형 찾기>
 크기가 n x m 이고 0과 1로만 이루어진 배열이 있다.
 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프록램을 작성하시오.
 예를 들어 다음과 같은 배열에서는 가운데에 있는 2 x 2 크기의 정사각형이 가장 크다.
 */

 /*
 <출제 포인트>
  (1) 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
  N 값이 문제를 부분 문제로 나누는 역할을 해야함 -> for루프의 순서를 고려해 대각선 왼쪽 위 | 왼쪽 | 위쪽의 값이 0이 아니면 사각형 범위 늘어나는 것으로 판정
  -> 90번문제의 LCS 동적 계획법 활용
  -> 근데 배열 받으면서 한번에 처리 인덱스 0보다 작은 것 처리 안되게 하고 사각형 안커질때 변은 가장 최소값(0)을 찾는 방식으로 해서 해결
  -> 내방식고 무슨 차이가 잇는지는 잘 모르겠다.

  (2) if문 쓸때 return 값나오는거 아니면 else 꼭 쓰자
  */
  /*
   DP[][]
    a[0][0] == 0 -> dp[0][0]
    a[1][0] == 0 -> dp[1][0] 
    a[2][0] == 1 -> dp[2][0]
    dp[i-1][j] == 0 or dp[i][j-1] == 0 -> dp[0][1] = a[0][0]  
    dp[i-1][j] == 1 or dp[i][j-1] == 1 ->
    
    0 0                 0 1 0 0
    0 1                 0 1 1 1
    1                   1 1 1 0
    0                   0 0 1 0
   */
import java.util.*;
public class Question91 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long DP[][] = new long[1001][1001];
        long max = 0;
        //정사각형 입력
        for(int i = 0; i < n ; i++){
            String[] strArr = sc.next().split("");
            for(int j = 0; j < m; j++){
                DP[i][j] = Integer.valueOf(strArr[j]);
                if(DP[i][j] == 1 && j > 0 && i > 0){
                    DP[i][j] = Math.min(DP[i-1][j-1], Math.min(DP[i-1][j], DP[i][j-1])) + DP[i][j];
                }
                if(max < DP[i][j]){
                    max = DP[i][j];
                }
            }
        }
        //최대 사각형의 변의 길이를 제곱
        System.out.println(max*max);
    }
}
