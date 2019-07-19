/*





 */





package com.dzneurals.Anasiw;
 import android.inputmethodservice.*;
 import android.view.*;
 import android.view.inputmethod.*;
 import android.media.*;

public class SimpleIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener{

    private KeyboardView kv;
    private KeyboardsDefinitions keyboardDefinitions;
    public Keyboard currentKeyborad ;
    private boolean isInSymbolsMode;
    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        isInSymbolsMode = false;
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardDefinitions = new KeyboardsDefinitions(this);
        kv.setKeyboard(keyboardDefinitions.qwertyKeyboard);
        currentKeyborad = keyboardDefinitions.qwertyKeyboard;
        kv.setOnKeyboardActionListener(this);
        kv.setPreviewEnabled(false);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                currentKeyborad.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_MODE_CHANGE:
                if (isInSymbolsMode)
                {
                    kv.setKeyboard(currentKeyborad);
                    isInSymbolsMode = false;
                }
                else
                {
                    kv.setKeyboard(keyboardDefinitions.symbolsKeyboard);
                    isInSymbolsMode = true;
                }
                break;
            case -101:
                currentKeyborad = keyboardDefinitions.GetNext(currentKeyborad);
                kv.setKeyboard(currentKeyborad);
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }
}