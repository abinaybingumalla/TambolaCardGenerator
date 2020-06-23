package com.test;

import java.util.HashSet;
import java.util.Random;

public class ThambolaGenerator {

    public static void main(String[] args) {
        int[][] card = new int[3][9];
        generateNum(card);
        deleteNumbers(card);
        printCard(card);
    }

    public static void generateNum(int[][] card) {
        HashSet<Integer> set = new HashSet<>();
        int st = 1;
        int end = 9;
        for (int i = 0; i < 9; i++) {


            //Account for num diff at end
            if (i == 8) {
                end++;
            }

            //System.out.println(st + " " + end);
            for (int j = 0; j < 3; j++) {

                int number = getRandomNumber(st, end);
                while (set.contains(number)) {
                    number = getRandomNumber(st, end);
                }
                set.add(number);
                card[j][i] = number;
            }
            st = st + 10;
            end = end + 10;

            //Account for num diff at start
            if (i == 0) {
                st--;
            }
        }
    }

    public static void deleteNumbers(int[][] card) {
        HashSet<Integer> deleteIndexSet = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            deleteIndexSet = new HashSet<>();
            for (int k = 0; k < 4; k++) {
                int deleteIdx = getRandomNumber(0, 9);
                while (deleteIndexSet.contains(deleteIdx)) {
                    deleteIdx = getRandomNumber(0, 9);
                }
                deleteIndexSet.add(deleteIdx);
                card[i][deleteIdx] = -1;
            }
        }

        //handle for last row
        deleteIndexSet = new HashSet<>();
        for (int j = 0; j < 4; j++) {
            int deleteIdx = getRandomNumber(0, 9);
            while (deleteIndexSet.contains(deleteIdx) || (card[0][deleteIdx] == -1 && card[1][deleteIdx] == -1)) {
                deleteIdx = getRandomNumber(0, 9);
            }

            deleteIndexSet.add(deleteIdx);
            card[2][deleteIdx] = -1;
        }
    }

    public static void printCard(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getRandomNumber(int st, int end) {
        Random r = new Random();
        return r.nextInt(end - st) + st;
    }
}
