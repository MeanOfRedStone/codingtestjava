package searching;

/*
 <문제 1987 알파벳>
 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 */
/*
 <출제 포인트>
 (1) DFS + 백트래킹
 -> 모든 방문 기록을 찾는 것이기 때문에 DFS
 -> 한 번 갔다고 다시 안찾는 것이 아니기 때문에 방문한 노드 다시 되돌려서 사이클 찾음 -> 백 트래킹

 (2) 알파벳 숫자 변형해서 사용하기 오지 String charAt형태에서만 int 변형이 가능하다.
    String letter = chess[x][y];
    char ch = letter.charAt(0);
    int letterCode = (int) ch -64;

 (3) DFS + 백트래킹 문제 25번
 */

import java.util.*;

public class Baekjoon1987 {
    static int R;
    static int C;
    static int max;
    static String chess[][];
    //A: 1 Z: 26
    static boolean visited[];

    static int dx[] = {1, -1 , 0, 0};
    static int dy[] = {0, 0, -1, 1};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        chess = new String[R + 1][C + 1];
        visited = new boolean[27];

        for(int i = 1; i < R + 1; i ++){
            String a[] = sc.next().split("");
            for(int j = 1; j < C + 1; j++){
                chess[i][j] = a[j-1];
            }
        }

        max = 0;
        
        DFS(1, 1,0);

        System.out.println(max);
    }

    static void DFS(int x, int y, int cnt){
        //입력받은 문자열 -> char -> 숫자
        String letter = chess[x][y];
        char ch = letter.charAt(0);
        int letterCode = (int) ch -64;
        
        if(visited[letterCode]){
            //알맞는 재귀 종료 원리에 의하면 여기에서 일괄적으로 끝나야 맞음. 뒤에 DFS삽입시에 미리 방문된 노드 거르는 건 맞지 않음 -> 백트래킹까지 고려해서도 여기가 맞음
            max = Math.max(max, cnt);
            return ;
        }

    
        visited[letterCode] = true;
        

        for(int i = 0; i < 4; i++){
            int visitX = x + dx[i];
            int visitY = y + dy[i];
            if(visitX >= 1 && visitX <= R && visitY >= 1 && visitY <= C){
                //깊이(cnt) 증가를 밖에 놔두면 cnt static이라 계속 증가한다. cnt는 변수 상에서만 증가하도록 해야함
                DFS(visitX, visitY, cnt + 1);
            }
        }
        //백트래킹 위해 한 방향의 DFS 끝나면 현재 방문한 노드 다시 방문할 수 있게 한다. 다른 사이클도 확인하기 위해서
        visited[letterCode] = false;
    }
}
