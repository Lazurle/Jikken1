package EIEV3;

import java.util.*;
import java.io.*;

class Command {
    public static final int CREATE = 1;
    public static final int MOVE = 2;
    public static final int EXPAND_SHRINK = 3;
    public static final int DELETE = 4;
    public static final int DELETE_ALL = 5;
    public static final int INTERSECT = 6;
    public static final int DISPLAY_BOARD = 7;
    public static final int EXIT = 8;
    public static final int INPUT_MIN_LENGTH = 1;
    public static final int INPUT_MAX_LENGTH = 8;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Board board = new Board();

    public void inputCommand() {
        try {
            System.out.println("�������͂��Ă�������");
            System.out.println("1.create");
            System.out.println("2.move");
            System.out.println("3.expand/shrink");
            System.out.println("4.delete");
            System.out.println("5.deleteAll");
            System.out.println("6.intersect");
            System.out.println("7.displayBoard");
            System.out.println("8.exit");

            int n = Integer.parseInt(reader.readLine());

            switch (n) {
                case CREATE :
                    while (true) {
                        if (this.create()) {
                            break;
                        }
                    }
                    break;
                case MOVE :
                    while (true) {
                        if (this.move()) {
                            break;
                        }
                    }
                    break;
                case EXPAND_SHRINK :
                    while (true) {
                        if (this.expand_shrink()) {
                            break;
                        }
                    }
                    break;
                case DELETE :
                    while (true) {
                        if (this.delete()) {
                            break;
                        }
                    }
                    break;
                case DELETE_ALL :
                    while (true) {
                        if (this.deleteAll()) {
                            break;
                        }
                    }
                    break;
                case INTERSECT :
                    while (true) {
                        if (this.intersect()) {
                            break;
                        }
                    }
                    break;
                case DISPLAY_BOARD :
                    this.displayBoard();
                    break;
                case EXIT :
                    this.exit();
                    break;
                default :
                    System.out.println("1-8�̐�������͂��ĉ������B");
                    break;
            }
        } catch (IOException e) {
            System.out.println("���͂ɃG���[���������܂����F" + e);
        } catch (NumberFormatException e) {
            System.out.println("�����ȊO�̒l�����͂���܂����F" + e);
        }
    }

    public boolean create() {
        if (board.array.size() >= Board.ARRAY_MAX_SIZE) {
            System.out.println("����𒴂��Ă��܂��B");
            return true;
        }

        try {
            System.out.print("���F");
            int width = Integer.parseInt(reader.readLine());
            System.out.print("�����F");
            int height = Integer.parseInt(reader.readLine());
            System.out.print("x���W�F");
            int x = Integer.parseInt(reader.readLine());
            System.out.print("y���W�F");
            int y = Integer.parseInt(reader.readLine());
            System.out.print("�F(red,blue,yellow,gray)�F");
            String color = reader.readLine();

            if (!(color.equals("red") || color.equals("blue") || color.equals("yellow") || color.equals("gray"))) {
                System.out.println("�w�肵���F�ȊO���I������܂����B");
                return false;
            }

            if (hasWrongWidthOrHeight(width, height, x, y)) {
                System.out.println("���܂��͍������s���ł��B");
                return false;
            } else if (isOverRectangle(width, height, x, y)) {
                System.out.println("�{�[�h�̕��𒴂��Ă��܂��B");
                return false;
            } else if (isPoint(width, height, x, y)) {
                System.out.println("�_�ł��B");
                return false;
            } else if (isLine(width, height, x, y)) {
                System.out.println("���ł��B");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("�d������l�p�`�����݂��܂��B");
                return false;
            }

            // �l�p�`���쐬
            Rectangle rectangle = new Rectangle(width, height, x, y, color);
            // �l�p�`��Board�ɒǉ�
            board.array.add(rectangle);
            System.out.println("�l�p�`��ǉ����܂����B");
            System.out.println("");

            return true;
        } catch (IOException e) {
            System.out.println("���͂ɃG���[���������܂����F" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("�����ȊO�̒l�����͂���܂����F" + e);
            return false;
        }
    }

    public boolean move() {
        if (board.array.isEmpty()) {
            System.out.println("�l�p�`�͑��݂��܂���B");
            return true;
        }

        try {
            System.out.print("���Ԗڂ̎l�p�`���ړ����܂���?�F");
            int n = Integer.parseInt(reader.readLine());

            System.out.print("x�������ւ̈ړ������F");
            int dx = Integer.parseInt(reader.readLine());
            System.out.print("y�������ւ̈ړ������F");
            int dy = Integer.parseInt(reader.readLine());

            int width = board.array.get(n - 1).getWidth();
            int height = board.array.get(n - 1).getHeight();
            int x = board.array.get(n - 1).getX() + dx;
            int y = board.array.get(n - 1).getY() + dy;

            if (isOverRectangle(width, height, x, y)) {
                System.out.println("�{�[�h�̕��𒴂��Ă��܂��B");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("�d������l�p�`�����݂��܂��B");
                return false;
            }

            board.array.get(n - 1).setX(x);
            board.array.get(n - 1).setY(y);

        } catch (IOException e) {
            System.out.println("���͂ɃG���[���������܂����F" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("�����ȊO�̒l�����͂���܂����B�F" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("���̔ԍ��̎l�p�`�͑��݂��܂���B" + e);
            return false;
        }

        return true;
    }

    public boolean expand_shrink() {
        if (board.array.isEmpty()) {
            System.out.println("�l�p�`�͑��݂��܂���B");
            return true;
        }

        System.out.print("���Ԗڂ̎l�p�`���g��܂��͏k�����܂���?�F");
        try {
            int n = Integer.parseInt(reader.readLine());
            System.out.print("x�������̔{���F");
            float mx = Float.parseFloat(reader.readLine());
            System.out.print("y�������̔{���F");
            float my = Float.parseFloat(reader.readLine());

            int width = Math.round(board.array.get(n - 1).getWidth() * mx);
            int height = Math.round(board.array.get(n - 1).getHeight() * my);
            int x = board.array.get(n - 1).getX();
            int y = board.array.get(n - 1).getY();

            if (hasWrongWidthOrHeight(width, height, x, y)) {
                System.out.println("���܂��͍������s���ł��B");
                return false;
            } else if (isOverRectangle(width, height, x, y)) {
                System.out.println("�{�[�h�̕��𒴂��Ă��܂��B");
                return false;
            } else if (isPoint(width, height, x, y)) {
                System.out.println("�_�ł��B");
                return false;
            } else if (isLine(width, height, x, y)) {
                System.out.println("���ł��B");
                return false;
            } else if (hasSameRectangle(width, height, x, y)) {
                System.out.println("�d������l�p�`�����݂��܂��B");
                return false;
            }

            board.array.get(n - 1).setWidth(width);
            board.array.get(n - 1).setHeight(height);

        } catch (IOException e) {
            System.out.println("���͂ɃG���[���������܂����F" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("�{���x���������_�ȊO�̒l�����͂���܂����B�F" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("���̔ԍ��̎l�p�`�͑��݂��܂���B" + e);
            return false;
        }

        return true;
    }

    public boolean delete() {
        if (board.array.isEmpty()) {
            System.out.println("�l�p�`�͑��݂��܂���B");
            return true;
        }

        System.out.print("���Ԗڂ̎l�p�`���폜���܂���?:");

        try {
            int n = Integer.parseInt(reader.readLine());
            board.array.remove(n - 1);
            System.out.println(n + "�Ԗڂ̎l�p�`���폜���܂����B");
            return true;
        } catch (IOException e) {
            System.out.println("���͂ɃG���[���������܂����F" + e);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("�����ȊO�̒l�����͂���܂����B�F" + e);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("���̔ԍ��̎l�p�`�͑��݂��܂���B" + e);
            return false;
        }
    }

    public boolean deleteAll() {
        if (board.array.isEmpty()) {
            System.out.println("�l�p�`�͑��݂��܂���B");
        } else {
            board.array.clear();
            System.out.println("���ׂĂ̒����`���폜���܂����B");
        }
        return true;
    }

    public boolean intersect() {
        if (board.array.isEmpty()) {
            System.out.println("�l�p�`�͑��݂��܂���B");
            return true;
        } else if (board.array.size() >= Board.ARRAY_MAX_SIZE) {
            System.out.println("����𒴂��Ă��܂��B");
            return true;
        }
        return true;
    }

    public void displayBoard() {
        Iterator iterator = board.array.iterator();

        while (iterator.hasNext()) {
            Rectangle rectangle = (Rectangle)iterator.next();
            System.out.print("����:" + rectangle.getWidth() + " ");
            System.out.print("�c��:" + rectangle.getHeight() + " ");
            System.out.print("x:" + rectangle.getX() + " ");
            System.out.print("y:" + rectangle.getY() + " ");
            System.out.print("�F:" + rectangle.getColor() + " ");
            System.out.println("");
        }
    }

    public boolean exit() {
        System.out.println("�v���O�������I�����܂��B");
        return true;
    }


    // �ǉ��֐��F�s���ȕ��܂��͍����ł��邩�ǂ����𔻒�
    public boolean hasWrongWidthOrHeight(int width, int height, int x, int y) {
        if (width <= 0 || height <= 0) {
            return true;
        }
        return false;
    }

    // �ǉ��֐� : �l�p�`���{�[�h�͈͓̔����ǂ����𔻒�
    public boolean isOverRectangle(int width, int height, int x, int y) {
        if ((width + x) > Board.BOARD_MAX_WIDTH || (height + y) > Board.BOARD_MAX_HEIGHT || x < Board.BOARD_MIN_WIDTH || y < Board.BOARD_MIN_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    // �ǉ��֐� : �l�p�`�����ł͂Ȃ����Ƃ𔻒�
    public boolean isLine(int width, int height, int x, int y) {
        if (width == 1 || height == 1) {
            return true;
        } else {
            return false;
        }
    }

    // �ǉ��֐� : �l�p�`���_�ł͂Ȃ����Ƃ𔻒�
    public boolean isPoint(int width, int height, int x, int y) {
        if (width == 1 && height == 1) {
            return true;
        } else {
            return false;
        }
    }

    // �ǉ��֐��F�d�����钷���`�����݂��邩�ǂ���
    public boolean hasSameRectangle(int width, int height, int x, int y) {
        Iterator iterator = board.array.iterator();

        while (iterator.hasNext()) {
            Rectangle rectangle = (Rectangle)iterator.next();
            if (rectangle.getWidth() == width && rectangle.getHeight() == height && rectangle.getX() == x && rectangle.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
