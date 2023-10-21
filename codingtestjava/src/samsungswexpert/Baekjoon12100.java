package samsungswexpert;
/*
 <백준 12100> 삼성 sw expert
 - dfs + 백트래킹으로 이동가능한 방향 조합 구함
 - 각 방향별로 이동
 - 최댓값 산출

 구현이 포인트! return, continue 잘 활용하자! -> while(true) 활용할 때 규칙 어긋나는 부분 있으면 먼저 return 시키기
 */

import java.util.*;
import java.io.*;

public class Baekjoon12100 {
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    static int N;
    static int board[][];

    static int directions[] = new int[5];

    static int maxBlock;

    static boolean visited[][];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.valueOf(br.readLine());

        board = new int[N][N];
        for(int i = 0; i < N; i++){
            String str[] = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.valueOf(str[j]);
            }
        }
        maxBlock = 0;
        DFS(5, 0);

        System.out.println(maxBlock);
    }

    public static void DFS(int end, int index){
        if(index == 5){
            confirm(board, directions);

            return ;
        }

        for(int i = 0; i < 4; i++){
            directions[index] = i;
            DFS(5, index + 1);
        }
    }

    public static void confirm(int board[][], int directions[]){
        int size = directions.length;

        //1, 원래 보드의 상태를 임시로 저장
        int copy[][] = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = board[i][j];
            }
        }


        //2.보드를 directions 배열에 맞게 움직인다.
        for(int i = 0; i < size; i++){
            visited = new boolean[N][N];
            if(directions[i] == 0){//아래 방향 이동
                for(int a = N - 1; a >= 0; a--){ 
                    for(int b = 0; b < N; b++){
                        if(board[a][b] != 0){
                            // System.out.println("아래 작동 : " + a + " , "+ b);
                            move(a, b, directions[i]);
                        }
                        
                    }
                }
            } else if(directions[i] == 1){ //오른쪽 이동
                for(int a = 0; a < N; a++){
                    for(int b = N -1; b >= 0; b--){
                        if(board[a][b] != 0){
                            // System.out.println("오른 작동 : " + a + " , "+ b);
                            move(a, b, directions[i]);
                        }
                    }
                }
            } else if(directions[i] == 2){//위로 이동
                for(int a = 0; a < N; a++){
                    for(int b = 0; b < N; b++){
                        if(board[a][b] != 0){
                            // System.out.println("위 작동 : " + a + " , "+ b);
                            move(a, b, directions[i]);
                        }
                    }
                }
            } else if(directions[i] == 3){ // 왼쪽으로 이동
                for(int a = 0; a < N; a++){
                    for(int b = 0; b < N; b++){
                        if(board[a][b] != 0){
                            // System.out.println("왼쪽 작동 : " + a + " , "+ b);
                            move(a, b, directions[i]);
                        }
                    }
                }
            }
        }
        
        //3.현 이동 경로에서의 최댓값
        int nowMax = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] != 0){
                    nowMax = Math.max(nowMax, board[i][j]);
                }
            }
        }

        //4. 최댓값 산출 후 보드를 다시 원상 복구
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                board[i][j] = copy[i][j];
            }
        }

        //5.현 이동 경로로 구한 보드의 최댓값과 기존의 최댓값을 비교해서 더 큰 값을 넣어준다.
        maxBlock = Math.max(maxBlock, nowMax);
    }

    public static void move(int row, int col, int direction){
        //1. 현재 블록의 정보
        int nowRow = row;
        int nowCol = col;
        int nowVal = board[row][col];

        int moveRow = nowRow;
        int moveCol = nowCol;
        // 2. 블럭을 이동 가능범위까지 이동 && 지나온 경로를 0으로 변경
        while(true){

            nowRow = moveRow;
            nowCol = moveCol;

            moveRow = moveRow + dx[direction];
            moveCol = moveCol + dy[direction];

            //정해진 인덱스의 범위를 벗어나는 경우 종료
            if(moveRow < 0 || moveRow > N - 1 || moveCol < 0 || moveCol > N - 1){
                moveRow -= dx[direction];
                moveCol -= dy[direction];
                return ;
            }

            //핵심 내가 오답이 난 부분
            if(visited[moveRow][moveCol]){
                return ;
            }
            //이동할 위치에 블럭이 있는 경우
            if(board[moveRow][moveCol] != 0){

                //3. 블럭의 크기에 따른 최종 위치 결정
                if(nowVal == board[moveRow][moveCol] ){ //블럭의 크기가 같은 경우 합침 후 종료 이후 다른 블럭은 방문 못함
                    board[moveRow][moveCol] = 2 * nowVal;
                    visited[moveRow][moveCol] = true;
                    board[nowRow][nowCol] = 0;
                    
                    return ;
                    
                } else if(nowVal != board[moveRow][moveCol]){ //같지 않을 경우 기존의 블럭보다 이동방향 기준 뒤쪽에 위치 후 종료
                    //이미 이전에 값을 바꿔줬기 때문에 뒤에 위치할 경우는 그냥 종료
                    return;
                }
            }

            //지나갈수 있다면 이전 경로 0으로 만들어준다.
            board[nowRow][nowCol] = 0;
            board[moveRow][moveCol] = nowVal;
            
        }

    }
}

