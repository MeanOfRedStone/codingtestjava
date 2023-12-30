package searching;

/*
 1. K번째 수를 찾는 방법
  -> 모르는 접근 방식이었음
  -> 구구단을 떠올려보자 행으로 나눈 개수(몫) 만큼 작은수가 존재하는 것

 2. 마지막에 start, end 변화 시키는 부분
  - count > K
   -> middle보다 작은 수가 K보다 크므로 해당 값은 아님

   - count == K
    -> middle 보다 작은 수가 K개 이므로 end값 더 더 작아지면 더이상 answer 값은 변화되지 않음

 3. 이분 탐색도 특정한 조건의 값을 찾는 탐색이다
즉 도구이다!
 */

import java.io.*;

public class Baekjoon1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int start = 1;
        int end = K;
        int answer = 0;

        while(start <= end) {
            int middle = (start + end) / 2;
            int count = 0;

            for(int i = 1; i <= N; i++) {
                if(middle / i > N) {
                    count += N;
                    continue;
                }

                count += middle / i;
            }

            if(count < K) {
                start = middle + 1;
                continue;
            }

            if(count >= K) {
                answer = middle;
                end = middle - 1;
                continue;
            }
        }

        System.out.println(answer);
    }
}
