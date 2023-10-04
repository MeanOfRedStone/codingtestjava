package dynamic;

/*
<백준11054 가장 긴 바이토닉 부분 수열>
(1)증가하는 부분 | 감소하는 부분으로 나누어서는 잘 생각했음
   -> but 구하는 알고리즘 찾는데 실패

(2) 해당 지점에서 합하면 가장 된다는 것을 증명 못함. -> 당연함 해당 값을 기준으로 왼쪽으론느 작아질수밖에 오른쪾으로는 커질수밖에

(3) 구현 부분이 문제! -> 전체 배열에서 예전 수 기준으로 계속 크기 비교어떻게 할것인가? 메모제이션을 이용해야 한다!!

*/
/*
               v    
 1 5 2 1 4 3 4 5 2 1
 0: 증가하는 수열 | 1 : 감소하는 수열
 1 100 105 109  5 4 3 2

 Increase[0] : 1 Decrease[] :  4
 Increase[1] : 2 Decrease[1] : 5
 Increase[2] : 2               4 
 Increase[3] : 2               4 
 Increase[4] : 2 Decrease[4] : 4
 Increase[5] : 3 Decrease[5] : 3
 Increase[6] : 4 Decrease[6] : 3
 Increase[7] : 5 Decrease[7] : 3
 Increase[8] : 5 Decrease[8] : 2
 Increase[9] : 5 Decrease[9] : 1


 Increase[0] = 0
 if(A[i] > A[i-1]){
    Increase[i] = Increase[i-1] + 1;
 } else{
    Intrease[i] = Math.max(Increase[i-1], for 반복문)
 }

 */

import java.util.*;
public class Baekjoon11054 {
	
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int A[] = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }


		int increase[] = new int[N];


        //증가하는 수열
        for(int i = 0; i < N; i++) {
            //모두 기본 값으로 1을 부여
            increase[i] = 1;
                
            // j : 0 ~ i-1 탐색
            for(int j = 0; j < i; j++) {
                // j번째 원소가 i번째 원소보다 작으면서 i번째 dp가 j번째 dp+1 값보다 작은경우
                // r_dp[i] < r_dp[j] + 1 : 이전 까지의 원소 중에서 차례로 순서를 비교할 수 있게 해준다
                // A[j] < A[i] 현재 수를 기준으로 작동하게 해줌
                // 합해서 현재 수를 기준으로 작은 원소들 중에서 차례로 순서를 비교할 수 있게 해준다

                if(A[j] < A[i] && increase[i] < increase[j] + 1) {
                    increase[i] = increase[j] + 1;	// j번째 원소의 +1 값이 i번째 dp가 된다.
                }
            }
        }

        int decrease[] = new int[N];
        //감소하는 수열
        for(int i = N-1; i >= 0; i--){
            decrease[i] = 1;

            for(int j = N-1; j > i; j--){
                if(A[i] > A[j] && decrease[j] + 1 > decrease[i]) {
                    decrease[i] = decrease[j] + 1;
                }
            }

        }
        int largest = 0;
        for(int i = 0; i < N; i++){
            largest = Math.max(largest, increase[i] + decrease[i] - 1);
        }
        System.out.println(largest);

    }
}
