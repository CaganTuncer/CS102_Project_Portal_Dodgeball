package com.mygdx.portaldodgeball.Tools;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {

    public Array<KeyState> keyStates = new Array<KeyState>();

    public InputManager() {
        for (int i = 0; i < 256; i++) {
            keyStates.add(new KeyState(i));
        }
    }

    public boolean isKeyPressed(int key){
        return keyStates.get(key).pressed;
    }
    public boolean isKeyDown(int key){
        return keyStates.get(key).down;
    }
    public boolean isKeyReleased(int key){
        return keyStates.get(key).released;
    }

    public class InputState {
        public boolean pressed = false;
        public boolean down = false;
        public boolean released = false;
    }

    public class KeyState extends InputState {

        public int key;

        public KeyState(int key) {
            this.key = key;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        keyStates.get(keycode).pressed = true;
        keyStates.get(keycode).down = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyStates.get(keycode).down = false;
        keyStates.get(keycode).released = true;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void update(){
        for (int i = 0; i < 256; i++) {
            KeyState k = keyStates.get(i);
            k.pressed = false;
            k.released = false;
        }
    }
}
