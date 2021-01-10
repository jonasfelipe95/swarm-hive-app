package com.eijteam.swarmhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SplashScreen implements Screen {
    private Game game;
    private AssetManager manager;
    private ShapeRenderer shapeRenderer;
    private Sound loadedSound;


    private float time = 0;

    private SpriteBatch batch;
    private Texture beehiveLogo;
    private Texture swarmHiveFlagLogo;

    private Texture logo;


    SplashScreen(Game game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        this.loadedSound = Gdx.audio.newSound(Gdx.files.internal("sounds/loaded.mp3"));
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        beehiveLogo = new Texture("beehive-with-bees.png");
        swarmHiveFlagLogo = new Texture("flag-logo.png");
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        //  logo = new Texture("logo.png");
    }

    @Override
    public void render(float delta) {
        time += delta;
        if (manager.update() && time >= 2) {
            loadedSound.play();
            game.setScreen(new PlayScreen(game, manager));

        }
        if(Gdx.input.justTouched()){
            Gdx.app.log("Log", "justTouched! " + Gdx.input.getX() + " " + Gdx.input.getY());
        }

        Gdx.gl.glClearColor((float) (226.0 / 255.0), (float) (233 / 255.0), (float) (174 / 255.0), 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width =  Gdx.graphics.getWidth();
        int height =  Gdx.graphics.getHeight();

        shapeRenderer.begin();

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor((float) (119 / 255.0), (float) (112 / 255.0), (float) (112 / 255.0), 1);
        shapeRenderer.rect(0.2f * width, height * 0.15f, 0.6f * width, 40);

        shapeRenderer.setColor((float) (227 / 255.0), (float) (235 / 255.0), (float) (12 / 255.0), 1);
        shapeRenderer.rect(0.2f * width, height * 0.15f, 0.6f * width * Math.min(manager.getProgress(), time/2f), 40);

        shapeRenderer.end();

        batch.begin();

      /*  batch.draw(
                beehiveLogo,
                0.1f * Gdx.graphics.getWidth(),
                0.15f * Gdx.graphics.getHeight(),
                0.8f * Gdx.graphics.getWidth() * Math.min(manager.getProgress(), time / 2f),
                0.1f * Gdx.graphics.getHeight()
        );*/

      //  swarmHiveFlagLogo
        batch.draw(
                beehiveLogo,
                0.5f * width - (382 * 1.4f) / 2,
                0.5f * height - (356 * 1.4f) / 2,
                382 * 1.4f,
                356 * 1.4f
        );

        batch.draw(
                swarmHiveFlagLogo,
                0.5f * width - (484 * 1.4f) / 2,
                0.8f * height - (137 * 1.4f) / 2,
                484 * 1.4f,
                137 * 1.4f
        );


//        batch.draw(logo, 0.5f*Gdx.graphics.getWidth() - logo.getWidth()/2 , Gdx.graphics.getHeight() - (0.1f*Gdx.graphics.getHeight() + logo.getHeight()));
        //batch.draw(logo, 0.5f*Gdx.graphics.getWidth() - logo.getWidth()/2, Gdx.graphics.getHeight() - (0.1f*Gdx.graphics.getHeight() - logo.getHeight()), (float) (Gdx.graphics.getWidth()/0.4), 0.5f*Gdx.graphics.getHeight() );
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        beehiveLogo.dispose();
        swarmHiveFlagLogo.dispose();
        shapeRenderer.dispose();
        loadedSound.dispose();
    }
}
