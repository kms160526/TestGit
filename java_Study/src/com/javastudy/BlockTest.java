package com.javastudy;

public class BlockTest {
    // 초기화 블록 연습

    // 클래스 초기화 블록
    static {
        System.out.println("static { } ");
    }

    // 인스턴스 초기화 블록
    {
        System.out.println("{ }");
    }

    // 생성자
    public BlockTest(){
        System.out.println("생성자");
    }

    public static void main(String[] args) {
        System.out.println("BlockTest bt = new BlockTest(); ");
        BlockTest bt = new BlockTest();

        System.out.println("BlockTest bt2 = new BlockTest(); ");
        BlockTest bt2 = new BlockTest();

    }

}
