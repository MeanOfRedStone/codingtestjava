package searching;

/*
 <이진탐색>
 탐색 숫자 같지 않을 경우
 index + 1 or index - 1 해줘야함
 그대로 옮겨주면 무한 루프 됨
 */

import java.io.*;
import java.util.*;

public class Baekjoon1920 {
    static List<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] number = new long[N];
        String[] numberInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            number[i] = Long.parseLong(numberInput[i]);
        }

        int M = Integer.parseInt(br.readLine());
        long[] findNumber = new long[M];
        String[] findNumberInput = br.readLine().split(" ");
        for(int i = 0; i < M; i++) {
            findNumber[i] = Long.parseLong(findNumberInput[i]);
        }

        Arrays.sort(number);

        answer = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            find(number, findNumber[i]);
        }

        for(int num : answer) {
            System.out.println(num);
        }
    }

    public static void find(long[] number, long findNumber) {
        int start = 0;
        int end = number.length - 1;
        int index = (start + end) / 2;

        while(start <= end) {
            long checkNumber = number[index];

            if(checkNumber > findNumber) {
                end = index - 1;
                index = (start + end) / 2;
                continue;
            }

            if(checkNumber < findNumber) {
                start = index + 1;
                index = (start + end) / 2;
                continue;
            }

            if(checkNumber == findNumber) {
                answer.add(1);
                return;
            }
        }
        answer.add(0);
    }
}
