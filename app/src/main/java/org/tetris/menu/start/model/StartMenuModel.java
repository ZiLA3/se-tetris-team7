package org.tetris.menu.start.model;

	classDiagram
	    class StartMenu {
	    - field : List of objects, 0번 인덱스는 GameNameText로 고정하는 게 좋을듯.
	    - field : index of List 
			- method : public void Render() - 커서가 움직이면 리스트 순회 display
			- method : public void moveKeyboardCursor(int input)
			- method : public void handleInput(KeyEvent e) -> 올바르지 않은 input이 온 경우 CorrectButtonText를 생성해야함.
	}
	    
	    StartMenu *-- GameNameText
	    StartMenu *-- StartGameButton
	    StartMenu *-- SettingButton
	    StartMenu *-- HighestScoreBoard
	    StartMenu *-- ExitButton
	    StartMenu *-- HighestScoreBoard
	    class GameNameText{
	    - Setting 함수
	    - Display 함수
	    }
	    
	    class CorrectButtonText{
		    - Setting 함수
		    - Display 함수
	    }
			
	    class StartGameButton {
	    - boolean focused → 키보드 커서?가 해당 버튼에 있는 지 표시.
	    - Setting 함수 - font나 string 등 font 컬러도.   
	    - DisPlay 함수 - 실제 어떻게 그릴 지를 설정
	    - Action 함수 - 엔터 눌렸을 때 - 동작 수행하도록
	    }
	    class SettingButton {
	    - boolean focused → 키보드 커서?가 해당 버튼에 있는 지 표시.
	    - Setting 함수 - font나 string 등 font 컬러도.   
	    - DisPlay 함수 - 실제 어떻게 그릴 지를 설정
	    - Action 함수 - 엔터 눌렸을 때 - 동작 수행하도록
	    }
	    class HighestScoreBoard {
	    - boolean focused → 키보드 커서?가 해당 버튼에 있는 지 표시.
	    - Setting 함수 - font나 string 등 font 컬러도.   
	    - DisPlay 함수 - 실제 어떻게 그릴 지를 설정
	    }
	    class ExitButton {
	    - boolean focused → 키보드 커서?가 해당 버튼에 있는 지 표시.
	    - Setting 함수 - font나 string 등 font 컬러도.   
	    - DisPlay 함수 - 실제 어떻게 그릴 지를 설정
	    - Action 함수 - 엔터 눌렸을 때 - 동작 수행하도록
	    }
	    class HighestScoreBoard {
	    - Setting 함수 - font나 string 등 font 컬러도.   
	    - DisPlay 함수 - 실제 어떻게 그릴 지를 설정
	    - highest score 값은 메모장에 저장해두고 가지고 오기
	    }


        class StartMenu {
    // 0번 인덱스는 GameNameText로 고정 (포커스 대상 아님)
    List<MenuItem> items
    int focusMinIndex = 1

    // 생성 시 구성
    void init() {
        items = [
          GameNameText(),         // index 0 (표시 전용)
          StartGameButton(),      // 1
          SettingButton(),        // 2
          ExitButton()            // 3 
          HighestScoreBoard(),    // 4 (표시 전용)
        ]
        applyDefaultStyles()
        applyFocus()
        // 점수판 데이터 읽기
        (items[4] as HighestScoreBoard).loadFromFile("highest.txt")
    }

    void applyDefaultStyles() {
        // 각 항목 스타일 배치 (폰트/크기/색/문구)
        // 예시: items[i].applySetting(styleFor(i))
    }

    void applyFocus() {
        // 모든 포커스 off, 현재 index만 on (타이틀/점수판은 off 유지)
        for i in 0..items.size-1:
            items[i].setFocus(i == focusIndex)
    }

    // 표시
    void render(RenderCtx ctx) {
        for item in items:
            item.display(ctx)
    }

    // 키 입력 처리 (비정상 입력 시 CorrectButtonText를 생성/표시)
    void handleInput(KeyEvent e) {
        switch (e.code) {
            case UP:
                moveKeyboardCursor(-1)    // 포커스 이동
            case DOWN:
                moveKeyboardCursor(+1)
            case ENTER:
                tryInvokeAction()
            default:
                spawnCorrectButtonText("Wrong Key: " + e.code)
        }
    }

    void moveKeyboardCursor(int delta) {
        // 포커스가 가능한 아이템(버튼류)만 순환
        next = clampOrWrap(focusIndex + delta, min=1, max = 3) //
        focusIndex = next
        applyFocus()
    }

    void tryInvokeAction() {
        item = items[focusIndex]
        item.action()
        // MenuContext {
            startGame: () => SceneManager.switch("Game"),
            openSettings: () => SceneManager.switch("Settings"),
            exitApp: () => App.quit()
        // })
    }

    void spawnCorrectButtonText(string msg) {
        // 이미 있다면 갱신/타이머 리셋, 없다면 임시로 하나 추가했다가 일정 시간 후 제거
        // 예시:
        //  - items 리스트의 끝에 CorrectButtonText를 attach
        //  - life 타이머 만료 시 제거
    }
}


import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartMenuModel {
    private String title;
    private String gameStartButtonText;
    private String settingButtonText;
    private String exitButtonText;

    private int titleWidth;
    private int titleHeight;
    private int buttonWidth;
    private int buttonHeight;
    
    Font titleFont;
    Font buttonFont;
    // 추가적인 상태 변수들...

    public StartMenuModel() {
        this.title = "Tetris";
        this.gameStartButtonText = "게임 시작";
        this.settingButtonText = "설정";
        this.exitButtonText = "종료";

        this.titleWidth = 400;
        this.titleHeight = 200;
        this.buttonWidth = 200;
        this.buttonHeight = 50;
        
        this.titleFont = new Font("Arial", 60);
        this.buttonFont = new Font("Arial", 20);
        // 초기화 코드...
    }

    public String getTitle() {
        return title;
    }

    public String getGameStartButtonText() {
        return gameStartButtonText;
    }

    public String getSettingButtonText() {
        return settingButtonText;
    }

    public String getExitButtonText() {
        return exitButtonText;
    }

    public Dimension2D getTitleSize() {
        return new Dimension2D(titleWidth, titleHeight);
    }

    public Dimension2D getButtonSize() {
        return new Dimension2D(buttonWidth, buttonHeight);
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getButtonFont() {
        return buttonFont;
    }

    public Color getTextColor() {
        return GameColor.getTextColor();
    }
}