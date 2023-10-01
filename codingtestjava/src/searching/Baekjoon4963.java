package searching;

/*
 <출제 포인트>
 -1 왜 DFS로 품?
 단절이 몇번 했는지 확인하는 문제이기 때문

 -2 엔드 포인트 설정
 break로
 */

import java.util.*;

public class Baekjoon4963 {
	static boolean visited[][];
	static int map[][];
    static int dx[] = {0, 1, 0, -1, 1, -1, 1, -1};
    static int dy[] = {1, 0, -1, 0, 1, 1, -1, -1};
    static int h;
    static int w;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> counts = new ArrayList<>();
        while(true){
            w = sc.nextInt();
            h = sc.nextInt();
            if(w == 0 && h == 0){
                break;
            }
            map = new int[h][w];
            visited = new boolean[h][w];

            for(int i = 0; i < h; i++){
                for(int j =0; j < w; j++){
                    map[i][j] = sc.nextInt();
                }    
            }

            int count = 0;
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    if(!visited[i][j] && map[i][j] == 1){
                        count += 1;
                        DFS(i, j);
                    }
                }
            }
            counts.add(count);
        }
        for(int i : counts){
            System.out.println(i);
        }
    }

    static void DFS(int x, int y){
        if(visited[x][y]){
            return ;
        }
        visited[x][y] = true;

        for(int i = 0; i < 8; i++){
            int X = x + dx[i];
            int Y = y + dy[i];
            if(X >= 0 && X < h && Y >= 0 && Y < w && !visited[X][Y] && map[X][Y] == 1){
                DFS(X, Y);
            }
        }
    }
}