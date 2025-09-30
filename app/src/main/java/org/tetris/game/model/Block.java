package org.tetris.game.model;

import org.extension.Point;

public class Block {
    protected int[][] shape;
    protected Point pivot;

    public Block(int[][] shape, Point pivot) {
        this.shape = shape;
        this.pivot = pivot;
    }

    public int getShape(int r, int c) {
        return shape[r][c];
    }

    public int getShape(Point pos) {
        return shape[pos.r][pos.c];
    }

    /**
     * pos에 블록이 있는지 확인합니다.
     * @param pos 검사할 위치
     * @return 블록이 있으면 true, 없으면 false
     */
    public boolean isBlockAt(Point pos) {
        return shape[pos.r][pos.c] != 0;
    }

    /**
     * 주어진 좌표를 블록의 피벗을 기준으로 변환합니다.
     * @param pos 변환할 좌표
     * @return 피벗을 기준으로 변환된 좌표
     */
    public Point toLocalPoint(Point pos) {
        return pos.subtract(pivot);
    }

    public int height() {
        return shape.length;
    }

    public int width() {
        if (shape.length > 0)
            return shape[0].length;
        return 0;
    }
}