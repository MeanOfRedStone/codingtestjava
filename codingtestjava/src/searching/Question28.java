package searching;

/*
 <문제28 트리의 지름 구하기>
 트리의 지름은 트리를 구성하는 노드 중 두 노드 사이의 거리가 가장 긴 것을 말한다.
 트리의 지름을 구하시오.
 */

 /*
  <출제 포인트>
  (1) BFS에 대한 이해 및 구현
  - 노드간 최단거리 BFS 유형 중 하나이다.
  - depth가 깊어질때마다 최초 노드 방문시 거리 기록하면서 최단거리 찾느다.

  (2) 자바 클래스 사용
  -Edge 임의로 구현 

  (3) while 루프 브레이크 포인트에 따른 종료 여부
  -> 종료포인트는 스캔 위치 바로 뒤에 위치 해야함

  (4) 시간초과
  -1 모든 점들을 BFS 하면 시간초과
  -2 2번의 BFS로 지름을 구할 수 있는 이유 : https://blogshine.tistory.com/111
  */
import java.util.*;
public class Question28 {
    static ArrayList<Edge>[] A;
    static boolean visited[];
    static int distance[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();

        //인접리스트 생성
        A = new ArrayList[V + 1];

        //인접리스트 노드 초기화
        for(int i = 1; i < V + 1; i++){
            A[i] = new ArrayList<Edge>();
        }

        // 노드 생성
        for(int i = 0; i < V; i++){
            int S = sc.nextInt();
            while(true){
                int E = sc.nextInt();
                if(E == -1){
                    break;
                }
                int D = sc.nextInt();
                Edge temp = new Edge(E, D);
                A[S].add(temp);
            }
        }
        //방문한 노드 표시
        visited = new boolean[V+1];
        //노드별 거리 표시하기 위한 배열
        distance = new int[V+1];

        BFS(1);
        int Max = 1;
        for(int i = 2; i < V+1; i++){
            if(distance[Max] < distance[i]){
                //인덱스를 바꿔준다. 
                Max = i; 
            }
        }
        //방문한 노드 표시
        visited = new boolean[V+1];
        //노드별 거리 표시하기 위한 배열
        distance = new int[V+1];
        BFS(Max);
        Arrays.sort(distance);
        //시간 초과 남
        // int Max = -1;
        // for(int i = 1; i < V + 1; i++){
        //     BFS(i);
        //     Arrays.sort(distance);
        //     if(Max < distance[V]){
        //         Max = distance[V];
        //     }
        //     visited = new boolean[V+1];
        //     distance = new int[V+1];
        // }
        System.out.println(distance[V]);

    }
    public static void BFS(int v){
        Queue<Integer> q = new LinkedList<>();
        //방문 표시
        visited[v] = true;
        //큐에 추가
        q.add(v);
        //방문한 노드 최초 방문시 거리 기록
        distance[v] = 0;
        while(!q.isEmpty()){
            int pop = q.poll();
            int d = distance[pop];
            for(Edge edge : A[pop]){          
                if(!visited[edge.e]){
                    visited[edge.e] = true;
                    q.add(edge.e);
                    distance[edge.e] = d + edge.value;
                }
            }
        }
    }
    
}
class Edge {
        int e;
        int value;
        
        public Edge(int e, int value){
            this.e = e;
            this.value = value;
        }
}