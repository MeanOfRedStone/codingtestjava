package dynamic;

/*

w 4  6  4  3  5
v 7  13 8  6  12

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
        int D[][] = new int[N + 1][N+1];
        
        //가방의 가치의 합을 담는 배열 D[x][0]   | 무게의 합 D[x][1]
        D[0][0] = 0;
        D[0][1] = 0;
        if(bags[1].w <= K){
            D[1][0] = bags[1].v;
            D[1][1] = bags[1].w;
        } else {
            D[1][0] = 0;
            D[1][0] = 0;
        }
        
        

        for(int i = 2; i < N + 1; i++){
            if(bags[i].w < K){
                if(D[i-1][1] + bags[i].w <= K){
                    //현재 가치가 가장 큰 물건의 무게
                    D[i][1] = D[i-1][1] + bags[i].w;
                    //현재 가치가 가장 큰 물건의 합
                    D[i][0] = D[i-1][0] + bags[i].v;
                } else{
                    //(1) 무게 더해서 < K 안될때까지 찾는 경우의 최대 가치 (2) 이전 단계의 최대 무게  (3) 현재 가치
                    for(int j = i -1; j >= 0; j--){
                        if(D[j][1] + bags[i].w <= K) {
                            D[i][0] = Math.max(bags[i].v, Math.max(D[j][0] + bags[i].v, D[i-1][0]));
                            if(D[i][0] == bags[i].v){
                                D[i][1] = bags[i].w;
                            } else if(D[i][0] == D[j][0] + bags[i].v){
                                D[i][1] = D[j][1] + bags[i].w;
                            } else{
                                D[i][1] = D[i-1][1];
                            }
                            break;
                        }
                        
                    }
                }
            }

            
        }
        System.out.println(D[N][0]);
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