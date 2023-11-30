package twoPointer;
/*
 <투 포인터>
 (1) 시작 인덱스, 종료 인덱스, sum
 
 (2) 이동 원칙
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
                printIndex("sum == N");
                continue;
            }

            if(sum > N){
                sum = sum - start_index;
                start_index++;
                printIndex("sum > N");
                continue;
            }

            if(sum < N) {
                end_index++;
                sum = sum + end_index;
                printIndex("sum < N");
                continue;
            }
        }
        System.out.println(count);
    }

    public static void printIndex(String status) {
        System.out.println(status + " : start_index, end_index, sum : " + start_index + ", " + end_index + ", " + sum);
    }
}
