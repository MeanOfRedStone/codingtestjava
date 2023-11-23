package samsungswexpert;

/*
 *빡구현
 (1) gear 값이 변화면 왼쪽 값 오른쪽 값도 따라서 바뀌어야 한다
 (2) 이렇게 숫자 많이들어가는 건 왠만하면 자료구조를 잘 활용하자 오타가 많이 나서 틀렸다.
 */

import java.io.*;
import java.util.*;

public class Baekjoon14891 {
    static List<Integer> gear1;
    static List<Integer> gear2;
    static List<Integer> gear3;
    static List<Integer> gear4;

    static int gear1Index;
    static int gear2Index;
    static int gear3Index;
    static int gear4Index;

    static int gear1RightValue;
        
    static int gear2LeftValue;
    static int gear2RightValue;
    
    static int gear3LeftValue;
    static int gear3RightValue;
    static int gear4LeftValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        gear1 = new ArrayList<>();
        gear2 = new ArrayList<>();
        gear3 = new ArrayList<>();
        gear4 = new ArrayList<>();

        gear1Index = 0;
        gear2Index = 0;
        gear3Index = 0;
        gear4Index = 0;

        String[] gear1Information = br.readLine().split("");
        for(int i = 0; i < 8 ;i++){
            gear1.add(Integer.parseInt(gear1Information[i]));
        }

        String[] gear2Information = br.readLine().split("");
        for(int i = 0; i < 8 ;i++){
            gear2.add(Integer.parseInt(gear2Information[i]));
        }

        String[] gear3Information = br.readLine().split("");
        for(int i = 0; i < 8 ;i++){
            gear3.add(Integer.parseInt(gear3Information[i]));
        }

        String[] gear4Information = br.readLine().split("");
        for(int i = 0; i < 8 ;i++){
            gear4.add(Integer.parseInt(gear4Information[i]));
        }

        //회전 횟수
        int K = Integer.parseInt(br.readLine());

        int[][] rotation = new int[K][2];

        for(int i = 0; i < K; i++){
            String[] rotationInformation = br.readLine().split(" ");
            for(int j = 0; j < 2; j++){
                rotation[i][j] = Integer.parseInt(rotationInformation[j]);
            }
        }

        gearChange();

        rotateGears(rotation, K);

        int answer = 0;
        if(gear1.get(gear1Index) == 1){
            answer += 1;
        }
        if(gear2.get(gear2Index) == 1){
            answer += 2;
        }
        if(gear3.get(gear3Index) == 1){
            answer += 4;
        }
        if(gear4.get(gear4Index) == 1){
            answer += 8;
        }

        System.out.println(answer);
    }

    public static void rotateGears(int[][] rotation, int rotationTimes){
        for(int i = 0; i < rotationTimes; i++){
            if(rotation[i][0] == 1){
                gear1Rotate(rotation[i][1]);
            }

            if(rotation[i][0] == 2){
                gear2Rotate(rotation[i][1]);
            }

            if(rotation[i][0] == 3){
                gear3Rotate(rotation[i][1]);
            }

            if(rotation[i][0] == 4){
                gear4Rotate(rotation[i][1]);
            }
        }
    }
    //-1 : 반시계 회전_index 값 + 1 | 1 : 시계 회전_index 값 - 1
    public static void gear1Rotate(int direction){
        //1. 움직임 설정 초기화
        boolean isGear2Move = false;
        boolean isGear3Move = false;
        boolean isGear4Move = false;

        //2. 움직임 설정 확인
        if(gear1RightValue != gear2LeftValue){
            isGear2Move = true;
        }

        if(gear2RightValue != gear3LeftValue){
            isGear3Move = true;
        }

        if(gear3RightValue != gear4LeftValue){
            isGear4Move = true;
        }

        //3. 개별 기어 움직임
        //1) gear1은 그냥 움직임
        gear1(direction);

        //2) gear2는 isgear2Move true일 때 반대 방향으로 회전
        if(isGear2Move){
            gear2(-1 * direction);
        }

        //3) gear3는 isGear2Move, isGear3Move 모두 true일 때 같은 방향으로 회전
        if(isGear2Move && isGear3Move){
            gear3(direction);
        }

        //4) gear는 isGear2Move, isGear3Move, isGear4Move 모두 true일 때 반대 방향으로 회전
        if(isGear2Move && isGear3Move && isGear4Move){
            gear4(-1 * direction);
        } 
    }

    public static void gear2Rotate(int direction){
        //1. 움직임 설정 초기화
        boolean isGear1Move = false;
        boolean isGear3Move = false;
        boolean isGear4Move = false;

        //2. 움직임 설정 확인
        if(gear1RightValue != gear2LeftValue){
            isGear1Move = true;
        }

        if(gear2RightValue != gear3LeftValue){
            isGear3Move = true;
        }

        if(gear3RightValue != gear4LeftValue){
            isGear4Move = true;
        }

        //3. 개별 기어 움직임
        //1) gear2는 그냥 움직임
        gear2(direction);
        
        //2) gear1은 isGear1Move true일때 반대방향 회전
        if(isGear1Move){
            gear1(-1 * direction);
        }

        //3) gear3는 isGear3Move true일 때 반대 방향 회전
        if(isGear3Move){
            gear3(-1 * direction);
        }

        //4) gear4는 isGear3Move, isGear4Move 모두 true일때 같은 방향으로 회전
        if(isGear3Move && isGear4Move){
            gear4(direction);
        }
    }

    public static void gear3Rotate(int direction){
        //1. 움직임 설정 초기화
        boolean isGear1Move = false;
        boolean isGear2Move = false;
        boolean isGear4Move = false;

        //2. 움직임 설정 확인
        if(gear1RightValue != gear2LeftValue){
            isGear1Move = true;
        }

        if(gear2RightValue != gear3LeftValue){
            isGear2Move = true;
        }

        if(gear3RightValue != gear4LeftValue){
            isGear4Move = true;
        }

        //3. 개별 기어 움직임
        //1) gear3는 그냥 움직임
        gear3(direction);

        //2) gear4는 isGear4Move true 이면 반대 방향으로 회전
        if(isGear4Move){
            gear4(-1 * direction);
        }

        //3) gear2는 isGear2Move true 이면 반대 방향으로 회전
        if(isGear2Move){
            gear2(-1 * direction);
        }

        //4) gear1은 isGear2Move, isGear1Move 모두 true이면 같은 방향으로 회전
        if(isGear2Move && isGear1Move){
            gear1(direction);
        }
    }

    public static void gear4Rotate(int direction){
        //1. 움직임 설정 초기화
        boolean isGear1Move = false;
        boolean isGear2Move = false;
        boolean isGear3Move = false;

        //2. 움직임 설정 확인
        if(gear1RightValue != gear2LeftValue){
            isGear1Move = true;
        }

        if(gear2RightValue != gear3LeftValue){
            isGear2Move = true;
        }

        if(gear3RightValue != gear4LeftValue){
            isGear3Move = true;
        }

        //3. 개별 움직임
        //1) gear4는 그냥 움직임
        gear4(direction);

        //2) gear3는 isGear3Move true이면 반대 방향으로 회전
        if(isGear3Move){
            gear3(-1 * direction);
        }

        //3) gear2는 isGear3Move, isGear2Move 모두 true이면 같은 방향으로 회전
        if(isGear3Move && isGear2Move){
            gear2(direction);
        }

        //4) gear1는 isGear3Move, isGear2Move, isGear1Move 모두 true이면 반대 방향으로 회전
        if(isGear3Move && isGear2Move && isGear1Move){
            gear1(-1 * direction);
        }
    }

    //-1 : 반시계 회전_index 값 + 1 | 1 : 시계 회전_index 값 - 1
    public static void gear1(int direction){
        if(direction == -1){
            gear1Index = (gear1Index + 1) % 8;
        }

        if(direction == 1){
            if(gear1Index - 1 < 0){
                gear1Index = gear1Index + 7;
            } else{
                gear1Index = gear1Index - 1;
            }
        }

        gearChange();
    }

    public static void gear2(int direction){
        if(direction == -1){
            gear2Index = (gear2Index + 1) % 8;
        }

        if(direction == 1){
            if(gear2Index - 1 < 0){
                gear2Index = gear2Index + 7;
            } else{
                gear2Index = gear2Index - 1;
            }
        }

        gearChange();
    }

    public static void gear3(int direction){
        if(direction == -1){
            gear3Index = (gear3Index + 1) % 8;
        }

        if(direction == 1){
            if(gear3Index - 1 < 0){
                gear3Index = gear3Index + 7;
            } else{
                gear3Index = gear3Index - 1;
            }
        }

        gearChange();
    }

    public static void gear4(int direction){
        if(direction == -1){
            gear4Index = (gear4Index + 1) % 8;
        }

        if(direction == 1){
            if(gear4Index - 1 < 0){
                gear4Index = gear4Index + 7;
            } else{
                gear4Index = gear4Index - 1;
            }
        }

        gearChange();
    }

    public static void gearChange(){
        gear1RightValue = gear1.get((gear1Index + 2) % 8);
        
        gear2LeftValue = 0;
        if(gear2Index - 2 < 0 ){
            gear2LeftValue = gear2.get(gear2Index + 6);
        } else{
            gear2LeftValue = gear2.get(gear2Index - 2);
        }
        gear2RightValue = gear2.get((gear2Index + 2) % 8);
        
        gear3LeftValue = 0;
        if(gear3Index - 2 < 0 ){
            gear3LeftValue = gear3.get(gear3Index + 6);
        } else{
            gear3LeftValue = gear3.get(gear3Index - 2);
        }
        gear3RightValue = gear3.get((gear3Index + 2) % 8);
        
        gear4LeftValue = 0;
        if(gear4Index - 2 < 0 ){
            gear4LeftValue = gear4.get(gear4Index + 6);
        } else{
            gear4LeftValue = gear4.get(gear4Index - 2);
        }
    }
}
