package org.tetris.menu.setting.model;

classDiagram
    class SettingMenu {
        - field : List of Options
        - method : public void Render()
        - method : public void moveKeyboardCursor(int direction)
        - method 
    }
    SettingMenu *-- ColorBlindOption
    SettingMenu *-- SoundOption
    SettingMenu *-- BackButton
    
    class ColorBlindOption {
        - boolean focused
        - boolean enabled
        - Setting 함수
        - DisPlay 함수
        - Action 함수
    }
    class SoundOption {
        - boolean focused
        - float volume
        - Setting 함수
        - DisPlay 함수
        - Action 함수 
    }
    class BackButton {
        - boolean focused
        - Setting 함수
        - DisPlay 함수
        - Action 함수 -> 다시 StartMenu를 보여줘야 됨.
    }


    


    class ToggleItem implements MenuItem {
    bool focused = false
    Style style
    string label          // 예: "색약 모드", "소리"
    bool value            // ON=true / OFF=false

    void applySetting(Style s) { style = s }
    void setFocus(bool on) { focused = on }

    void display(RenderCtx ctx) {
        text = label + ": " + (value ? "ON" : "OFF")
        ctx.drawToggle(text, style, focused)
    }

    void action(MenuContext ctx) {
        value = !value
        // 변경 시 시스템 반영(옵션)
        if (label == "색약 모드") ctx.applyColorBlindMode(value)
        if (label == "소리")     ctx.applySoundEnabled(value)
    }
}

class BackButton implements MenuItem {
    bool focused = false
    Style style
    void applySetting(Style s) { style = s }
    void setFocus(bool on) { focused = on }
    void display(RenderCtx ctx) { ctx.drawButton(style, focused, text="Back") }
    void action(MenuContext ctx) { ctx.goToStartMenu() }
}

class SettingMenu {
    List<MenuItem> items
    int focusIndex = 0  // 첫 토글부터 포커스
    // 기본값: 색약모드 OFF, 소리 ON(예시)

    void init() {
        items = [
          ToggleItem(label="색약 모드", value=false),
          ToggleItem(label="소리",     value=true),
          BackButton()
        ]
        applyDefaultStyles()
        applyFocus()
        // (선택) 저장된 설정 불러오기
        // loadSettings() → items[0].value, items[1].value 갱신
    }

    void applyDefaultStyles() {
        // 각 항목에 Style 적용 (폰트/스케일/색/포커스 색 등)
        // items[i].applySetting(styleFor(i))
    }

    void applyFocus() {
        for i in 0..items.size-1:
            items[i].setFocus(i == focusIndex)
    }

    void render(RenderCtx ctx) {
        for item in items: item.display(ctx)
    }

    void handleInput(KeyEvent e) {
        switch (e.code) {
            case UP:    moveFocus(-1)
            case DOWN:  moveFocus(+1)
            case ENTER: invokeAction()
            case ESC:   goBack()
            default:    /* 필요 시 안내 텍스트 생성 */
        }
    }

    void moveFocus(int delta) {
        focusIndex = clampOrWrap(focusIndex + delta, 0, items.size-1)
        applyFocus()
    }

    void invokeAction() {
        items[focusIndex].action()
        // (선택) 즉시 저장
        // saveSettings(Settings.colorBlind, Audio.enabled)
    }
}