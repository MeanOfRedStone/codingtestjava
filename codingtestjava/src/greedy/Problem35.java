package greedy;

/*
 <문제 35>
 1개의 회의실에서 N개의 회의를 진행하기 위해 회의실 사용표를 만들려고 한다. 
 각 회의마다 시작 시간과 끝나는 시간이 주어질 때 회의 시간이 겹치지 않으면서 회의를 가장 많이 진행하려면 최대 몇 번까지 할 수 있는지 알아보자.
 단, 회의를 시작하면 중간에 중단할 수 없고, 한 회의를 끝내는 것과 동시에 다음 회의를 시작할 수 있다.
 회의의 시작 시간과 끝나는 시간이 같을 수도 있는데, 이때는 시작하자마자 끝나는 것으로 생각하면 된다.
 */

 /*
  <출제 포인트>
  (1)그리디 알고리즘에 대한 이해 -> 직면한 가장 좋은 방법을 사용한다. 
   -1 종료 시간이 적은 것부터 찾는다 -> 현재 회의의 종료 시간이 빠를수록 다음 회의와 겹치지 않고 사용 가능
   -2 종료 시간이 같을 경우 시작 시간을 기준이 빠른 것을 기준으로 다시 한번 정렬(더 많이 겹쳐서 회의 할 가능성이 더 큼)
   -3 순차적으로 탐색하다가 시간이 겹치지 않는 회의가 나오면 선택한다. (이전 회의의 종료시간과 다음 회의의 시작시간을 비교한다.)
  (2)2차원 배열에 대한 이해와 사용 -> 자료구조 활용
  (3)sort 기준 comparator로 재정의 할 수 있는지

  <내가 생각하지 못했던점>
  (1) 그리디 알고리즘: 
  -1 어떤 것이 최선일지 고민(나는 회의 시간이 짧은 것 기준으로 했음 하지만 여기서는 종료시간이 빠른 것을 기준으로 함)
  -2 시작점이 앞의 회의의 종료시간보다 빠르면 추가하지 않는다.
  (2) 자료구조 : -1 2차원 배열 | -2 2차원 배열을 비교하는 Comparator 함수
  */



import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


public class Problem35 {
  public static void main(String[] args){
    //입력
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[][] Time = new int[N][2];
    for(int i = 0; i < N; i++){
      Time[i][0] = sc.nextInt();
      Time[i][1] = sc.nextInt();
    }

    //시간 순서대로 배열 정렬
    //Comparator <> 비교할 타입 지정 우리는 int[]를 비교
    Arrays.sort(Time, new Comparator<int[]>() {
      //2차원 배열 중 2개를 비교하는 것임
      @Override
      public int compare(int[] S, int[] E){
        //종료 시간이 같을 경우
        if(S[1] == E[1]){
          return S[0] - E[0]; //빼줄 경우 양수 이면 S[] 값이 더 큰 것을 의미한다. 즉 시작 시간을 기준으로 다시 비교한다는 말.
        }
        return S[1] - E[1];
      }
    });

    
    int count = 0;
    int end = -1;
    for(int i = 0; i < N; i++){
      if(Time[i][0] > end){
        count += 1;
        end = Time[i][1];
      }
    }

    System.out.println(count);

  }
    
}
