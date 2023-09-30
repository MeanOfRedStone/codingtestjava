package searching;

/*
 <토마토>
 출제포인트
 -1 왜 BFS를 선택했는가?
 가장 짧은 방문 깊이(토마토가 모두 익은 날짜)를 찾는 문제였기 때문

 -2 생각해볼 점
 한 번에 여러 방문을 시작할 경우 BFS를 점 단위로 하지 말고
 모든 노드를 넣고 시작했다.
 */
import java.util.*;
public class Baekjoon7576 {
    static int dx[] = {1, 0 , -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int tomato[][];
    static boolean visited[][];
    static int M;
    static int N;
    static int finish;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //가로
        M = sc.nextInt();
        //세로
        N = sc.nextInt();
        tomato = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){ //행은 세로
            for(int j = 0; j < M; j++){//열은 가로
                tomato[i][j] = sc.nextInt();
            }
        }


        finish = 0;
        BFS();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j++){
                if(tomato[i][j] == 0){
                    System.out.println(-1);
                    return ;
                }
            }
        }
        //첫 날을 1부터 계산했기 때문에 1을 빼준다.
        if(finish != 0){
            System.out.println(finish - 1);
        } else{
            System.out.println(0);
        }
        
    }

    static void BFS(){
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(tomato[i][j] == 1){
                    visited[i][j] = true;
                    q.add(new int[] {i, j});
                }
            }
        }
        int day = 0;
        while(!q.isEmpty()){
            int now[] = q.poll(); 
            for(int i = 0;i < 4; i++){    
                int nearX = now[0] + dx[i];
                int nearY = now[1] + dy[i];
                //주변에 있는 방문하지 않은 토마토를 숙성 일자 표시 및 큐에 추가 
                if(nearX < N && nearX >= 0 && nearY < M && nearY >=0 && !visited[nearX][nearY] && tomato[nearX][nearY] == 0){
                    visited[nearX][nearY] = true;
                    day = tomato[nearX][nearY] = tomato[now[0]][now[1]] + 1;
                    q.add(new int[] {nearX, nearY});
                } 
            }
            finish = Math.max(day, finish);
        }

    }
}
