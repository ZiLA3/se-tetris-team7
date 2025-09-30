package org.tetris.game.model;

import org.extension.Point;

public class Board {
    private int height = 20;
    private int width = 10;

    private int[][] board;

    /**
     * 기본 크기의 보드를 생성합니다. (20x10)
     */
    public Board() {
        board = new int[height][width];
    }

    /**
     * 주어진 크기의 보드를 생성합니다.
     * @param height 보드의 높이
     * @param width 보드의 너비
     */
    public Board(int height, int width){
        this.height = height;
        this.width = width;
        board = new int[height][width];
    }

    /**
     * 보드에 블록을 놓습니다.
     * @param block 놓을 블록
     * @param pos 블록의 피벗이 놓일 위치
     */
    public void placeBlock(Block block, Point pos) {
        int blockHeight = block.height();
        int blockWidth = block.width();

        for (int r = 0; r < blockHeight; r++) {
            for (int c = 0; c < blockWidth; c++) {
                var curPoint = new Point(r, c);
                
                if (block.isBlockAt(curPoint)) {
                    var mapPoint = pos.add(block.toLocalPoint(curPoint));
                    place(mapPoint);

                }
            }
        }
    }

    /**
     * 블록을 해당 위치에 놓을 수 있는지 확인합니다.
     * @param block 놓을 블록
     * @param pos 블록의 피벗이 놓일 위치
     * @return 블록을 놓을 수 있으면 true, 그렇지 않으면 false
     */
    public boolean canPlaceBlock(Block block, Point pos) {
        int blockHeight = block.height();
        int blockWidth = block.width(); 

        for (int r = 0; r < blockHeight; r++) {
            for (int c = 0; c < blockWidth; c++) {
                var curPoint = new Point(r, c);
                if (block.isBlockAt(curPoint)) {
                    var mapPoint = pos.add(block.toLocalPoint(curPoint));

                    if (isOutside(mapPoint) || isBlockAt(mapPoint)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isOutside(Point pos) {
        return pos.r < 0 || pos.r >= height || pos.c < 0 || pos.c >= width;
    }

    private boolean isBlockAt(Point pos) {
        return board[pos.r][pos.c] != 0;
    }

    private boolean place(Point pos){
        if(isBlockAt(pos)) return false;
        board[pos.r][pos.c] = 1;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                str.append(board[r][c]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}