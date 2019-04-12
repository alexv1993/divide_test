package com.github;


import java.awt.*;
import java.util.*;
import java.util.List;

public class App2 {

    /**
     *
     * @param numberofrects
     */
    public void createRectangles(int numberofrects) {

        List<Rectangle> rectangleList = new ArrayList<>();

        // when calling this method, you put in how many rectangles you want (INCLUDING) the first one of 600, 600
        Rectangle OldRectangle = new Rectangle(600, 600, 100, 100);
        for (int i = 0; i < numberofrects; i++) {
            Rectangle NewRectangle =
                    new Rectangle(
                            OldRectangle.x + (100),
                            OldRectangle.y + (100),
                            OldRectangle.width -= (100),
                            OldRectangle.height -= (100));
            rectangleList.add(NewRectangle);
            OldRectangle = NewRectangle;
        }
    }
}
