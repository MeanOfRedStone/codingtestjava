package dynamic;

/*
 LCS 대표적인 동적 계획법 문제
 -1 동적 계획법
 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
 N 값이 문제를 부분 문제로 나누는 역할을 해야함

 -2 두 수열에서 최고로 긴 부분 수열 찾기 문제 -> 90번 91번 참고(두 수열 비교할 때는 행렬 형태로 만들어보자)
 내가 생각한 풀이법도 배열 1과 배열 2에서 어디로 해야지 더 긴 부분수열이 될지 고민했었음
 여기서는 그래서 위에서부터 시작하는 배열과 아래부터 시작하는 배열중 더 긴 부분을 저장
 그럼 의미가 생기잖아 길이가 N일 때 가장 긴 것은?
 나는 두 문자열을 비교하는 건데 한군데로만 몰아넣고 비교를 하려고 햇음
 문자열 두개 비교하니간 당연히 행렬 2개 생기는게 당연
 */

 /*
  ACAYKP  = a | c | a | y | k | p
  CAPCAK  = c | a | p | c | a | k

 
  a c a y k p
c 0 1
a 1 
p
c
a
k
  */

import java.util.*;

public class Baekjoon9251 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String A = sc.next();
        String B = sc.next();
        int lengthA = A.length();
        int lengthB = B.length();
        int D[][] = new int[lengthA + 1][lengthB + 1];

        int max = 0;
        for(int i = 1; i < lengthA + 1 ;i++){
            for(int j = 1; j < lengthB + 1; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    D[i][j] = D[i-1][j-1] + 1;
                    max = Math.max(max, D[i][j]);
                } else{
                    D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
                    max = Math.max(max, D[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
