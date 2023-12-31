package searching;

/*
 <문제>
 신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 
 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.

예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 
1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다.
하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.

어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 
컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.
 */

 /*
  <출제 포인트>
  (1) 왜 깊이 우선 탐색인가?
  - 단절이 안된 깊이를 찾는 문제이기 때문
  */
import java.util.*;

public class Baekjoon2606 {
    static ArrayList<Integer>[] A;
    static boolean visited[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //컴퓨터의 수
        int N = sc.nextInt();
        //직접 연결되어 있는 컴퓨터 쌍의 수
        int C = sc.nextInt();
        //컴퓨터 인접 리스트
        A = new ArrayList[N+1];
        //방문한 노드 표시
        visited = new boolean[N+1];
        //인접리스트 초기화
        for(int i = 1; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        //연결되 컴퓨터 인접 리스트에 추가
        for(int i = 0; i < C; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            A[s].add(e);
            A[e].add(s);
        }
        DFS(1);
        int count = 0;
        for(int i = 1; i < N + 1; i++){
            if(visited[i] == true){
                count += 1;
            }
        }
        //1번 컴퓨터는 제외
        System.out.println(count - 1);
    }
    public static void  DFS(int v){
        if(visited[v] == true){
            return ;
        }
        visited[v] = true;
        for(int i : A[v]){
            if(visited[i] == false){
                DFS(i);
            }
        }
    }
}
