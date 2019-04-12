package com.github;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Test {

    public static void main(String[] args) {
        List<Rect> ret = new ArrayList<>();
        Rect source = new Rect(6, 6);
        new App().divide(source, 9, ret);


        for (Rect s: ret) {
            System.out.println(s.getHeight() + " " + s.getWidth());
        }


        System.out.println("----------------------");

        List<Rect> ret2 = new ArrayList<>();
        new App().divide2(6,6,0,0, 9, ret2);


        for (Rect s: ret2) {
            System.out.println(s.getTop() + " " + s.getLeft() + " " + s.getHeight() + " " + s.getWidth());
        }


        System.out.println("----------------------");

        List<Rect> ret3 = new ArrayList<>();
        new App().divide3(6,6,0,0, 9, ret3);


        for (Rect s: ret3) {
            System.out.println(s.getTop() + " " + s.getLeft() + " " + s.getHeight() + " " + s.getWidth());
        }


    }


    public void run1() {
        int numRows = 0;
        int[] cols_pre_row = null;
        DivideRectangularArea(5, numRows, cols_pre_row);
    }

    // num_rects : Specifies the number of small rectangle you want to divide a big rectangle into
// num_rows  : Output parameter to receive number of rows needed to divide the big rectangle
// cols_per_row : Output parameter to receive the number of varying columns per rows (just pass a int* data type, the function will create an array of integers where the index of the array is the row, and the value in that index is the columns per that row)


    public static boolean DivideRectangularArea(int num_rects, /*OUT*/ int num_rows, /*OUT*/ int[] cols_per_row) {
        if (num_rects == 0)
            return false;

        if (num_rects < 4) {
            int sections = 1;
            cols_per_row = new int[sections];
            for (int i = 0; i < sections; i++) {
                cols_per_row[i] = num_rects;
            }
            num_rows = sections;
        } else {
            double root = num_rects;
            double sqr_root = sqrt(root);
            int rounded_root = (int) sqr_root;

            int row_count = rounded_root;
            int col_count = rounded_root;

            cols_per_row = new int[row_count];

            int leftover = ((int) num_rects) - row_count * col_count;

            int distrib = leftover / row_count;
            col_count += distrib;
            int left = num_rects - (row_count * col_count);

            int start_adding_index = row_count - left;

            for (int i = 0; i < row_count; i++) {
                if (i == start_adding_index) {
                    col_count++;
                    start_adding_index = -1;
                }
                cols_per_row[i] = col_count;
            }
            num_rows = row_count;
        }
        return true;
    }
}
