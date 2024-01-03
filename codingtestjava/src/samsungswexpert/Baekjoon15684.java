package samsungswexpert;

/*
 (1) 인덱스 사다리 추가할 때
  -> 인덱스 순서대로 안나와 헷갈림

 (2) 처음부터 계속 사다리를 추가함
  - 마지막 위치 기록 후 사다리 놓아야 함 -> 즉, DFS 제대로 구현해야 함

  (3) 움직임 함수 구현 문제 
  -> 사다리가 같은 행렬에서 양 옆에 잇는경우 이동이 제대로 이루어지지 않음
   -> 해당 행에서 왼쪽 사다리칸 수 조사해서 해결

  (4) 사다리 밑에 칸에 설치할 때 맨 처음은 건너 뜀
  -> 첫 행만 이전 열 다음 위치에서 시작하고 다음 행부터는 0열부터 시작

  ----- 답지 참고 -----

  (5) 사다리 배열을 1, -1로 이동할 때 알아보기 편하게 설정

  (6) 사다리 설치 방식 변화
   -> 설치는 두 칸의 연속된 열에 해야하므로 설치 시작 위치를 마지막에서 -1칸으로 제한을 둠(97)
   -> dfs는 계속 처음부터 진행됨 : 단순한 진행을 위해!

  (7) 메소드 흐름을 dfs 위주로 play를 바깥으로 뺌
   -> 사다리 놓는 '위치'를 기준으로 설정하는게 아니라 사다리 '설치 개수'를 기준으로 설정  


 */

import java.io.*;

public class Baekjoon15684 {
    static int N;
    static int M;
    static int H;
    static int[][] ladders;
    static int answer;
    static boolean isFinish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        //세로선의 수
        N = Integer.parseInt(input[0]);
        //가로선의 수
        M = Integer.parseInt(input[1]);
        //세로선마다  가로선을 놓을 수 있는 위치의 개수
        H = Integer.parseInt(input[2]);

        ladders = new int[H][N];

        for(int i = 0; i < M; i++) {
            String[] lineInput = br.readLine().split(" ");
            //가로선 a에서 연결
            int a = Integer.parseInt(lineInput[0]);
            //세로선b와 b+1 을 연결
            int b = Integer.parseInt(lineInput[1]);

            ladders[a-1][b-1] = 1;
            ladders[a-1][b] = -1;
        }
        
        isFinish = false;

        for(int i = 0; i < 4; i++) {
            answer = i;
            addLadder(0);
            
            if(isFinish) {
                break;
            }
        }
    
        if(!isFinish) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }

    public static void addLadder(int depth) {
        System.out.println("addLadder");
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++){
                System.out.print(ladders[i][j] + " ");
            }
            System.out.println();
        }
        if(isFinish) {
            return;
        }

        if(answer == depth) {
            //사다리가 규칙대로 내려온다면 답을 출력하고 종료
            if(move()) {
                isFinish = true;
            }

            return;
        }

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N - 1; j++) {
                //인덱스를 만족하고 사다리를 설치할 수 있는 곳이라면 사다리 설치 후 다시 움직여본 뒤 백트래킹
                if(ladders[i][j] != 0 || ladders[i][j + 1] != 0) {
                    continue;
                }

                ladders[i][j] = 1;
                ladders[i][j + 1] = -1;

                addLadder(depth + 1);

                ladders[i][j] = 0;
                ladders[i][j + 1] = 0;
            }
        }
    }
    public static boolean move() {
        for(int i = 0; i < N; i++) {
            int row = 0;
            int col = i;

            while(row < H) {
                //사다리 왼쪽부분인 경우 오른쪽 위치로 이동 후 내려감
                if(ladders[row][col] > 0) {
                    col++;

                    row++;

                    continue;

                }
                //사다리 오른쪽 위치인 경우 왼쪽 부분으로 이동후 내려감
                if(ladders[row][col] < 0) {
                    col--;

                    row++;

                    continue;
                }
                //사다리가 없을 경우 그냥 내려감
                row++;
            }

            //마지막까지 이동이 끝난 후 col의 위치 비교
            if(col != i) {
                return false;
            }
        }
        
        return true;
    }
}
/*
//두 가로선은 연속하지 않고 접하지 않는다
 1 1 0 0 0 0 0
 1 1 1 1 1 1 0
 0 1 1 0 0
 0 0 0 0 0
 1 1 0 1 1
 0 0 0 0 0

 1 1 1 1 0
 2 2 1 1 0
 0 1 1 1 1
 0 1 1 0 0
 1 1 0 1 1
 2 2 0 0 0


*/
