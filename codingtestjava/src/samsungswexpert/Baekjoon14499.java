package samsungswexpert;

/*
 <디버깅 상황>
 (1) 아웃오브 어레이
 -> 0부터 시작해서 N 초과하려면 >= N 부터임

 (2) 주사위 굴러갈시 항상 4면이 바뀌어야 함
 */
 /*


     지도의 좌표 (r, c) : 행, 렬

     처음 주사위의 위치 (x, y)

     처음 주사위의 모든 면은 0

     <주사위 굴리기 규칙>
     (1) 보드의 숫자가 0 -> 주사위 바닥에 있는 수가 복사
     (2) 보드의 숫자가 0이 아닌 경우 -> 칸에 있는 수가 주사위 바닥면으로 복사

     <주어진 조건>
     (1) 주사위를 놓은 좌표 (x, y)
     (2) 이동시키는 명령

     <구해야할 것>
     (1) 상단에 쓰여 있는 값

     <예외사항>
     (1) 보드 밖으로 이동시키는 명령은 무시 -> 출력하면 안된다.

     <참고>
     이동시키는 명령에 따른 주사위 위치의 변경
       2
     4 1 3
       5
       6

       2
     3 1 4
       5
       6
     */
        /*
    <구현사항>
    현재 위치 : x, y
    for(명령 : 명령집합)
      1. 다음 위치 결정
      2. 위치가 예외사항에 해당하면 continue
      3. 현재 위치 <- 다음 위치
      4. 주사위 이동
      5. 현재 위치 보드값 구함
      6. 주사위 아랫면의 값 구함
      7. 주사위 굴리기 규칙에 따른 주사위 바닥면의 값 변경
      8. 주사위 상단의 값 출력
     */
import java.util.*;
import java.io.*;

public class Baekjoon14499 {
    static int[][] board;
    static int[] cast;
    static int[][] castDirection = new int[4][3];

    static int N;
    static int M;
    static int x;
    static int y;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        //세로크기
        N = Integer.parseInt(input[0]);
        ///가로 크기
        M = Integer.parseInt(input[1]);
        //주사위를 놓은 고의 좌표
        x = Integer.parseInt(input[2]);
        y = Integer.parseInt(input[3]);
        //명령의 개수
        int K = Integer.parseInt(input[4]);

        board = new int[N][M];
        cast = new int[] {0, 0, 0, 0, 0, 0, 0};
        
        int[] castInput1 = new int[] {0, 2, 0};
        int[] castInput2 = new int[] {4, 1, 3};
        int[] castInput3 = new int[] {0, 5, 0};
        int[] castInput4 = new int[] {0, 6, 0};
        for(int i = 0; i < 3; i++){
            castDirection[0][i] = castInput1[i];
        }
        for(int i = 0; i < 3; i++){
            castDirection[1][i] = castInput2[i];
        }
        for(int i = 0; i < 3; i++){
            castDirection[2][i] = castInput3[i];
        }
        for(int i = 0; i < 3; i++){
            castDirection[3][i] = castInput4[i];
        }

        for(int i = 0; i < N; i++){
            String[] mapInput = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(mapInput[j]);
            }
        }

        List<Integer> orders = new ArrayList<>();
        
        String[] orderInput = br.readLine().split(" ");
        for(int i = 0; i < K; i++){
            int order = Integer.parseInt(orderInput[i]);
            orders.add(order);
        }

        play(orders);
    }

    public static void play(List<Integer> orders){
        int nowRow = x;
        int nowCol = y;
        for(int order : orders){
            //1. 다음 위치 결정
            int moveRow = nowRow + dx[order];
            int moveCol = nowCol + dy[order];

            //2. 위치가 예외사항에 해당하면 continue
            if(moveRow < 0 || moveRow >= N || moveCol < 0 || moveCol >= M){
                continue;
            }

            //3. 현재 위치 <- 다음위치
            nowRow = moveRow;
            nowCol = moveCol;

            //4. 주사위 이동
            if(order == 1 || order ==2){ // 동서 이동
                int topVal = castDirection[1][1];
                int leftVal = castDirection[1][0];
                int rightVal = castDirection[1][2];
                int bottomVal = castDirection[3][1];

                if(order == 1){ //보드 동쪽으로 이동 시 주사위 값 오른쪽으로 이동
                    castDirection[1][1] = leftVal;
                    castDirection[1][0] = bottomVal;
                    castDirection[1][2] = topVal;
                    castDirection[3][1] = rightVal; 
                } else if(order == 2){
                    castDirection[1][1] = rightVal;
                    castDirection[1][0] = topVal;
                    castDirection[1][2] = bottomVal;
                    castDirection[3][1] = leftVal; 

                }
            } else if(order == 3 || order == 4 ){ //남북 이동
                int firstVal = castDirection[0][1];
                int secondVal = castDirection[1][1];
                int thirdVal = castDirection[2][1];
                int fourthVal = castDirection[3][1];

                if(order == 3){ //보드 북쪽으로 이동 시 주사위 값 위쪽으로 이동
                    castDirection[0][1] = secondVal;
                    castDirection[1][1] = thirdVal;
                    castDirection[2][1] = fourthVal;
                    castDirection[3][1] = firstVal;
                } else if(order == 4){ // 보드 남쪽으로 이동 시 주사위 값 아래쪽으로 이동
                    castDirection[0][1] = fourthVal;
                    castDirection[1][1] = firstVal;
                    castDirection[2][1] = secondVal;
                    castDirection[3][1] = thirdVal;
                }
            }

            // 5. 현재 위치 보드값 구함
            int boardVal = board[nowRow][nowCol];

            // 6. 주사위 아랫면의 값 구함
            int castBottomIndex = castDirection[3][1];
            int castVal = cast[castBottomIndex];

            // 7. 주사위 굴리기 규칙에 따른 주사위 바닥면의 값 변경
            if(boardVal == 0){
                board[nowRow][nowCol] = castVal;
            } else if(boardVal != 0){
                cast[castBottomIndex] = boardVal;
            }

            //8. 주사위 상단의 값 출력
            int castTopIndex = castDirection[1][1];
            int printCast = cast[castTopIndex];
   
            System.out.println(printCast);
        }
    }
   
}
