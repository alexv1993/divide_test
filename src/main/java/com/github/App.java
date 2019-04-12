package com.github;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class App {

    //1. Приведение к четному виду (добавление координаты, дополнительный сдвиг)
    //2. Рекурсивное разбиение (деление пополам по горизонтали и по вертикали)
    //3. Уровень вложенности


    private int N;
    private int i = 0;


//    public void DivideSquare(Rect source, int n, List<Rect> result) {
//        if (n == 0) return;
//        if (n == 1) {
//            result.Add(source);
//            return;
//        }
//
//        var l = n / 2;
//        var r = n - l;
//
//        Rect newSource = null;
//
//        if (source.Width > source.Height)
//            newSource = new Rect() {
//                Width =source.Width /2,Height =source.Height
//            };
//        else
//            newSource = new Rect() {
//                Width =source.Width,Height =source.Height /2
//            };
//
//        DivideSquare(newSource, l, result);
//        DivideSquare(newSource, r, result);
//    }


    /**
     * Размер кластера
     * Нестабильная работа на определенных наборах данных
     *
     * @param source иходный прямоугольник
     * @param n      размер кластера
     * @param result список новым прямоугольников
     */
    public void divide(Rect source, int n, List<Rect> result) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            result.add(source);
            return;
        }

        int l = n / 2;
        int r = n - l;

        Rect newSource = null;

        if (source.getWidth() > source.getHeight())
            newSource = new Rect(source.getWidth() / 2, source.getHeight());
        else
            newSource = new Rect(source.getWidth(), source.getHeight() / 2);
        divide(newSource, l, result);
        divide(newSource, r, result);

    }


    /**
     *
     * @param width
     * @param height
     * @param top
     * @param left
     * @param n
     * @param result
     */
    public void divide2(int width, int height, int top, int left, int n, List<Rect> result) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            result.add(new Rect(width, height, top, left));
            return;
        }

        int l = n / 2;
        int r = n - l;

        int newWidth = width;
        int newHeight = height;

        if (width > height)
        {
            newWidth /= 2;

            divide2(newWidth, newHeight, top, left, l, result);
            divide2(newWidth, newHeight, top, left + newWidth, r, result);

        }
        else
        {
            newHeight /= 2;
            divide2(newWidth, newHeight, top, left, l, result);
            divide2(newWidth, newHeight, top + newHeight, left, r, result);
        }
    }

    public void divide3(int width, int height, int top, int left, int n, List<Rect> result) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            result.add(new Rect(width, height, top, left));
            return;
        }

        int l = n / 2;
        int r = n - 1 ;

        int newWidth = width;
        int newHeight = height;

        if (width > height)
        {
            newWidth /= 2;

            divide3(newWidth, newHeight, top, left, l, result);
            divide3(newWidth, newHeight, top, left + newWidth, r, result);

        }
        else
        {
            newHeight /= 2;
            divide3(newWidth, newHeight, top, left, l, result);
            divide3(newWidth, newHeight, top + newHeight, left, r, result);
        }
    }


    List<Rect> arrange(Rect rect, int numWindows)
    {
        int columns = (int) ceil(sqrt(numWindows));
        int fullRows = numWindows / columns;
        int orphans = numWindows % columns;   // how many 'odd-sized' ones on our bottom row.

        int width =  rect.getWidth()/ columns;
        int height = rect.getHeight() / (orphans == 0 ? fullRows : (fullRows+1)); // reduce height if there are orphans

        List<Rect> output = new ArrayList<>();

        //Calculate rectangles
        for (int y = 0; y < fullRows; ++y)
            for (int x = 0; x < columns; ++x)
                output.add(new Rect(x * width, y * height, width, height));

        if (orphans > 0)
        {
            int orphanWidth = rect.getWidth()/ orphans;
            for (int x = 0; x < orphans; ++x)
                output.add(new Rect(x * orphanWidth , x * height, orphanWidth , height));
        }

        return output;
    }

    /**
     * ОБщее число многоугольников
     * @param n
     * @param m
     * @return
     */
    public static long  rectCount(int n, int m)
    {
        return (m * n * (n + 1) * (m + 1)) / 4;
    }

}
