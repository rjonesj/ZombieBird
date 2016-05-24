package com.kilobolt.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbHelpers.AssetLoader;
import com.kilobolt.zbHelpers.InputHandler;

/**
 * Created by Ricky on 4/25/2016.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY); // initialize world
        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
        renderer = new GameRenderer(world, (int) gameHeight, midPointY); // initialize renderer

        Gdx.app.log("GameScreen", "ScreenWidth: "+screenWidth+", screenHeight: "+screenHeight+", gameWidth: "+gameWidth+", gameHeight: "+gameHeight);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

        Gdx.app.log("GameScreen", "resizing");
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        // Leave blank
    }
}
