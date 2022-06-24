package com.example.authenservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class demo {
    public static void exam(int total){
        int[] number = {9,2,10,4,11,6,7,3,15};
        for (int i = 0; i < number.length; i++) {
            for ( int j = i+1;j < number.length;j++){
                for (int k = j+1;k<number.length;k++){
                    for (int n=k+1;n< number.length;n++){
                        if ((number[i]+number[j]+number[k]+number[n]) == total){
                            System.out.println(""+number[i]+""+number[j]+""+number[k]+""+number[n]);
                        }
                    }
                }
            }
        }
    }

    public static void quadTuple(int[] A, int target)
    {
        Arrays.sort(A);

        for (int i = 0; i <= A.length - 4; i++)
        {
            for (int j = i + 1; j <= A.length - 3; j++)
            {
                int k = target - (A[i] + A[j]);//11

                int low = j + 1, high = A.length - 1;// low =2; high=7

                while (low < high)
                {
                    if (A[low] + A[high] < k) {
                        low++;
                    }
                    else if (A[low] + A[high] > k) {
                        high--;
                    }
                    // quadruplet with a given sum found
                    else {
                        System.out.println("(" + A[i] + " " + A[j] + " " +
                                A[low] + " " + A[high] + ")");
                        low++;
                        high--;
                    }
                }
            }
        }
    }


    public static void min(){
        int min = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int[] number = {1,2,3,4,5,6,7,8};
        for(int i =0;i<number.length;i++){
            for (int j = i+1;j< number.length;j++){
                System.out.println("Các cặp số nguyên " +number[i]+"-"+number[j]+" với tổng số "+(number[i]+number[j]));
                list.add(number[i]+number[j]);
            }
        }
        for (int i = 1; i < list.size(); i++) {
          min = list.get(0);
          if (min > list.get(i)){
              min = list.get(i);
          }
        }
        System.out.println(min);
    }

    public static void main(String[] args) {
//      exam(35);
//        min();
        int[] A = { 2, 7, 4, 0, 9, 5, 1, 3 };
        int target = 20;

        quadTuple(A, target);
    }
}
