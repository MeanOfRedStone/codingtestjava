package fail;

import java.util.*;

public class Baekjoon16236 {
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static boolean visited[][];
    static int sea[][];
    static int N;
    static int size;
    static int second;
    static int fish;
    static int minSize;
    static int lastX;
    static int lastY;
    static Queue<Integer> depths;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sea = new int[N][N];
        visited = new boolean[N][N];
        //남은 물고기를 세준다.
        fish = 0;
        minSize = 9999;
        int fishX = -1;
        int fishY = -1;
        lastX = -1;
        lastY = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sea[i][j] = sc.nextInt();
                if(sea[i][j] > 0 && sea[i][j] <= 6){
                    fish += 1;
                    minSize = Math.min(minSize, sea[i][j]);
                    fishX = i;
                    fishY = j;
                } else if(sea[i][j] == 9){
                    lastX = i;
                    lastY = j;
                }
            }
        } 
        size = 2;
        second = 0;
        depths = new LinkedList<>();
        // 첫 방문
        if(fish > 1 && size > minSize){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(sea[i][j] == 9){
                        BFS(i, j);
                        break;
                    }
                }
            }

            //잡을 물고기의 깊이를 더 해준다.
            second += depths.remove();
        
        } else if(fish == 1 && size > minSize){
            second = Math.abs(fishX - lastX) + Math.abs(fishY - lastY);
            fish -= 1;
        }

        
        

        // 이 후 방문
        while(fish != 0){
            //남은 물고기의 최소 사이즈 구함
            minSize = 9999;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(sea[i][j] > 0 && sea[i][j] <= 6){
                        minSize = Math.min(minSize, sea[i][j]);
                        fishX = i;
                        fishY = j;
                    }
                }
            }
            //물고기를 먹을 수 있는 상태
            if(size > minSize){
                if(fish > 1){
                    BFS(lastX, lastY);
                    //잡을 물고기의 깊이를 더 해준다.
                    second += depths.remove();
                } else{
                    fish -= 1;
                    second = second + Math.abs(lastX - fishX) + Math.abs(lastY- fishY);
                }
            } else{ // 물고기를 못 먹는 상태
                break;
            }
        }
        // System.out.println(minSize);
        System.out.println(second);
    }

    static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[] {x, y});
        /*
             * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
                먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
                먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
                거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
                거리가 가까운 물고기가 많다면, 가장 위에 있는  물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
        */
        int depth = 0;
        //물고기가 1마리보다 많을 경우에 계속 찾아다닌다
        while(!q.isEmpty() && depths.isEmpty()){
            //상어의 현재 위치
            int now[] = q.poll();
            lastX = now[0];
            lastY = now[1];
            
            //깊이 증가
            depth += 1;


            for(int i = 0; i < 4; i++){
                int tempX = now[0] + dx[i];
                int tempY = now[1] + dy[i];
                
                if(tempX >= 0 && tempX < N && tempY >= 0 && tempY < N && !visited[tempX][tempY] && sea[tempX][tempY] <= size){
                    visited[tempX][tempY] = true;
                    q.add(new int[] {tempX, tempY});
                    //잡아먹을 수 있는 물고기는 0으로 바꿔 준다.
                    sea[tempX][tempY] = 0;
                    fish -= 1;
                    size += 1;
                    depths.add(depth);
                    break;
                }
            }

        }
        //최단거리로 물고기를 먹을 수 있는 것이 끝나면 방문 표시를 초기화 해준다.
        visited = new boolean[N][N];
    }
}
