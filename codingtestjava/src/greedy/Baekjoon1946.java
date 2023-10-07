package greedy;


/*
 -1 그리디
 두 개 등수 비교
 1개 정렬 후
 이후 최적해 어떻게 찾을 것인가

 -2 BuffereReader 사용법
 BufferedReader br = new BufferedReader(new IntputStreamer(System.in));
 int X = Integer.valueOf(br.readLine());

 여러개 받을 떄
 String str[] = br.readLine().split(" ");
 int X = Integer.valueOf(str[0]);
 ...

 */
/* 
    5
 * 3 2
 * 1 4
 * 4 1
 * 2 3
 * 5 5
 * 
 * 1 4
 * 2 3
 * 3 2
 * 4 1
 * 5 5
 */

 /*
  * 7
   1 4
   2 5
   3 6
   4 2
   5 7
   6 1
   7 3
    3 6 - x
    7 3 - x
    4 2 - o
    1 4 - o
    5 7 - x
    2 5 - x
    6 1 - o

    하나씩 비교하면 서 최적해를 만족하는 사람만 정답에 넣어줌
  */

import java.util.*;
import java.io.*;

public class Baekjoon1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.valueOf(br.readLine());
        ArrayList<Integer> answer = new ArrayList<>();

        for(int a = 0; a < T; a++){
            //지원자 수
            int N = Integer.valueOf(br.readLine());
            //지원자 성적 보관 리스트
            int list[][] = new int[N][2];


            for(int i = 0; i < N; i++){
                String str[] = br.readLine().split(" ");
                int paper = Integer.valueOf(str[0]);
                int interview = Integer.valueOf(str[1]);
                list[i][0] = paper;
                list[i][1] = interview;
            }

            //우선 서류전형을 오름차순 정렬함
            Arrays.sort(list, new Comparator<int[]> () {
                @Override
                public int compare(int o1[], int o2[]){
                    return o1[0] - o2[0];
                }
            });

            int sum = N;
            //서류 1등의 면접 등수
            int score = list[0][1];

            for(int i = 1; i < N; i++){
                //비교 대상의 면접 등수
                int compare = list[i][1];
                
                if(score < compare){
                    sum = sum -1;
                } else{ //만약 현재 등수의 사람이 탈락하지 않는다면 그 사람을 기준으로 비교 어짜피 서류는 정렬 되있으니
                    score = list[i][1];
                }
            }

            answer.add(sum);
        }
        
        for(int i : answer){
            System.out.println(i);
        }
    }
}
