package searching;

/*
 모든 경로의 수 찾기 DP + DFS
 -> 새로운 문제 유형

 dp 탑다운 방식
 목표지점만 방문 표시 안되게 해야한다.
 */

import java.io.*;

public class Baekojoon1520 {
    static int M;
    static int N;
    //지도, 방문
    static int map[][];


    //이동
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    //DP 배열
    static int D[][];



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String size[] = br.readLine().split(" ");
        M = Integer.valueOf(size[0]);
        N = Integer.valueOf(size[1]);


        map = new int[M][N];

        D = new int[M][N];
        for(int i = 0; i < M; i++){
            String height[] = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.valueOf(height[j]);
                D[i][j] = -1;
            }
        }
        
        System.out.println(DFS(0, 0));


    }

    public static int DFS(int x, int y){
        //방문한 경우에도 경로를 쌓아주면 안된다
        if(x == M - 1 && y == N -1){
           return 1;
        }

        if(D[x][y] == -1){
            D[x][y] = 0;

            for(int i = 0; i < 4 ; i++){
                int xNow = x + dx[i];
                int yNow = y + dy[i];

                //탑-다운 방식 DP
                if(xNow >= 0 && xNow < M && yNow >= 0 && yNow < N && (map[x][y] > map[xNow][yNow])){
                    //뒤에서부터 차근차근 경로를 쌓는다 -> 경로 분기되도 배열에 계속 쌓인다.
                    D[x][y] += DFS(xNow, yNow);
                }
            }

        }
        
        return D[x][y];

    }
}
