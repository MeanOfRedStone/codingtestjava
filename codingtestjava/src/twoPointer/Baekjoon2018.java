package twoPointer;
/*
 <투 포인터>
 (1) 시작 인덱스, 종료 인덱스, sum
    index는 부분합의 범위를 나타내준다.

 (2) 투포인트의 시간 복잡도는 O(N) 이다

 (3) 이동 원칙
  sum > N : sum = sum - start_index; start_index++;
  sum < N : end_index++; sum = sum + end_index;
  sum == N : end_index++; sum = sum + end_index; count++;
 */

import java.io.*;

public class Baekjoon2018{
    static int start_index;
    static int end_index;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 숫자 == N 인경우 먼저 포함해 놓는다
        int count = 1;
        start_index = 1;
        end_index = 1;
        sum = 1;
        
        while(end_index != N){
            if(sum == N) {
                count++;
                end_index++;
                sum = sum + end_index;
                continue;
            }

            if(sum > N){
                sum = sum - start_index;
                start_index++;
                continue;
            }

            if(sum < N) {
                end_index++;
                sum = sum + end_index;
                continue;
            }
        }
        System.out.println(count);
    }
}
