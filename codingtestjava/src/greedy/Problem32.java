
/*
 <문제 32 동전 개수의 최솟값 구하기>
 준규가 소유하고 있는 동전은 총 N종류이고, 각 동전의 개수가 많다. 동전을 적절히 사용해 그 가격의 합을 K로 만들려고한다.
 이 대 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
 */


/*
 <입력>
 1번째 줄 : N과 K
 2번째 줄부터: N개의 동전의 가격 주어짐. (1 <= Ai <= 1,000,000, A1 = 1, i>=2일때 Ai는 Ai-1의 배수)

 <출력>
 1번째 줄에 K원을 만드는 데 필요한 동전 개수의 최솟값을 출력한다.
 */

 /*
 <문제 분석>
 (1) 전형적인 그리디 문제. <돈 세기 유형> <그래프에서도 나온다>
 가격이 큰 동전부터 차례로 사용한다.

 (2) 큰 수부터 가격이 같거나 작을때까지 탐색. 
  */
package greedy;
import java.util.Scanner;

public class Problem32 {
    public static void main(String[] args){
        //입력
        int n, k;
        Scanner sc = new Scanner(System.in);
        //동전의 개수
        n = sc.nextInt();
        //가격의 합
        k = sc.nextInt();
        //소유한 동전의 가격 배열
        int[] coins = new int[n] ;
        //동전 가격 입력
        for(int i = 0; i < n; i++){
            coins[i] = sc.nextInt();
        }

        //k 보다 작은 동전의 인덱스 찾기
        int min;
        for(min = n - 1; min >= 0 && coins[min] > k; min--){
        }
        //인덱스부터 배열의 맨 처음까지 동전의 최소 갯수를 구한다.
        int index;
        int count = 0;
        for(index = min; index >= 0 && k != 0; index--){
            count += k / coins[index];
            k = k % coins[index];
        }
        //출력
        System.out.println(count);
    }
}
