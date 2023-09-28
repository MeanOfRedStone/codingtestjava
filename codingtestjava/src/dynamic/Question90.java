package dynamic;

/*
 <문제90 최장 공통 부분 수열 찾기>
 LCS문제는 두 수열이 주어졌을 때 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제다.
 예를 들어 ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 */
/*
 ACAYKP
 왼쪽L[] A , AC, ACAY, ACAYK, ACAYKP
 오른쪽 R[] P, KP, YKP, AYKP, CAYKP, ACAYKP

 CAPCAK

 */

 /*
  <출제 포인트>
  (1) 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
  N 값이 문제를 부분 문제로 나누는 역할을 해야함

  (2) LCS - 문자열을 이용한 대표적인 동적 계획법
  -> 각 문자열을 축으로하는 2차원의 배열을 생성.
  -> 특정 자리가 가리키는 행과 열의 문자열값을 비교해 값이 같으면 배열의 대각선 왼쪽위의 값에 1을 더한 값을 저장
  예) DP[i][j] = DP[i-1][j-1] + 1

  - 비교한 값이 다르면 배열의 왼쪽의 값 중 큰 값을 선택해 저장합니다.
  DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1])

  -출력 : 마지막부터 탐색을 수행, 해당자리에 있는 문자열값을 비교해 값이 같으면 최장 증가수열에 해당하는 문자로 기록하고, 왼쪽 대각선으로 이동.
         비교한 값이 다르면 배열의 왼쪽 위의 값 중 큰 값으로 이동합니다.


  (3) 아예 풀이 유형을 몰랐던 문제
  */

import java.util.*;

public class Question90 {
    static int col;
    static int row;
    static int DP[][];
    static String A;
    static String B;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        col = A.length();
        B = sc.nextLine();
        row = B.length();
        DP = new int[row + 1][col + 1];

        for(int i = 1; i <= col; i++){
            for(int j = 1; j <= row; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    DP[j][i] = DP[j-1][i-1] + 1;
                } else{
                    DP[j][i] = Math.max(DP[j-1][i], DP[j][i-1]);
                }
            }
        }

        System.out.println(DP[row][col]);
        printDP(DP);
    }

    public static void printDP(int DP[][]){
        Stack<Character> st = new Stack<>();
        
        while(col != 0 && row != 0){
            if(A.charAt(col-1) == B.charAt(row-1)){
                st.push(A.charAt(col-1));
                row = row -1;
                col = col -1;
            } else{
                int temp = Math.max(DP[row-1][col], DP[row][col-1]);
                if(temp == DP[row-1][col]){
                    row = row - 1;
                } else{
                    col = col -1;
                }
            }
        }

        while(!st.empty()){
            System.out.print(st.pop()+"");
        }
    }
}
