package org.tetris.menu.start.model;

interface MenuItem {
    // 상태
    bool focused

    // 설정(스타일): 폰트, 스케일, 색, 텍스트 등
    void applySetting(Style style)

    // 표시
    void display(RenderCtx ctx)

    // 엔터/클릭 등 확정 입력 처리
    void action(MenuContext ctx)

    // 선택 포커스 on/off
    void setFocus(bool on)
}

// 스타일 묶음 (UI 공통 속성)
struct Style {
    Font font
    float textScale
    Color textColor
    Color focusColor
    string text
}

class GameNameText implements MenuItem {
    bool focused = false
    Style style

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) { ctx.drawText(style.text, style) }
    void action(MenuContext ctx) { /* 무동작 */ }
    void setFocus(bool on) { focused = on }
}

class CorrectButtonText implements MenuItem {
    bool focused = false
    Style style
    Timer life // 잠깐 표시 후 사라질 수 있음(옵션)

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) { ctx.drawText(style.text, style) }
    void action(MenuContext ctx) { /* 무동작 */ }
    void setFocus(bool on) { focused = on }
}

class StartGameButton implements MenuItem {
    bool focused = false
    Style style

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) { 
        ctx.drawButton(style, focused) 
    }
    void action(MenuContext ctx) { ctx.startGame() }
    void setFocus(bool on) { focused = on }
}

class SettingButton implements MenuItem {
    bool focused = false
    Style style

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) { ctx.drawButton(style, focused) }
    void action(MenuContext ctx) { ctx.openSettings() }
    void setFocus(bool on) { focused = on }
}

class ExitButton implements MenuItem {
    bool focused = false
    Style style

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) { ctx.drawButton(style, focused) }
    void action(MenuContext ctx) { ctx.exitApp() }
    void setFocus(bool on) { focused = on }
}

// 점수판(버튼 아님, 선택 없음)
class HighestScoreBoard implements MenuItem {
    bool focused = false
    Style style
    int highestScore

    void applySetting(Style s) { style = s }
    void display(RenderCtx ctx) {
        ctx.drawPanel(style)
        ctx.drawText("High Score: " + highestScore, style)
    }
    void action(MenuContext ctx) { /* 무동작 */ }
    void setFocus(bool on) { focused = on }

    // 파일 I/O (간단 개념)
    void loadFromFile(String path) { highestScore = File.readInt(path, default=0) }
    void saveToFile(String path) { File.writeInt(path, highestScore) }
}