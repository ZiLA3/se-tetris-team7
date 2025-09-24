package org.tetris.game.controller;

ENUM Direction
{
    LEFT, RIGHT, DOWN, ROTATE, DROP
}

class BoardController
{
    BoardController()
    { }
    @FXML
    void handleKey(KeyEvent event)
    {
        switch(event.getCode())
        {
            case LEFT:
                this.moveBlock(Direction.LEFT);
                break;
            case RIGHT:
                this.moveBlock(Direction.RIGHT);
                break;
            case DOWN:
                this.moveBlock(Direction.DOWN);
                break;
            case UP:
                this.moveBlock(Direction.ROTATE);
                break;
            case SPACE:
                this.moveBlock(Direction.DROP);
                break;
        }
    }
    void moveBlock(Direction direction)
    {
        // Implementation for moving the block in the specified direction
        // 모델 데이터 변경
    }
}
