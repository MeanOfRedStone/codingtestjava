package searching;
/*
 <문제> 영역 구하기
 */

import java.util.*;

public class Baekjoon2583 {
    //모눈종이
    static int paper[][];

    //방문 표시
    static boolean visited[][];
    //이동
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};

    static int colStart, rowStart, colEnd, rowEnd;

    static int M, N;

    static int sum;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //행
        M = sc.nextInt();
        //열
        N = sc.nextInt();
        //직사각형의 수
        int K = sc.nextInt();

        //모눈종이
        paper = new int[M][N];

        //방문 표시
        visited = new boolean[M][N];

        //사각형 보관
        Square list[] = new Square[K];
        //직사각형의 좌표 입력
        for(int i = 0; i < K; i++){
            //왼쪽 아래 꼭지점
            int xLeft = sc.nextInt();
            int yLeft = sc.nextInt();

            int xRight = sc.nextInt();
            int yRight = sc.nextInt();

            Square item = new Square(xLeft, yLeft, xRight, yRight);

            list[i] = item;
        }
        int cnt = 1;
        //사각형 탐색 DFS
        for(int i = 0; i < K; i++){
            Square square = list[i];
            colStart = square.xLeft;
            rowStart = square.yLeft;

            colEnd = square.xRight - 1;
            rowEnd = square.yRight - 1;
            
            DFS(rowStart, colStart, cnt);
            //다음 사각형 방문 표시 위해 방문 배열 초기화
            visited = new boolean[M][N]; 
            cnt += 1;
        }
        System.out.println("사각형 그리기 후");
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                System.out.print(paper[i][j] + " ");
            }
            System.out.println();
        }

        //0으로 이루어진 영역의 수
        int zeroCount = 0;
        //0으로 이루어진 영역의 넓이 저장
        ArrayList<Integer> zeroSum = new ArrayList<>();

        //칠해지지 않는 부분의 DFS
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && paper[i][j] == 0){
                    System.out.println("방문 지점 : " + i + " ," + j);
                    sum = 0;
                    zeroCount += 1;
                    DFSzero(i, j);
                    zeroSum.add(sum);
                }
            }
        }

        System.out.println(zeroCount);
        Collections.sort(zeroSum);
        for(int x : zeroSum){
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("0 탐색 후");
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                System.out.print(paper[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void DFS(int row, int col, int cnt){
        if(visited[row][col]){
            return;
        }

        visited[row][col] = true;

        for(int i = 0; i < 4; i++){
            int rowMove = row + dx[i];
            int colMove = col + dy[i];
            if(rowMove >= rowStart && rowMove <= rowEnd && colMove >= colStart && colMove <= colEnd){
                paper[rowMove][colMove] = cnt;
                DFS(rowMove, colMove, cnt);
            }

        }
    }

    public static void DFSzero(int x, int y){
        if(visited[x][y]){
            return;
        }

        visited[x][y] = true;
        paper[x][y] = 9;
        sum += 1;
        for(int i = 0; i < 4; i++){
            int X= x + dx[i];
            int Y = y + dy[i];
            if(X >= 0 && X < M && Y >= 0 && Y < N && paper[X][Y] == 0){
                DFSzero(X, Y);
            }
        }
    }

    
}

class Square{
    int xLeft;
    int yLeft;

    int xRight;
    int yRight;

    public Square(int xLeft, int yLeft, int xRight, int yRight){
        this.xLeft = xLeft;
        this.yLeft = yLeft;
        this.xRight = xRight;
        this.yRight = yRight;
    }
}