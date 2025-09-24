package org.tetris.menu.start.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class StartMenuController {
    private final StartMenuModel model;
    private final StartMenuView view;
    private final Runnable onGameStart;
    private final Runnable onOpenSettings;
    private final Runnable onExitGame;

    public StartMenuController(StartMenuModel model,
            StartMenuView view,
            Runnable onGameStart,
            Runnable onOpenSettings,
            Runnable onExitGame) {
        this.model = model;
        this.view = view;
        this.onGameStart = onGameStart;
        this.onOpenSettings = onOpenSettings;
        this.onExitGame = onExitGame;

        setView();
    }

    private void setView() {
        // Text Set
        view.setTitleText(model.getTitle());
        view.setGameStartButtonText(model.getGameStartButtonText());
        view.setSettingButtonText(model.getSettingButtonText());
        view.setExitButtonText(model.getExitButtonText());

        // Font Set
        view.setTitleFont(model.getTitleFont());
        view.setButtonFont(model.getButtonFont());
        view.setTextColor(model.getTextColor());

        // Size Set
        view.setTitleSize((int)model.getTitleSize().getWidth(), (int)model.getTitleSize().getHeight());
        view.setButtonSize((int)model.getButtonSize().getWidth(), (int)model.getButtonSize().getHeight());

        view.setActionHandlers(
            this::gameStart,
            this::openSettings,
            this::exitGame
        );
    }

    public void bindInput(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                view.setFocusButton(-1);
            } else if (e.getCode() == KeyCode.DOWN) {
                view.setFocusButton(1);
            } else if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER) {
                view.buttonFire();
            }
            else {
                // view에서 correct button 알려주는 거 해야함.
            }
        });
    };

    private void gameStart()
    {
        onGameStart.run();
    }

    private void openSettings()
    {
        onOpenSettings.run();
    }

    private void exitGame()
    {
        onExitGame.run();
    }
}