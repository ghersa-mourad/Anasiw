package com.dzneurals.Anasiw;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

public class TamazightKeyboardView extends KeyboardView {
    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;

    public TamazightKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TamazightKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
