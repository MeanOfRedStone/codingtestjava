package dynamic;

/*
 <문제86 이친수 구하기>
 0과 1로만 이루어진 수를 '이진수'라고 한다.
 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 '이친수'라고 한다.
 이친수는 다음의 성질을 만족한다.

 - 이친수는 0으로 시작하지 않는다.
 - 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉 11을 부분문자열로 갖지 않는다.

 예를 들면, 1, 10, 100, 101 등이 이친수가 된다.
 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
 N(1 <= N <= 90)이 주어졌을 때 N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
 */

/*
 <출제 포인트>
 (1) 동적 계획법의 이해 및 구현
 - 뭘 저장할지 잘 고민해보자 (작은 것부터 저장)
 -> 이전 경우의 수에 새로운 경우 더하기만 하면 됨
 */
/*
 N = 5
 10000
 10001
 10010
 10101
 10100

 N[5] : [] [] [] [] []
 (N[i] && N[i+1] = true)
 N[1] = true
 for(i = 1; i < N + 1)
 *i = 1
 count = i
for(j = 1; j < N && count > 1; j++)
    가능 조합: N[1] or N[2] or N[3] or N[4] = true
    if(! (N[j - 1]=true && N[j]=true))
        D[1] += 1;

 * D[2] : i가 2개일 때
   for(j = 1 ; j < N)


 N[0] = True

 N[1] = true (X)
 N[2] = false(o)

 N[2] = true(o)
 N[2] = false(o)


 D
 */


import java.util.*;



public class Question86 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        //D[i][0] : i길이에서 끝이 0으로 끝나는 이치수
        //D[i][1] : i길이에서 끝이 1로 끝나는 수
        long D[][] = new long[N+1][2];
        D[1][1] = 1; //1자리 이친수는 1 한가지
        D[1][0] = 0;

        for(int i = 2; i <= N; i++){
            D[i][0] = D[i -1][1] + D[i - 1][0];
            D[i][1] = D[i - 1][0];
        }
        System.out.println(D[N][0] + D[N][1]);
    }
}
