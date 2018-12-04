package com.dzneurals.tamazightboard;

import android.content.Context;
import java.lang.Object;
import android.content.Context;
import android.content.ContextWrapper ;
import android.app.Service;
import android.inputmethodservice.*;
import android.view.*;
import android.view.inputmethod.*;
import android.media.*;

public class KeyboardsDefinitions {

    public Keyboard qwertyKeyboard;
    public Keyboard symbolsKeyboard;
    public Keyboard tamazightLatinKeyboard;
    public Keyboard tifinaghsKeyboard;
    public Keyboard arabeKeyborad;
    public KeyboardsDefinitions (Context context){
        qwertyKeyboard = new Keyboard(context, R.xml.qwerty);
        symbolsKeyboard = new Keyboard(context, R.xml.symboles);
        tamazightLatinKeyboard = new Keyboard(context , R.xml.tamazightlatin);
        tifinaghsKeyboard = new Keyboard(context , R.xml.tifinaghs);
        arabeKeyborad = new Keyboard(context , R.xml.arabe);
    }
    public Keyboard GetNext(Keyboard current)
    {
        if (current == qwertyKeyboard)
            return arabeKeyborad;
        else if (current == arabeKeyborad)
            return tamazightLatinKeyboard;
        else if (current == tamazightLatinKeyboard)
            return tifinaghsKeyboard;
        else if (current == tifinaghsKeyboard)
            return qwertyKeyboard;
        else
            return qwertyKeyboard;
    }
}
