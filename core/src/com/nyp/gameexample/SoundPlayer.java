package com.nyp.gameexample;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundPlayer {

    private Music paletteTownTheme = Gdx.audio.newMusic(Gdx.files.internal("palette-town-theme.mp3"));

    public Music getPaletteTownTheme() {
        return paletteTownTheme;
    }
}
