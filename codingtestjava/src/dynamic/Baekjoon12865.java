package dynamic;

/*
무게를 기준으로 DP를 만들자
-1 동적 계획법
 작은 부분으로 큰 부분의 문제를 어떻게 해결할 것인가
 N 값이 문제를 부분 문제로 나누는 역할을 해야함

 -2 비교 대상이 여러개라면 그 대상을 기준으로 N차원 행렬 목표값을 값 안에 넣어준다.

N = 4, K = 7
w  6  4  3  5
v  13 8  6  12

for(int i = 1; i < N + 1; i++){
    int weight = bags[i].w;
    int value = bags[i].v;
    for(int j = 1; j < K + 1; j++){
        D[i][j] = D[i-1][j];
        if(j - weight >= 0){
            D[i][j] = Math.max(D[i-1][j], D[i-1][j - weight] + value);
        }
    }
}

D  1  2  3  4  5  6  7 (k)  
1  0  0  0  0  0  13 13
2  0  0  0  8  8  13 13
3  0  0  6  8  8  13 14
4 
(N)



4  6   6  
7  13  13
*/




import java.util.*;

public class Baekjoon12865 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //물건의 개수
        int N = sc.nextInt();
        //배낭의 최대 무게
        int K = sc.nextInt();
        
        //가방 배열
        Item bags[] = new Item[N + 1];
        
        //가방 배열에 물건 담아줌
        for(int i = 1; i < N + 1; i++){
            int w = sc.nextInt();
            int v = sc.nextInt();
            
            Item item = new Item(w, v);
            bags[i] = item;
        }
        //점화식 배열
        int D[][] = new int[N + 1][K+1];
        
        for(int i = 1; i < N + 1; i++){
            int weight = bags[i].w;
            int value = bags[i].v;
            for(int j = 1; j < K + 1; j++){
                D[i][j] = D[i-1][j];
                if(j - weight >= 0){
                    D[i][j] = Math.max(D[i-1][j], D[i-1][j - weight] + value);
                }
            }
        }

        System.out.println(D[N][K]);
    }
}

class Item {
    int w;
    int v;
    
    public Item(int w, int v){
        this.w = w;
        this.v = v;
    }
    
}