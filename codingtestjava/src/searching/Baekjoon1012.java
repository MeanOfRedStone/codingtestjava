package searching;

/*
 <출제 포인트>
 (1) 왜 DFS라 생각했는지?
 -> 단절점(배추벌레의 수)를 찾는 것이기 때문
 (2) 주의 사항
  DFS 방향 이동 시 제발 인덱스 주의좀 0보다 커야함
 */

import java.util.*;
public class Baekjoon1012 {
    static int cabbage[][];
    static boolean visited[][];
    static int dx[] = {0 , 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    static int N;
    static int M;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        ArrayList<Integer> test = new ArrayList<>();
            for(int t = 0; t < T; t++){
                //가로 길이
            M = sc.nextInt();
            //세로 길이
            N = sc.nextInt();
            int K = sc.nextInt();
            cabbage = new int[M][N];
            visited = new boolean[M][N];


            for(int i = 0; i < K; i++){
                int X = sc.nextInt();
                int Y = sc.nextInt();
                cabbage[X][Y] = 1;
            }

            int bugsCount = 0;
            for(int i = 0; i < M; i++){
                for(int j = 0; j < N; j++){
                    if(cabbage[i][j] == 1 && !visited[i][j]){
                        bugsCount += 1;
                        DFS(i, j);
                    }
                }
            }
            test.add(bugsCount);
        }
        for(int i : test){
            System.out.println(i);
        }
    }
    static void DFS(int x, int y){
        //방문했을 경우 종료
        if(visited[x][y]){
            return;
        }
        //방문을 표시
        visited[x][y] = true;

        //배추가 있고 방문하지 않은 곳이라면 DFS
        for(int i = 0; i < 4; i++){
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if(tempX >= 0 && tempY >= 0 && tempX < M && tempY < N && cabbage[tempX][tempY] == 1 && !visited[tempX][tempY]){
                DFS(tempX, tempY);
            }
            
        }
    }
}
