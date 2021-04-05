package com.javastudy;

public class Main extends Thread{
    private int[] temp;
    public Main(String threadname){
        super(threadname);
        temp = new int[10];

        for(int start=0; start<temp.length; start++){
            temp[start] = start;
        }
    }

    public void run(){
        for(int start:temp){
            try{
                Thread.sleep(1000);
            } catch ()
        }
    }

    public static void main(String[] args) {
	// write your code here
    }
}
