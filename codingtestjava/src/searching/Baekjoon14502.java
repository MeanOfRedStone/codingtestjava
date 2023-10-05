package searching;


/*
 0 - 빈 칸 | 1 - 벽 | 2 - 바이러스

 (1) 벽 3개를 세워서 안전구역(0) 가장 크게 만들기. 벽을 어떻게 세울 것인가?
 -> 벽 세우기는 DFS + 백 트래킹
 모든 지점에 벽을 세우고 벽 세우고 BFS 시켜봄!

 백 트래킹(모든 경로 확인해보기)

 (2) 바이러스 퍼뜨리기는 BFS
 -> 벽 세울때마다 지도 복사해서 퍼뜨려봄

 (3) 방문 배열을 꼭 서야하는지 잘 고민해보자
 */
/*
2 3 4 5 1 1 4
3 4 1 6 1 2 3
4 1 1 7 1 3 4
5 1 7 6 5 4 5
6 7 8 7 6 1 1
7 1 9 8 7 8 9
8 1 10 9 8 9 10


8 7 6 5 4 3
1 6 5 4 3 2
1 1 1 4 3 2
7 6 5 4 3 2
 */

import java.util.*;
public class Baekjoon14502 {
    //연구소의 크기
    static int N;
    static int M;
    //연구소
    static int lab[][];

    //바이러스 퍼지는 것을 확인하기 위한 지도
    static int virusMap[][];

    //이동
    static int dx[] = {1, 0 , -1, 0};
    static int dy[] = {0, 1, 0, -1};

    //최대 면적
    static int max;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //연구소의 크기
        N = sc.nextInt();
        M = sc.nextInt();

        //연구소의 크기
        lab = new int[N][M];

        //연구소 상태 입력
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                lab[i][j] = sc.nextInt();
            }
        }

        max = 0;
        DFS(0);
        
        System.out.println(max);
    }

    // 벽세우기
    public static void DFS(int wall) {
        //벽 세우기가 완료될때마다 바이러스 퍼뜨림
        if (wall == 3) {
            BFS();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    DFS(wall + 1);
                    //백트래킹(모든 벽 세우기 경우를 탐색하기 위해) 위해 방문한 곳 다시 해제해준다.
                    lab[i][j] = 0;
                }
            }
        }
    }


    //벽을 세울때마다 바이러스를 퍼뜨려서 최댓값을 찾는다
    public static void BFS(){
        Queue<int[]> q = new LinkedList<>();

        //벽 세우기를 완료한 모든 경로마다 새로 복사해줘서 바이러스를 퍼뜨린다.
        virusMap = new int[N][M];

        //지도 복사
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                virusMap[i][j] = lab[i][j];
            }
        }

        //지도에서 바이러스 있는 곳 찾기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(lab[i][j] == 2){
                    q.add(new int[] {i, j});
                }
            }
        }

        //바이러스를 퍼뜨리는 작업
        while(!q.isEmpty()){
            int now[] = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i = 0; i < 4; i++){
                int tempX = nowX + dx[i];
                int tempY = nowY + dy[i];

                if(tempX >= 0 && tempX < N && tempY >= 0 && tempY < M && virusMap[tempX][tempY] == 0){
                    virusMap[tempX][tempY] = 2;
                    q.add(new int[] {tempX, tempY});
                }
            }
        }
        //바이러스 퍼뜨리고 남은 안저한 곳의 위치를 찾는다.
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(virusMap[i][j] == 0){
                    count += 1;
                }
            }
        }
        //안전한 곳 위치의 최댓값을 구해줌
        max = Math.max(max, count);
    }
}
