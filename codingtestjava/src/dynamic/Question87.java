package dynamic;
/*
 <문제87 2*N 타일 채우기>
 다음 그림은 2 x 5 크기의 직사각형을 채운 1가지 방법의 예다.
 이렇게 2 x n 크기의 직사각형을 1 x 2 또는 2 x 1 타일로 채우는 방법의 수를
 구하는 프로그램을 작성하시오.
 */

/*
 <출제 포인트>
 (1) 동적 계획법의 이해 및 구현
 -> 뭘 저장할지 잘 고민해보자 (작은 것부터 저장 : 보텀 업 방식)

 -> 경우의 수까지 다 구해봤는데 왜 나는 피보나치 수열이라는 것을 생각못했을 지 생각해보자
 */
/*
  _ _ _ _ _
 |         |
 |         |
 
  _ _        _
 |   |  or  | |
            | |


D[1] - 가로 길이 1일 때 경우의 수
세로

D[2]
세로, 세로
가로

D[3]
세로, 세로, 세로
가로,     , 세로
세로, 가로,     

D[4]
세로, 세로, 세로, 세로
세로, 세로, 가로
가로,     , 세로, 세로
가로,     , 가로
세로, 가로,    , 세로

D[5]
세로, 세로, 세로, 세로, 세로
세로, 세로, 가로,     , 세로
가로,     , 세로, 세로, 세로
가로,     , 가로,    , 세로


2세로 = 1가로
나머지와 몫을 활용해보자
몫 1 -> 경우의 수 2가지
나머지 1 -> 경우의 수 1가지

홀수로 끝날 경우

D[1] 조합 가지 수: 0묶음 1
|

D[2] 조합 가지수 : 0묶음 - 1개 , 1묶음 - 1개(나머지 0)
| | 
2가지

D[3] 조합 가지수 : 0묶음 - 1개, 1묶음 - 2개 (나머지 1)
| | |

D[4] 조합 가지수 : 0묶음 - 1개, 1묶음 - 3개, 2묶음 - 1개
| | | |

D[5] 조합 가지수 : 0묶음 - 1개, 1묶음 - 4개, 2묶음 - 3개
1 1 1 1 1

D[6] 조합 가지수 : 0묶음 -1개, 1묶음 - 5개, 2묶음 - 6개, 3묶음 - 1개
| | | | | |
*/
import java.util.*;

public class Question87 {
    static long mod = 10007;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long D[] = new long[1001];
        D[1] = 1;
        D[2] = 2;
        for(int i = 3; i <= N; i++){
            D[i] = (D[i-1] + D[i-2]) % mod;
        }
        System.out.println(D[N]);
    }
}
