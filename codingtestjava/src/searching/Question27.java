package searching;

/*
 <문제27 미로 탐색하기>
 4 x 6 크기의 배열로 표현되는 다음과 같은 미로가 있다.
 미로의 각 칸에 들어 있는 숫자 중 1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸을 나타낸다.
 한 칸에서 다른 칸으로 이동할 대는 서로 인접한 칸으로만 이동할 수 있다.
 이동한 칸을 셀 때는 시작 위치와 도착 위치를 포함한다. 
 즉(1, 1)에서 (4, 6)으로 이동하려면 총 15칸을 지나가야 한다.
 N x M 크기의 미로가 주어질 때(1,1)에서 출발해(N, M)의 위치로 이동하기 위해 지나야 하는 칸 수의
 최솟값을 구하는 프로그램을 작성하시오.
 */

 /*
  <출제 포인트>
  (1) BFS 이해 및 구현 
  -1 최단거리 문제는 BFS를 활용해 구한다.
  (DFS는 처음 막힌 지점부터 파기 때문에 돌아간다.)
  (반면 BFS는 처음으로 다시 돌아가기 때문에 최단 경로를 찾을 수 있음)
  
  -2 BFS에서 깊이 측정은 최초 도달했을 때 깊이를 기록해준다.
    DFS 반면 DFS는 계속 깊이가 증가함(재귀 호출 하면서 break하기 떄문에 깊이 게속 더해가도 됨)

  (2) 자바 클래스 활용
  -1 띄어쓰기 없는 문자열 배열에 넣기 sc.next().split("")
  -2 큐 배열 정보 삽입 방법
  
  (3) 배열 미로 움직이는 방법
  -1 이동 위치 좌표를 생성해준다.
  */

import java.util.*;
public class Question27 {
    static int A[][];
    static int count;
    static boolean visited[][];
    static int N;
    static int M;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc. nextInt();

        //배열 생성
        A = new int[N][M];
        //연결리스트 초기화
        for(int i = 0; i < N; i++){
            String[] temp = sc.next().split("");
            for(int j = 0; j < M; j++){         
                A[i][j] = Integer.parseInt(temp[j]);
            }
        }
        //방문 노드 확인 배열 생성
        visited = new boolean[N][M];
        
        count = 0;
        BFS(0, 0);
        System.out.println(A[N-1][M-1]);
    }

    public static void BFS(int row, int col){
        Queue<int[]> q = new LinkedList<>();
        visited[row][col] = true;
        count += 1;
        q.offer(new int[] {row, col});

        while(!q.isEmpty()){
            int[] temp = q.poll();
            //현재 노드에서 주변에 가능한 노드를 큐에 넣어준다.
            for(int i = 0; i < 4; i++){
                int x = temp[0] + dx[i];
                int y = temp[1] + dy[i];
                if(x >= 0 && y >= 0 && x < N && y < M){ //좌표가 미로 안에 있는지
                    if(A[x][y] == 1 && !visited[x][y]){
                        visited[x][y] = true;
                        q.offer(new int[] {x, y});
                        A[x][y] = A[temp[0]][temp[1]] + 1; // 다음 노드에 방문한 횟수를 넣어줌 -> 경로가 되는 길은 깊이를 최소로 더해갈 수 있다.
                    }
                }
            }
        }
    }
}
