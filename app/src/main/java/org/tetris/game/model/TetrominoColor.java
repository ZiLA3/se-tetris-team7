package org.tetris.game.model;
import java.awt.Color;
import java.util.Map;

public class TetrominoColor {
    // 전역 색약 모드 상태
    private static boolean isColorBlind = false;

    // 색상 팔레트 (일반)
    private static final Map<BlockType, Color> normalPalette = Map.of(
        Tetromino.O, Color.decode("#FFFF00"),  // Yellow
        Tetromino.I, Color.decode("#00FFFF"),  // Cyan
        Tetromino.S, Color.decode("#008000"),  // Green
        Tetromino.Z, Color.decode("#FF0000"),  // Red
        Tetromino.L, Color.decode("#FFA500"),  // Orange
        Tetromino.J, Color.decode("#0000FF"),  // Blue
        Tetromino.T, Color.decode("#800080")   // Purple
    );

    // 색상 팔레트 (색약 모드)
    private static final Map<BlockType, Color> colorBlindPalette = Map.of(
        Tetromino.O, Color.decode("#F0E442"),
        Tetromino.I, Color.decode("#56B4E9"),
        Tetromino.S, Color.decode("#009E73"),
        Tetromino.Z, Color.decode("#D55E00"),
        Tetromino.L, Color.decode("#E69F00"),
        Tetromino.J, Color.decode("#0072B2"),
        Tetromino.T, Color.decode("#CC79A7")
    );

    // 색상 가져오기
    public static Color getColor(Tetromino t) {
        return isColorBlind ? colorBlindPalette.get(t) : normalPalette.get(t);
    }

    // 색약 모드 상태 변경
    public static void setColorBlind(boolean active) {
        isColorBlind = active;
    }

    // 현재 색약 모드 확인
    public static boolean isColorBlind() {
        return isColorBlind;
    }
}

public enum BlockType {
    I, O, T, S, Z, J, L
}

...
...
class Block
{
	...
	BlockType type;
	Color color;
	...
	Block()
	{
		...
		type = BlockType.I // example
		color = TerminoColor.getColor(type);
		....
	}
}