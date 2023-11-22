package shortTermGrowth;
/*
 *냅색 : 2차원 배열 활용한 대표적인 DP 문제
 -> 나는 무게를 활용하지 않아 가장 일렬로밖에 무게를 사용하지 못함
 -> 무게도 모두 DP를 활용하면 이를 해결할 수 있음 : 배열 자체 조건으로 무게 활용

 -> D[N][K]에서 최댓값 나올수 밖에 없음 무게 더 작은 경우를 더 큰 경우에 계속 갱신해주므로
 */


import java.io.*;

public class Baekjoon12865 {
    static int N;
    static int K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] initialInformation = br.readLine().split(" ");
        int N = Integer.parseInt(initialInformation[0]);
        int K = Integer.parseInt(initialInformation[1]);

        int[][] items = new int[N + 1][2];
        
        for(int i = 1; i < N + 1; i++){
            String[] itemInformation = br.readLine().split(" ");
            items[i][0] = Integer.parseInt(itemInformation[0]);
            items[i][1] = Integer.parseInt(itemInformation[1]);
        }

        int[][] D = new int[N + 1][K + 1];

        for(int i = 1; i < N + 1; i++){
            int weight = items[i][0];
            int value = items[i][1];

            for(int j = 1; j < K + 1; j++){
                D[i][j] = D[i - 1][j];

                if(j - weight >= 0){
                    D[i][j] = Math.max(D[i-1][j], D[i-1][j-weight] + value);
                }
            }
        }
        
        System.out.println(D[N][K]);
    }
}

/*가방 최대 : 7
무게 :  6  | 4 | 3 | 5
가치 :  13 | 8 | 6 | 12


13 | 13 | 14 | 

1. 이전 최댓값 + 현재 가치 -> 가능하다면 이 값으로 변경
2. 불가능하다면
0번째 가치 + 현재 가치 -> 이값으로 변경 | 불가능하다면 현재 가치로 변경
0번째 가치 + 1번째 가치 -> 이값으로 변경 | 1번째 가치 + 현재가치와 비교 | 불가능하다면 현재 가치로


D[0] = if(items[0][0] <= K){items[0][1]}

D[1] = if(itmes[0][0] + items[1][0] <= K){items[0][1] + items[1][1]} else{Math.max(D[0], items[1][1])}

D[2] = if(itmes)
 */
