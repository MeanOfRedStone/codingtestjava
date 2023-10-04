package greedy;

import java.util.*;
/*
 <백준 16953 A -> B>
    정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.
    2를 곱한다.
    1을 수의 가장 오른쪽에 추가한다. 
    A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.
 */

 /*
  -1 그리디?
  ~ 하는데 최솟값을 구하여라 : 최적해

  -2. 연산 2개는 배타적 (1) -1 , /10 (2) / 2 -> 하나는 무조건 홀수 하나는 무조건 짝수여야만 가능하다.
  */

/*
 B (1) A로 나눈다
   
   (2) 2로 나눈다
   (3) - 1 & / 10을 해준다

 A    B
 2 -> 162
 
 (1) 81
 (2) 8
 (3) 4
 (4) 2

 4 -> 42

 (1) 21
 (2) 2
 x

100 -> 40021

 (1) 4002
 (2) 2001
 (3) 200
 (4) 100
 
 cnt = 1;
 wihle(B >= A){
    if(B % 2 == 0){
        B = B / 2
        count += 1;
    } else if( (B -1) % 10 == 0 ){
        B = (B - 1) / 10
        count += 1;
    } else {
        break;
    }
 }

 if(A == B){
    System.out.println(cnt);
 } else{
    System.out.println(-1);
 }


 
 100 → 200 → 2001 → 4002 → 40021
 */
public class Beakjoon16953 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        int cnt = 1;
        while(B > A){
            if(B % 2 == 0){
                B = B / 2;
                cnt += 1;
            } else if( (B -1) % 10 == 0 ){
                B = (B - 1) / 10;
                cnt += 1;
            } else {
                break;
            }
        }

        if(A == B){
            System.out.println(cnt);
        } else{
            System.out.println(-1);
        }
    }
}
