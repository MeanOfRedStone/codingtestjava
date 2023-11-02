package samsungswexpert;

/*
 구현 문제
 List를 활용한 피스의 이동

 조건 꼼꼼히 확인하기
 (1) 방향 전환시 회전 요소를 잘 못해서
 (2) 벽에 부딪히는 조건을 잘못 적어서
 -> 시간 매우 오래걸림...

 문제 이해
 (1) 초에 도달한다는 것을 기존 초에서 계속 증가해야한다고 생각했음 -> 문제 읽어보면 그런 말은 안나옴. 초에 도달하면 종료한다고 함
 (2) 방향전환은 초가 끝나면 진행한다고 했음. 이런 기준점을 잘 생각하자 헷갈렸다!!

 */


import java.io.*;
import java.util.*;

public class Baekjoon3190 {
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //보드의 크기
        N = Integer.parseInt(br.readLine());
        //사과의 개수
        int K = Integer.parseInt(br.readLine());

        //보드 초기화
        board = new int[N+1][N+1];

        for(int i = 0; i < K; i++){
            String[] appleInput = br.readLine().split(" ");
            int row = Integer.parseInt(appleInput[0]);
            int col = Integer.parseInt(appleInput[1]);
            board[row][col] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<String[]> snakeMove = new LinkedList<>();

        for(int i = 0; i < L; i++){
            String[] move = br.readLine().split(" ");
            String second = move[0];
            String rotation = move[1];

            snakeMove.add(new String[] {second, rotation});
        }

        int answer = play(board, snakeMove);

        System.out.println(answer);
    }
    /*
    <조건>
1. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 
2. 뱀은 처음에 오른쪽을 향한다. (뱀은 초마다 이동) (보드의 상하좌우 끝에 벽)
     * 
     * <기능>
1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
2. 만약 벽이나 부딪히면 게임이 끝난다.
3. 만약 자기자신의 몸과 부딪히면 게임이 끝난다. -> 다음 움직임 장소에 뱀의 몸이 있으면 정지
4. 방향 변환 정보에 따라 초가 지나면 전환
     * D: 시계 방향 90도, L 반시계 방향 90도
     * 
     * <출력>
     * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
     */


    //총 2가지 기능 |
    public static int play(int[][] board, Queue<String[]> snakeMove){
        //출력
        int second = 0;

        //뱀의 회전 조건
        int termSecond = 0;
        char nextMove = '?';

        if(!snakeMove.isEmpty()){
            String[] term = snakeMove.poll();
            termSecond = Integer.parseInt(term[0]);
            nextMove = term[1].charAt(0);
        }

        //뱀의 상태 조건
        int moveIndex = 0; // 현재 움직임 방향

        //조건 1 (뱀의 머리)
        List<int[]> snake = new ArrayList<>();

        int headRow = 1;
        int headCol = 1;

        snake.add(new int[] {headRow, headCol});

        // (뱀의 몸통 위치)
        board[headRow][headCol] = -1;

        while(true){ //조건2 벽고 부딪힐시 게임 끝남 
            //0) 초 증가
            second++;

            //기능 1 1초마다 먼저 머리의 길이를 늘려 다음칸에 이동(시작할때 오른쪽 방향)
            //1) 머리 늘이기
            headRow = headRow + dx[moveIndex];
            headCol = headCol + dy[moveIndex];

            if(headRow < 1 || headRow > N || headCol < 1 || headCol > N){ // 벽과 부딪힐 경우 roof 종료
                break;
            }

            if(board[headRow][headCol] == -1){
                break;
            }

            //새로운 머리의 위치를 뱀 리스트에 포함시킴
            snake.add(new int[] {headRow, headCol});

            if(board[headRow][headCol] == 1){ //사과가 있을 경우 그대로 뱀의 길이 증가
                board[headRow][headCol] = -1;
            } else if(board[headRow][headCol] == 0){//이 외의 경우 마지막 꼬리 부분을 줄여서 길이를 유지한다
                board[headRow][headCol] = -1;
                int[] remove = snake.remove(0);
                int removeRow = remove[0];
                int removeCol = remove[1];

                board[removeRow][removeCol] = 0;
            }

            //조건 4 방향 변환 정보에 따라 움직임 방향 변화
            //4 - 1) 정해진 초가 끝날 때 뱀의 움직임 방향을 변화한다.
            if(second == termSecond){
                if(nextMove == 'D'){
                    moveIndex = (moveIndex + 1) % 4;
                } else if(nextMove == 'L'){
                    moveIndex -= 1;
                    if(moveIndex == -1){
                        moveIndex = 3;
                    }
                }
            }

            //4 - 2) 조건 정보가 남아있고뱀의 방향 전환 정보를 정해진 초가 다다를때마다 뺀다 초는 기존의 초에서 지난 게 아니다! 문제 잘못 이해한것
            if(!snakeMove.isEmpty() && second == termSecond){
                String[] term = snakeMove.poll();
                termSecond = Integer.parseInt(term[0]);
                nextMove = term[1].charAt(0);
            }
        }

        return second;
    }
}
