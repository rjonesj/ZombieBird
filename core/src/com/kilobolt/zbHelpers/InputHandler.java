package com.kilobolt.zbHelpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricky on 4/26/2016.
 */
public class InputHandler implements InputProcessor {

    private GameWorld myWorld;
    private Bird myBird;

    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;

    private float scaleFactorX;
    private float scaleFactorY;

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(GameWorld myWorld, float scaleFactorX,
                        float scaleFactorY) {
        // myBird now represents the gameWorld's bird.
        this.myWorld = myWorld;
        myBird = myWorld.getBird();

        int midPointY = myWorld.getMidPointY();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                midPointY + 50, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
    }



    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        System.out.println(screenX + " " + screenY);
        if (myWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
            myWorld.start();
        }

        myBird.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            myWorld.restart();
        }

        return true;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }

        return false;
    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Input.Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myBird.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }

        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
