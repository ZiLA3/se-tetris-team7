package org.tetris.game.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

abstract class MenuController
{
    MenuController()
    {
    }
    @FXML
    public void handleKey(KeyEvent event)
    {
        switch(event.getCode())
        {
            case LEFT:
                this.executeMenu(Command.LEFT);
                break;
            case RIGHT:
                this.executeMenu(Command.RIGHT);
                break;
            case DOWN:
                this.executeMenu(Command.DOWN);
                break;
            case UP:
                this.executeMenu(Command.UP);
                break;
            case ENTER:
                this.executeMenu(Command.SELECT);
                break;
        }
    }
    abstract void executeMenu(Command command) {
        // TODO: 실제 동작 (게임 시작, 옵션창 열기, 종료 등)
    }
}
