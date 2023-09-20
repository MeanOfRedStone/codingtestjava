package searching;

/*
 <문제24 신기한 소수 찾기>
 수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 이용해 노는 것이다.
 요즘 수빈이가 가장 관심 있어 하는 소수는 7331이다. 
 7331은 신기하게도 733도 소수, 73도 소수, 7도 소수다.
 즉, 왼쪽부터 1자리, 2자리, 3자리, 4자리수 모두 소수다.
 수빈이는 이런 숫자를 신기한 소수라고 이름 붙였다.
 수빈이는 N의 자리의 숫자 중 어떤 수들이 신기한 소수인지 궁금해졌다. 
 숫자 N이 주어졌을 때 N의 자리 숫자 중 신기한 소수를 모두 찾아보자.
 */

/*
 <출제 포인트>
 (1) DFS에 대한 이해
 -> 연결리스트가 아닌 방법으로 재귀함수를 만드는 방법 : 자릿수 늘어나는 숫자의 경우 | 
    visited 배열이 아닌 자릿수로 종료 컨트롤
 (2) 소수에 대한 이해
 */

 /*
 <몰랐던 점>
 (1) 어떻게 재귀함수 만들 것인가? break 포인트가 중요.
  1 -> 1 * 10 + i -> (1 * 10 + i) * 10 + i 로 재귀함수
 (2) 소수 구하는 법
 -1 자기 자신 / 2까지 나누어보며 나눠지면 소수가 아님
 -2 2번째 자리부터 2로나누어지면 소수 아님

 <숫자 자리> - 깊이 우선 탐색
  */

import java.util.*;

public class Question24 {
    static int N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //일의 자리 소수는 2,3, 5, 6이므로 4개 수에서만 시작
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    static void DFS(int number, int jarisu){
        if(jarisu == N){
            if(isPrime(number)){
                System.out.println(number); //마지막 자리수에서 종료하기 위해
            }
            return; //소수가 아닌경우 재귀호출 종료
        }
        for(int i = 1; i < 10; i += 2){ //끝자리가 0인 경우는 어짜피 짝수이므로 고려 x
            if(isPrime(number * 10 + i)){ // 소수라면 재귀 함수로 자릿수를 늘림
                DFS(number * 10 + i, jarisu  + 1);
            }
        }
    }
    static boolean isPrime(int num){
        //
        for(int i = 2; i < num / 2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
