package com.javastudy;

// 자바 프로그램의 기본 구조
// class 키워드로 클래스 선언
// public 선언하면 다른 클래스에서 접근 가능
// 클래스 코드는 {} 내에 모두 작성
public class Hello {
    // 메소드
    // C/C++ 에서의 함수를 메소드로 지칭
    // 클래스 밖에 작성할 수 없음
    public static int sum(int n, int m){
        return n + m;
    }

    // 주석문
    // 한라인 주석

    /*
    * 여러행 주석
    * */

    // main() 메소드에서 실행 시작
    // public static void 로 선언
    // String[] args로 실행 인자를 전달 받음

    public static void main(String[] args){
        // 변수 선언
        // 변수 타입과 변수 이름 선언
        // ; 로 한 문장의 끝을 인식
        int i=20;
        int s;
        char a;

        // sum() 메소드 호출

        s = sum(i, 10);
        // sum() 호출 시 변수 i의 값과 정수 10을 전달
        // n, m에 각각 20, 10 값 전달
        // n과 m값을 더한 30 리턴
        // 변수 s는 정수 30을 전달 받음
        a = '?';

        // 문자 ? 화면 출력
        System.out.println(a);
        // Hello 문자열 출력
        System.out.println("Hello");
        // 정수 s값 화면 출력
        System.out.print(s);

        // 화면 출력
        // 표준 출력 스트림에 메시지 출력
        // 표준 출력 스트림 System.out.println() 메소드 호출
        // println()은 여러 타입의 데이 출력 가능
        // println()은 출력 후 다음 행으로 커서 이동
    }
}
