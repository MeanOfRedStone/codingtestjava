package dynamic;
/*
<전깃줄>
 철거되어야 할 전 선 개수 = 전체 전선 개수 - 최대한 겹치지 않게 설치가능한 개수

 "최대한 겹치지 않게 설치 가능한 개수를 찾아보자"

  A전봇대 기준으로 i번째에 연결된 전깃줄을 잇고 이후 전선들을 탐색하면서 
  i번째가 연결된 B의 값보다 큰 경우들을 모두 탐색해보는 것이다. 
  
  (1)
  결국 정렬된 A를 기준으로 B에 연결된 값들에서 LIS를 하면 된다는 것이다.

  (2) Arrays.sort 2차원 배열 적용

    Arrays.sort(arr, new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0]-o2[0]; // 첫번째 숫자 기준 오름차순 {1,30}{2,10}{3,50}{4,20}{5,40}
            //return o2[0]-o1[0]; // 첫번째 숫자 기준 내림차순 {5,40}{4,20}{3,50}{2,10}{1,30}
            //return o1[1]-o2[1]; // 두번째 숫자 기준 오름차순 {2,10}{4,20}{1,30}{5,40}{3,50}
            //return o2[1]-o1[1]; // 두번째 숫자 기준 내림차순 {3,50}{5,40}{1,30}{4,20}{2,10}
        }
    });
 
 */

import java.util.*;

public class Baekjoon2565 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //총 설치하는 전선의 개수
        int N = sc.nextInt();
        //전봇대 [i][0] : i번째로 A 전봇대에 설치하는 위치 
        //[i][1] : i번째로 B 전봇대에 설치하는 위치
        int wire[][] = new int[N + 1][2];
        //해당 위치까지 가장 많이 설치할 수 있는 전깃줄 개수
        int D[] = new int[N + 1];

        for(int i = 1; i < N + 1; i++){
            wire[i][0] = sc.nextInt();
            wire[i][1] = sc.nextInt();
        }

        //A 전봇대 기준으로 배열 정리(Arrays.sort -> 1차원 배열만 정렬)
        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });


        for(int i = 1; i < N + 1; i++){
            D[i] = 1;
            /*
             * i번째 전봇대를 기준으로 이전 위치의
             * 전선을 연결하기 위한 탐색
             * 즉, i번째 전봇대에 연결된 B 전봇대는
             * 탐색할 j번째 전봇대에 연결된 B전봇대보다 값이 커야한다.
             */
            //순서대로 배열 됏으니 인덱스 순서대로 전봇대 배치됨

            //현재 전봇대 위치 이전 위치 에서 B위치에 연결된 것도 이전인 것들
            for(int j = 1; j < i; j++){
                if(wire[i][1] > wire[j][1]){
                    D[i] = Math.max(D[i], D[j] + 1);
                }
            }
        }

        int max = 0;
            for(int i = 1; i < N + 1; i++){
                max = Math.max(max, D[i]);
            }

            //전체 개수 - 설치 가능한 전깃줄 = 최소 절거 개수
            System.out.println(N - max);
        
    }
}



