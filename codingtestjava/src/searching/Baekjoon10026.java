package searching;

/*
 (1) 깊이 우선 탐색
 - 단절 지점 확인해서 몇 개 영역을 분리 되어있는지.

 (2) 케이스
 -1 R ,G ,B 따로
 -2 (R, G) , B

 (3) Array, ArrayList -> Array 사용
 -> 그래프가 아니므로 Array 사용

 (4) 자바 String 동일 체크는 contains 이다
 */

import java.util.*;

public class Baekjoon10026 {
    static String picture[][];
    static boolean visited[][];

    //이동
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    //picture 크기
    static int N;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        //그림 Array의 크기 지정
        picture = new String[N][N];
        //방문 배열의 크기 지정
        visited = new boolean[N][N];

        //picture에 색깔 담기
        for(int i = 0; i < N; i++){
            String colors[] = sc.next().split("");
            for(int j = 0;j < N; j++){
                picture[i][j] = colors[j];
            }
        }

        int redCount = 0;
        //빨간 색상구역을 찾는 DFS
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && picture[i][j].contains("R")){
                    DFSRed(i, j);
                    redCount += 1;
                }
            }
        }
        //다른 색상 찾기 위해 방문 배열 초기화
        visited = new boolean[N][N];

        int greenCount = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && picture[i][j].contains("G")){
                    DFSGreen(i, j);
                    greenCount += 1;
                }
            }
        }

        visited = new boolean[N][N];

        int blueCount = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && picture[i][j].contains("B")){
                    DFSBlue(i, j);
                    blueCount += 1;
                }
            }
        }

        visited = new boolean[N][N];
        int abnormalCount = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && (picture[i][j].contains("R") || picture[i][j].contains("G"))){
                    DFSAbnormal(i, j);
                    abnormalCount += 1;
                }
            }
        }

        int normal = redCount + blueCount + greenCount;
        int abnormal = blueCount + abnormalCount;

        System.out.print(normal + " " + abnormal);

    }

    static void DFSRed(int x , int y){
        if(visited[x][y]){
            return ;
        }

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int tempX = x + dx[i];
            int tempY = y + dy[i];

            if(tempX >= 0 && tempY >= 0 && tempX < N && tempY < N && picture[tempX][tempY].contains("R")){
                DFSRed(tempX, tempY);
            }
        }
    }

    static void DFSGreen(int x , int y){
        if(visited[x][y]){
            return ;
        }

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int tempX = x + dx[i];
            int tempY = y + dy[i];

            if(tempX >= 0 && tempY >= 0 && tempX < N && tempY < N && picture[tempX][tempY].contains("G") ){
                DFSGreen(tempX, tempY);
            }
        }
    }

    static void DFSBlue(int x , int y){
        if(visited[x][y]){
            return ;
        }

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int tempX = x + dx[i];
            int tempY = y + dy[i];

            if(tempX >= 0 && tempY >= 0 && tempX < N && tempY < N && picture[tempX][tempY].contains("B")){
                DFSBlue(tempX, tempY);
            }
        }
    }

    static void DFSAbnormal(int x , int y){
        if(visited[x][y]){
            return ;
        }

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int tempX = x + dx[i];
            int tempY = y + dy[i];

            if(tempX >= 0 && tempY >= 0 && tempX < N && tempY < N && (picture[tempX][tempY].contains("R") || picture[tempX][tempY].contains("G") )){
                DFSAbnormal(tempX, tempY);
            }
        }
    }
}
