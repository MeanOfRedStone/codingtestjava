package searching;

import java.util.*;
import java.io.*;

public class Baekjoon2206 {
    static int N;
    static int M;
    static int map[][];

    static int visited[][];

    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};

    static int shortest;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String size[] = br.readLine().split(" ");

        N = Integer.valueOf(size[0]);
        M = Integer.valueOf(size[1]);

        map = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++){
            String element[] = br.readLine().split("");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.valueOf(element[j]);
            }
        }
        shortest = 1000000;
        move(0, 0);
        if(shortest != 1000000){
            System.out.println(shortest);
        } else{
            System.out.println(-1);
        }
        
    }

    public static void move(int x, int y){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1){
                    map[i][j] = 0;
                    BFS(x, y);
                    //백트래킹: 부순 한 개의 벽은 다음 탐색을 위해 되돌린다.
                    map[i][j] = 1;
                    //다음 방문을 위해 방문 배열 초기화
                    visited = new int[N][M];
                }
                
            }
        }
    }

    public static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = 1;
        int count = 0;

        while(!q.isEmpty()){
            int now[] = q.poll();
            int rowNow = now[0];
            int colNow = now[1];
            if(rowNow == (N - 1) && colNow ==( M - 1)){
                count = visited[rowNow][colNow];
                break;
            }
            for(int i = 0; i < 4; i++){
                int rowMove = rowNow + dx[i];
                int colMove = colNow + dy[i];

                if(rowMove >= 0 && rowMove < N && colMove >= 0 && colMove < M && map[rowMove][colMove] == 0){
                    if(visited[rowMove][colMove] == 0){
                        q.offer(new int[] {rowMove, colMove});
                        visited[rowMove][colMove] = visited[rowNow][colNow] + 1;
                    }
                }
            }
        }
        if(count != 0){
            shortest = Math.min(shortest, count);
        }
    }
}
