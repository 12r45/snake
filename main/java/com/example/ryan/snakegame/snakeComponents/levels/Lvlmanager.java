package com.example.ryan.snakegame.snakeComponents.levels;

import android.content.SharedPreferences;

/**
 * Created by ryan on 3/16/2015.
 */
public class Lvlmanager {

    SharedPreferences levels;
    public static final String PREFS_NAME = "levels";

    public Lvlmanager(SharedPreferences l){
        levels = l;
        store();
    }

    private void store()
    {
        SharedPreferences.Editor editor = levels.edit();
        editor.putString("l1","obs half half 100 300");
        editor.commit();
    }



}
