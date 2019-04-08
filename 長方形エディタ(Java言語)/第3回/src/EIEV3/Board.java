package EIEV3;

import java.util.ArrayList;

class Board {
    public static final int BOARD_MAX_WIDTH = 500;
    public static final int BOARD_MAX_HEIGHT = 400;
    public static final int BOARD_MIN_WIDTH = 0;
    public static final int BOARD_MIN_HEIGHT = 0;
    public static final String BOARD_COLOR = "white";
    public static final int ARRAY_MAX_SIZE = 10;
    public static final int ARRAY_MIN_SIZE = 0;

    ArrayList<Rectangle> array = new ArrayList<Rectangle>();

    public int getWidth() {
        return this.BOARD_MAX_WIDTH;
    }

    public int getHeight() {
        return this.BOARD_MAX_HEIGHT;
    }
}
