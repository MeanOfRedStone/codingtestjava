package samsungswexpert;

/*
 문자열 bool 확인은 contains

 BFS도 cnt 처리 리스트로. -> visited는 boolean

 *어떻게 같이 이동 시킬 것인가?
 -> 우선 이동 시키고 같은 위치에 있다면 이동 횟수로 구분
 -> + 이동 배열 2배로

 * 잘못된 케이스에서 계속 진행되는 경우는 어떻게 할 것인가?
 

 반복문에서 continue의 의미: 계속해서 경우를 세준다가 아니라 경우를 무시한다(건너 뛴다)는 뜻!!!!
 */
import java.util.*;
import java.io.*;

public class Baekjoon13460 {
    static String board[][];

    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};

    static int N;
    static int M;

    static boolean visited[][][][];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String size[] = br.readLine().split(" ");

        //보드의 세로
        N = Integer.valueOf(size[0]);
        //가로
        M = Integer.valueOf(size[1]);
        
        board = new String[N][M];

        visited = new boolean[N][M][N][M];

        for(int i = 0; i < N; i++){
            String str[] = br.readLine().split("");
            for(int j = 0; j < M; j++){
                board[i][j] = str[j];
            }
        }

        Queue<int[]> q = new LinkedList<>();

        int i = 0;
        while(q.size() != 2 && i < N){
            for(int j = 0; j < M; j++){
                if(board[i][j].contains("R")){
                    q.offer(new int[] {i, j, 1});
                } else if(board[i][j].contains("B")){
                    q.offer(new int[] {i, j, 0});
                }
            }
            i++;
        }

        int now[] = q.poll();
        int redRow = 0; int redCol = 0;
        int blueRow = 0; int blueCol = 0;
        if(now[2] == 1){
            redRow = now[0];
            redCol = now[1];
            int next[] = q.poll();
            blueRow = next[0];
            blueCol = next[1];
        } else{
            blueRow = now[0];
            blueCol = now[1];
            int next[] = q.poll();
            redRow = next[0];
            redCol = next[1];
        }
        int answer = findWay(redRow, redCol, blueRow, blueCol, 0);

        System.out.println(answer);
    }

    public static int findWay(int redRow, int redCol, int blueRow, int blueCol, int cnt){
        Queue<Beads> q = new LinkedList<>();
        visited[redRow][redCol][blueRow][blueCol] = true;
        Beads first = new Beads(redRow, redCol, blueRow, blueCol, cnt);
        q.add(first);
        
    
        int count = Integer.MAX_VALUE;
        //빨간 구슬이 구멍에 도달할때 까지 깊이를 저장하며 경로를 탐색
        //만약 경로가 10 초과라면 answer = -1 아니라면 answer = 1
        while(!q.isEmpty()){
            Beads present = q.poll();
            int presentRedRow = present.redRow;
            int presentRedCol = present.redCol;
            int presentBlueRow = present.blueRow;
            int presentBlueCol = present.blueCol;
            int presentCnt = present.cnt;

            for(int i = 0; i < 4; i++){
                int moveRedRow = presentRedRow;
                int moveRedCol = presentRedCol;
                int moveBlueRow = presentBlueRow;
                int moveBlueCol = presentBlueCol;
                int redMove = 0; int blueMove = 0;
                while(true){
                    moveRedRow = moveRedRow + dx[i];
                    moveRedCol = moveRedCol + dy[i];
                    if(board[moveRedRow][moveRedCol].contains("#")){
                        //현재 위치가 벽 또는 구멍이라면 이전위치를 마지막 위치로 저장하고 현재 방향으로 이동은 중지한다.
                        moveRedRow = moveRedRow - dx[i];
                        moveRedCol = moveRedCol - dy[i];
                        redMove -= 1;
                        break;
                    } else if(board[moveRedRow][moveRedCol].contains("O")){
                        break;
                    }
                    redMove += 1;
                }
                while(true){
                    moveBlueRow = moveBlueRow + dx[i];
                    moveBlueCol = moveBlueCol + dy[i];
                    if(board[moveBlueRow][moveBlueCol].contains("#")){
                        //현재 위치가 벽 또는 구멍이라면 이전위치를 마지막 위치로 저장하고 현재 방향으로 이동은 중지한다.
                        moveBlueRow = moveBlueRow - dx[i];
                        moveBlueCol = moveBlueCol - dy[i];
                        blueMove -= 1;
                        break;
                    } else if(board[moveBlueRow][moveBlueCol].contains("O")){
                        break;
                    }
                    blueMove += 1;
                }
                if(moveRedRow == moveBlueRow && moveRedCol == moveBlueCol && !board[moveRedRow][moveRedCol].contains("O")){ //0에 들어간 경우는 둘 다 넣어줘야한다.
                    if(redMove > blueMove){
                        moveRedRow -= dx[i];
                        moveRedCol -= dy[i];
                    } else{
                        moveBlueRow -= dx[i];
                        moveBlueCol -= dy[i];
                    }
                }
                if(board[moveBlueRow][moveBlueCol].contains("O")){
                    continue;
                }
                if(board[moveRedRow][moveRedCol].contains("O")){
                    count = Math.min(count, presentCnt + 1);
                }

                //방문하지 않는 곳이라면 깊이를 표시해주고 큐에 넣어서 다음 탐색이 진행할 수 있도록 한다.
                if(!visited[moveRedRow][moveRedCol][moveBlueRow][moveBlueCol]){
                    visited[moveRedRow][moveRedCol][moveBlueRow][moveBlueCol] = true;
                    q.offer(new Beads(moveRedRow, moveRedCol, moveBlueRow, moveBlueCol, presentCnt + 1));
                }
            }
        }

        if(count > 10 || count == Integer.MAX_VALUE){
            return -1;
        } else { return count; }
        
    }
}

class Beads {
    int redRow;
    int redCol;
    int blueRow;
    int blueCol;
    int cnt;

    public Beads(int redRow, int redCol, int blueRow, int blueCol, int cnt){
        this.redRow = redRow;
        this.redCol = redCol;
        this.blueRow = blueRow;
        this.blueCol = blueCol;
        this.cnt = cnt;
    }
}