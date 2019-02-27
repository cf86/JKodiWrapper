package com.cf.jkodiwrapper.methods.input

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class InputMethod(var methodName: String) : KodiMethod {

    BACK("Input.Back"),
    CONTEXT_MENU("Input.ContextMenu"),
    DOWN("Input.Down"),
    EXECUTE_ACTION("Input.ExecuteAction"),
    HOME("Input.Home"),
    INFO("Input.Info"),
    LEFT("Input.Left"),
    RIGHT("Input.Right"),
    SELECT("Input.Select"),
    SEND_TEXT("Input.SendText"),
    SHOW_OSD("Input.ShowOSD"),
    SHOW_PLAYER_PROCESS_INFO("Input.ShowPlayerProcessInfo"),
    UP("Input.Up");

    override fun toString(): String {
        return methodName
    }
}