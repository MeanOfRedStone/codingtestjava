package searching;

/*
 flag + DFS 활용
 (1) flag를 활용하여 while 반복문을 조건 하에 계속 실행
  -Melting 메소드, DFS 계속 실행

 (2) Melting 메소드 실행될때마다 빙하가 녹고 , years += 1;
  - 빙하가 녹는 것을 기록하기 위해 복사할 배열 생성
  - 복사할 배열에서 녹을 양을 체크하고
  - 기존 배열에서는 빙하를 제거해준다.

  (3) DFS
  - 분절을 파악하기 위해
 */

import java.io.*;
import java.util.*;

public class Baekjoon2573 {
    static int N;
    static int M;
    static int map[][];

    //이동
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    static boolean visited[][];

    //빙산이 녹는데 걸리는 시간
    static int years;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String size[] = br.readLine().split(" ");
        N = Integer.valueOf(size[0]);
        M = Integer.valueOf(size[1]);

        map = new int[N][M];
        

        for(int i = 0; i <N ;i++){
            String height[] = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.valueOf(height[j]);
            }
        }

        years = 0;
        int visit;
        while(true){
            visited = new boolean[N][M];
            Melting();
            visit = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] != 0 && !visited[i][j]){
                        DFS(i, j);
                        visit += 1;
                    }
                }
            }
            // System.out.println("visit: " + visit);
            if(visit != 1){
                break;
            }
        }

        if(visit == 0){
            System.out.println(visit);
        } else {
            System.out.println(years);
        }
    }

    public static void Melting(){
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0){
                    // System.out.println("빙산 위치 : " + i + " ," + j);
                    q.add(new int[] {i, j});
                }
            }
        }
        //녹는 빙하를 체크하기 위한 배열
        int check[][] = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                check[i][j] = map[i][j];
            }
        }

        // System.out.println("빙산");
        // for(int i = 0 ; i < N; i++){
        //     for(int j = 0; j < M; j++){
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        while(!q.isEmpty()){
            int now[] = q.poll();
            int rowNow = now[0];
            int colNow = now[1];

            int sea = 0;
            for(int i = 0; i < 4; i++){
                int rowNear = rowNow + dx[i];
                int colNear = colNow + dy[i];
                if(rowNear >= 0 && rowNear < N && colNear >= 0 && colNear < M && check[rowNear][colNear] == 0){
                    sea += 1;
                }

            }
            
            map[rowNow][colNow] -= sea;
            if(map[rowNow][colNow] < 0){
                map[rowNow][colNow] = 0;
            } 
        }
        years += 1;

    }

    public static void DFS(int x, int y){
        if(visited[x][y] == true){
            return;
        }

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nearX = x + dx[i];
            int nearY = y + dy[i];

            if(nearX >= 0 && nearX < N && nearY >= 0 && nearY < M && map[nearX][nearY] != 0){
                DFS(nearX, nearY);
            }
        }
    }
    
}
