package greedy;

/*
-0 이전문제 1, 2 ,3 더하기 해설
(1, 2, 3) 이 고정인게 핵심 -> 피보나치 뭐 이런거 알아도 이 힌트에 민감하게 반응하지 못하면 절때 케이스 분류할 수 없다
 포도주 시식이날 계단 오르기는 단순히 합하기만 하면 됐잖아. 근데 이거는 이 규칙을 알아야만 합할 수 있다.


-1 왜 그리디?
최적해
가장 걔 중 가장 큰 것 가장 작은 것
그 다음 가장 큰 것 가장 작은 것 계속 찾음

 A, B 곱해서 최솟값
 A - 0 1 1 1 6
 B - 2 7 8 3 1
 visited[N]

visited
 f | f | f | f | f

(1)
B max = 8
index = 2
visited[2]

beore = A[0]
after = A[2]

A[0] = after
A[2] = before

(2)

for(int i = 0; i < N; i++){
    int max = -1;
    for(j = 0; j < N; j++){
        if(!visited[j]){
            max = Math.max(B[j], max);
        }
    }
    int index = -1;
    for(j = 0; j < N; j++){
        if(max == B[j]){
            index = j;
            visited[j] = true;
            break;
        }
    }
    int before = A[i];
    int after = A[j];

    A[i] = after;
    A[j] = before;
}
  


 */


import java.util.*;
public class Baekjoon1026 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //A, B 수열의 길이
        int N = sc.nextInt();
        //A, B 수열
        int A[] = new int[N];
        Integer B[] = new Integer[N];

        boolean visited[] = new boolean[N];

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++){
            B[i] = sc.nextInt();
        }
        Arrays.sort(A);

        Arrays.sort(B, Collections.reverseOrder());
        
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += A[i] * B[i];
        }
        
        System.out.println(sum);
    }
}





/* 문제 이상함 -> 이풀이는 잠시 보관
 for(int i = 0; i < N; i++){
            int max = -1;
            for(int j = 0; j < N; j++){
                if(!visited[j]){
                    max = Math.max(B[j], max);
                }
            }

            int index = -1;
            for(int j = 0; j < N; j++){
                if(max == B[j]){
                    index = j;
                    visited[index] = true;
                    break;
                }
            }
            int now = A[i];
            C[index] = now;
        }
 */