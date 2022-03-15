package com.chess.engine.board;
import java.util.Arrays;

public class test {

    static int[] a = new int[]{5,3,1,8,10,2};
    public static void main(String[] args) {
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(minMax(a)));
    }

    public static int[] minMax(int[] arr){
        Arrays.sort(arr);
        int[] b = new int[]{arr[0], arr[(arr.length-1)]};
        return b;

    }
    
}
