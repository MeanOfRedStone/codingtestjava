package dynamic;

/*
 -1 왜 동적 계획법이라고 생각했는지?
 좌 우로 나누어서 양 끝부터 길이가 N인 지점에서 최댓값을 모두 구해서
 더하면 된다고 생각했기 때문.
 즉 부분의 문제로 전체의 문제를 해결할 수 있다고 생각했기 때문이다.

 -2 음수일 때를 생각을 못함 -> sum의 모든 배열의 크기를 구함. 0 , N-1

 -3 가운데를 제거하는 것이 아니기 때문에 왼쪽에서부터 이어진 합, 오른쪽에서 이어진 합이 더 클 수가 있음

 */
import java.util.*;

public class Baekjoon1912 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A[] = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }

        int L[] = new int[N];
        L[0] = A[0];
        for(int i = 1; i < N; i++){
            L[i] = Math.max((L[i-1] + A[i]), A[i]);
        }

        int R[] = new int[N];
        R[N-1] = A[N-1];
        for(int i = N-2; i >= 0; i--){
            R[i] = Math.max((R[i+1] + A[i]), A[i]);
        }

        //음숭리
        int sum[] = new int[N];
        sum[0] = Math.max(L[0], R[0]);
        sum[N-1] = Math.max(R[N-1], L[N-1]);
        int biggest = Math.max(sum[0], sum[N-1]);
        for(int i = 1; i < N-1; i++){
            //왼쪽에서부터 이어져 온 합, 오른쪽부터 이어져 온 합, 왼쪽 오른쪽 이어진 합을 비교
            int temp = Math.max(L[i], R[i]);
            sum[i] = L[i] + R[i+1];
            biggest = Math.max(biggest, sum[i]);
        }

        System.out.println(biggest);
        
    }
}
