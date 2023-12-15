package searching;
/*
 <이진 탐색>
 (1) 어떤 것을 start, end로 삼을 지 고민해봐야함
 (2) 진행 과정은 똑같음

 <디버깅 포인트>
 마지막 저장할 때 sum이 0이 안됨
 */


import java.io.*;

public class Baekjoon2343 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] informationInput = br.readLine().split(" ");
        //강의의 개수
        int N = Integer.parseInt(informationInput[0]);
        //만들고자 하는 블루레이의 개수
        int M = Integer.parseInt(informationInput[1]);

        int[] lectures = new int[N];
        int start = 0;
        int end = 0;

        String[] lectureInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(lectureInput[i]);
            start = Math.max(start, lectures[i]);
            end += lectures[i];
        }

        int answer = 0;

        while(start <= end) {
            int middle = (start + end) / 2;
            int sum = 0;
            int count = 0;

            for(int i = 0; i < N; i++) {
                if(sum + lectures[i] > middle) {
                    count++;
                    sum = 0;
                }
                sum += lectures[i];
            }

            if(sum != 0) {
                count++;
            }

            if(count <= M) {
                answer = middle;
                end = middle - 1;
            }

            if(count > M) {
                start = middle + 1;
            }
        }

        System.out.println(answer);
    }
}

/*
9 3
1 2 3 4 5 6 7 8 9

start : 최대 레슨 시간
end : 레슨 시간을 모두 합친 것
 */
