package prefixSum;

/*
 <2차원 구간합> -> DP랑 비슷하네
 배열 합을 어떻게 구성할 것인지

 [구간합] : 외우자 행, 열 모두 합해주는 것이라고 _ 행으로도 계산되고 열로도 구간합이 계산되기 위해
 D[i][j] = D[i][j-1] + D[i-1][j] - D[i-1][j-1] + A[i][j]
 -> 행으로도 더하고 열로도 더하고 공통부분 빼주고 + 현재 값 더해주고

 [질의 값]
 D[3][4] - D[1][4] - D[3][1] + D[1][1] = 27


 */


import java.io.*;

public class Baekjoon11660 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] informationInput = br.readLine().split(" ");
        //표의 크기
        int N = Integer.parseInt(informationInput[0]);
        //합을 구하는 횟수
        int M = Integer.parseInt(informationInput[1]);

        int[][] board = new int[N + 1][N + 1];
        
        for(int i = 1; i < N + 1; i++){
            String[] numberInput = br.readLine().split(" ");
            for(int j = 1; j < N + 1; j++){
                board[i][j] = Integer.parseInt(numberInput[j - 1]);
            }
        }

        int[][] prefixSumCoordinate = new int[M][4];

        for(int i = 0; i < M; i++){
            String[] coordinateInput = br.readLine().split(" ");
            for(int j = 0; j < 4; j++){
                prefixSumCoordinate[i][j] = Integer.parseInt(coordinateInput[j]);
            }
        }

        int[][] prefixSum = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < N + 1; j++){
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + board[i][j];
            }
        }

        for(int i = 0; i < M; i++){
            int x1 = prefixSumCoordinate[i][0];
            int y1 = prefixSumCoordinate[i][1];
            
            int x2 = prefixSumCoordinate[i][2];
            int y2 = prefixSumCoordinate[i][3];

            int answer = prefixSum[x2][y2] - prefixSum[x2][y1 - 1] - prefixSum[x1 - 1][y2] + prefixSum[x1 - 1][y1 - 1];
            
            System.out.println(answer);
        }

    }
}
