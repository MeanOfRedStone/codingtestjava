package greedy;
/*
 각 도시에 있는 주유소의 기름 가격과, 각 도시를 연결하는 도로의 길이를 입력으로 받아 
 제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용을 계산하는 프로그램을 작성하시오.
 */

 /*
  <출제 포인트>
  (1) 왜 그리디 알고리즘 ??
  현재 최적해를 선택하여 적합한 결과를 도출한다.

  (2) 제약 조건 주의
  정수가 과하다 싶으면 long으로 넘어가자
  */

  /*
    4
    2 3 1
    5 2 4 1
    총 이동거리 : 6km

       2     3     1
    5  -  2  -  4  -  1

    정렬을 해

    5원 x 2 + 2원 x 4

    총 이동거리: 10km
       3     3     4
    1  -  1  -  1  -  1

       3     5     2     4
    5  -  3  -  4  -  2  -  1     
    현재부터 다음번 주요소들까지의 가격을 비교해보자
    int min = oilPrice[0];
    for(int i = 0; i < N-1; i++){
        price = oilPrice[i];
        if(price <= min){
            sum += price * distance[i]
            min = price;
        } else{
            sum += price * distance[i]
        }
    }

    5원 * 3 + 3원 * 5 + 4원 * 2 + 2원 * 4
    5원 * 3 + 3원 * 7
   */


import java.util.*;

public class Baekjoon13305 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long distance[] = new long[N-1];
        for(int i = 0; i < N-1; i++){
            distance[i] = sc.nextLong();
        }
        long oilPrice[] = new long[N];
        for(int i = 0; i < N; i++){
            oilPrice[i] = sc.nextLong();
        }
        
        long min = oilPrice[0];
        long sum = 0;
        for(int i = 0; i < N-1; i++){
            long price = oilPrice[i];
            if(price <= min){ //현재 도시에서 기름값이 최솟값보다 싼경우 현재 도시의 기름값으로 다음도시까지의 기름을 구입
                sum += price * distance[i];
                min = price;
            } else{//현재 도시의 기름값보다 최솟값이 더 싼경우 최솟값으로 현재 도시부터 다음도시까지의 기름을 구입
                sum += min * distance[i];

            }
        }
        System.out.println(sum);
    }
}
