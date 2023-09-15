package com.example.roomdb;

public class sort {

    public static void main(String[] args) {
        int array[] = {12, 11, 13, 5, 6};

        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        for (int arrayelement : array) {
            System.out.println(arrayelement);
        }
    }
}
