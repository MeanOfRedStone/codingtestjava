package dynamic;


/*
-1 동적 계획법
 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
 N 값이 문제를 부분 문제로 나누는 역할을 해야함

 -2 나의 풀이
 가장 긴 것을 여러개로 나눠서 저장하려고 햇음
 하지만 그럴 필요 없이
 여러 비교를 통해 최댓값을 가져오도록 하면 됨
 
 최댓값 비교

 최솟값이 포인트인가?

 D[N][N] : 길이가 N일 M번째에서 시작하는 증가하는 수열의 길이

 D[N][M] 
 D[1][1] : 1
 D[2][1] : 2  D[2][2]= 1
 D[3][1] : 2  D[3][2] : 1 D[3][3] : 1
 D[4][1] : 3  D[4][2] : 
 D[1] : 10      -> 1
 D[2] : A[2] > A[1]  -> 10 20   -> 2  D[2] = D[1] + 1   | A[2] < A[1] 
 D[3] : A[3] > A[2] |  A[3] < A[2]  -> D[3] = [2]
 10 20 10 30 20 50

 10 80 5 6 70 80

 10 20  | 
 */
import java.util.*;

public class Baekjoon11053 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A[] = new int[N+1];
        for(int i = 1; i < N + 1; i++){
            A[i] = sc.nextInt();
        }
        
        int D[] = new int[N+1];
        for(int i = 1; i < N + 1; i++){
            D[i] = 1;
            for(int j = 1 ; j < i; j++){
                if(A[i] > A[j] && D[i] < D[j] + 1){
                    D[i] = D[j] + 1;
                }
            }
        }

        Arrays.sort(D);
        System.out.println(D[N]);
    }
}
