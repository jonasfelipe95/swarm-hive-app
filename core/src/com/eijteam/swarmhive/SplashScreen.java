package com.eijteam.swarmhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class SplashScreen implements Screen {
    private Game game;
    private AssetManager manager;

    private BitmapFont font;

    private float time = 0;

    private SpriteBatch batch;
    private Texture bar;

    private Texture logo;
    private Sound sound;


    SplashScreen(Game game, AssetManager manager){
        this.game = game;
        this.manager = manager;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        bar = new Texture("bar.png");
        logo = new Texture("logo.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("som1.mp3"));

        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Audiowide-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (0.05f*Gdx.graphics.getWidth());
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);

        generator.dispose();
    }

    @Override
    public void render(float delta) {
        time += delta;
       if(manager.update() && time >= 2){
           sound.play();
            game.setScreen(new MainScreen(game, manager));
       }

        Gdx.gl.glClearColor(1,1,0,0.2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            font.draw(batch, "Swarm Hive", Gdx.graphics.getWidth()/2 - 400, 0.5f*Gdx.graphics.getHeight() );
            batch.draw(bar, 0.1f*Gdx.graphics.getWidth(), 0.15f*Gdx.graphics.getHeight(), 0.8f*Gdx.graphics.getWidth()*Math.min(manager.getProgress(), time/2f), 0.1f*Gdx.graphics.getHeight());
            batch.draw(logo, 0.5f*Gdx.graphics.getWidth() - logo.getWidth()/2 , Gdx.graphics.getHeight() - (0.1f*Gdx.graphics.getHeight() + logo.getHeight()));
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

    }
}
