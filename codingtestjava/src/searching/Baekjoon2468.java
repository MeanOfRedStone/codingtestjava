package searching;

/*
 <안전 영역>
 -1 단절된 부분이 몇개인지 찾는 문제
 -> 깊이 우선 탐색

 */

import java.util.*;
public class Baekjoon2468 {
    static int A[][];
    static boolean visited[][];
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    static int N;
    static int height;
    static int tempHeight;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N][N];
        height = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = sc.nextInt();
                height = Math.max(height, A[i][j]);
            }
        }

        int max = 0;
        for(int h = 0; h < height; h++){
            int count = 0;
            tempHeight = h;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j] && A[i][j] > tempHeight){
                        count += 1;
                        DFS(i, j);
                    }
                }
            }
            max = Math.max(count, max);
        }
        
        System.out.println(max);
    }

    static void DFS(int x, int y){
        if(visited[x][y]){
            return ;
        }
        visited[x][y] = true;
        for(int d = 0; d < 4; d++){
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            if(tempX < N && tempY < N && tempX >= 0 && tempY >= 0 && !visited[tempX][tempY] && A[tempX][tempY] > tempHeight){
                DFS(tempX, tempY);
            }
        }
    }
}
