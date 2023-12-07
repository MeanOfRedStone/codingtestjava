package slidingWindow;
/*
 <디버깅 포인트>
 1. 시간 복잡도
  1) 규칙에 합당한지 계산하는 부분
  규칙에 몇개 부합하는지 미리 체크해놓는다. 
  그때그때마다 모두 체크하는게 아님 변하는 것만 체크

 (2) 배열 방식
  종료 포인트를 배열에 직접 지정해주는 걸로 변경
 */

import java.io.*;

public class Baekjoon12891 {
    static String[] DNA;
    static int[] result;
    static int[] rule;
    static int checkCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] informationInput = br.readLine().split(" ");
        //DNA 문자열 길이
        int s = Integer.parseInt(informationInput[0]);
        //비밀번호로 사용할 부분 문자열의 길이
        int p = Integer.parseInt(informationInput[1]);

        DNA = br.readLine().split("");

        rule = new int[4];
        checkCount = 0;
        String[] ruleInput = br.readLine().split(" ");
        for(int i = 0; i < 4; i++) {
            rule[i] = Integer.parseInt(ruleInput[i]);
            if(rule[i] == 0) {
                checkCount++;
            }
        }

        //1.값 초기화
        int answer = 0;

        //2. 판별 값 초기화
        result = new int[4];

        //3. 배열 안의 값 검사
        for(int i = 0; i < p; i++) {
            addRear(i);
        }

        //4. Rule을 만족하면 asnwer 값 1 증가
        if(checkCount == 4) {
            answer++;
        }

        for(int i = p; i < s; i++) {
            int endIndex = i;
            int startIndex = i - p;

            addRear(endIndex);
            deleteFront(startIndex);

            if(checkCount == 4) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void checkDNA(int startIndex, int endIndex) {
        for(int i = startIndex; i < endIndex + 1; i++) {
            if(DNA[i].equals("A")) {
                result[0]++;

                if(result[0] == rule[0]) {
                    checkCount++;
                }

                continue;
            }

            if(DNA[i].equals("C")) {
                result[1]++;

                if(result[1] == rule[1]) {
                    checkCount++;
                }

                continue;
            }

            if(DNA[i].equals("G")) {
                result[2]++;

                if(result[2] == rule[2]) {
                    checkCount++;
                }

                continue;
            }

            if(DNA[i].equals("T")) {
                result[3]++;

                if(result[3] == rule[3]) {
                    checkCount++;
                }

                continue;
            }
        }
    }

    public static void deleteFront(int startIndex) {
        String value = DNA[startIndex];

        if(value.equals("A")){
            if(result[0] == rule[0]) {
                checkCount--;
            }

            result[0]--;
            return;
        }

        if(value.equals("C")) {
            if(result[1] == rule[1]) {
                checkCount--;
            }

            result[1]--;
            return;
        }

        if(value.equals("G")) {
            if(result[2] == rule[2]) {
                checkCount--;
            }

            result[2]--;
            return;
        }

        if(value.equals("T")) {
            if(result[3] == rule[3]) {
                checkCount--;
            }

            result[3]--;
            return;
        }
    }

    public static void addRear(int endIndex) {
        String value = DNA[endIndex];

        if(value.equals("A")){
            result[0]++;

            if(result[0] == rule[0]) {
                checkCount++;
            }

            return;
        }

        if(value.equals("C")) {
            result[1]++;

            if(result[1] == rule[1]) {
                checkCount++;
            }

            return;
        }

        if(value.equals("G")) {
            result[2]++;

            if(result[2] == rule[2]) {
                checkCount++;
            }

            return;
        }

        if(value.equals("T")) {
            result[3]++;

            if(result[3] == rule[3]) {
                checkCount++;
            }

            return;
        }
    }
}
/*
 |   |
 a b c d
    startIndex = 0;
    endIndex = (p - 1);
    answer = 0;

    int a = 0;
    int c = 0;
    int g = 0;
    int t = 0;
    //50만
    for(int j = startIndex; j < endIndex + 1; j++) {
        if(DNA[j].equals("A")) {
            a++;
            continue;
        }

        if(DNA[j].equals("C")) {
            c++;
            continue;
        }

        if(DNA[j].equals("G")) {
            g++;
            continue;
        }

        if(DNA[j].equals("T")) {
            t++;
            continue;
        }
    }

    if(a >= rule[0] && c > rule[1] && g > rule[2] && t > rule[3]) {
        answer++;
    }

 //50만
 while(endIndex < s) {
    //1. 배열 앞 부분 제거
    if(DNA[startIndex].equals("A")) {
            a--;
            startIndex++;
            endIndex++;
            
            continue;
        }

    if(DNA[startIndex].equals("C")) {
        c++;
        continue;
    }

    if(DNA[startIndex].equals("G")) {
        g++;
        continue;
    }

    if(DNA[startIndex].equals("T")) {
        t++;
        continue;
    }
 }
 for(int i = 1; i < s - p + 1; i++) {
    if(DNA[startIndex.equals()])
    
    startIndex = i;
    endIndex = (p - 1) + i;

    //50만
    //소배열에서 주어진 DNA 개수만큼 카운트
    for(int j = startIndex; j < endIndex + 1; j++) {
        if(DNA[j].equals("A")) {
            a++;
            continue;
        }

        if(DNA[j].equals("C")) {
            c++;
            continue;
        }

        if(DNA[j].equals("G")) {
            g++;
            continue;
        }

        if(DNA[j].equals("T")) {
            t++;
            continue;
        }
    }

    if(a >= rule[0] && c > rule[1] && g > rule[2] && t > rule[3]) {
        answer++;
    }
 }

 50_0000 * 50_0000 = 250_000_000_000
 */


/*
 A, C, G, T로만 이루어진 문자열
 최소 몇개 이상이 있어야 하는 규칙이 있음
 */