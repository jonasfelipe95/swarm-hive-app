package com.eijteam.swarmhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayScreen implements Screen {
    private Game game;
    private AssetManager manager;
    private Music backgroundMusic;
    private TextButton btnPlay;

    private SpriteBatch batch;
    private Texture beehiveLogo;
    private Texture swarmHiveFlagLogo;
    private OkHttpClient client;



    private Stage stage;

    PlayScreen(Game game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background-play-screen.mp3"));

        this.client = new OkHttpClient();

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);


         Skin skin = new Skin(Gdx.files.internal("level-plane-ui.json"));

         String url = "https://viacep.com.br/ws/13036140/json/";

        final Request request = new Request.Builder().url(url).build();

        this.btnPlay = new TextButton("Jogar", skin, "primary");

        btnPlay.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("hiii");

client.newCall(request).enqueue(new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()) {
            System.out.println(response.body().string());
        }

    }
});

                // game.getSoundManager().play( TyrianSound.CLICK );

            }
        } );
        stage.addActor(btnPlay);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        beehiveLogo = new Texture("beehive-with-bees.png");
        swarmHiveFlagLogo = new Texture("flag-logo.png");
    }

    @Override
    public void render(float delta) {
        backgroundMusic.play();


        if(Gdx.input.justTouched()){
            Gdx.app.log("Log", "justTouched! " + Gdx.input.getX() + " " + Gdx.input.getY());
        }

        Gdx.gl.glClearColor((float) (226.0 / 255.0), (float) (233 / 255.0), (float) (174 / 255.0), 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width =  Gdx.graphics.getWidth();
        int height =  Gdx.graphics.getHeight();

        batch.begin();
        this.btnPlay.setPosition(width * 0.5f - btnPlay.getWidth() / 2,height * 0.1f);
        this.btnPlay.setSize(320,80);
        this.btnPlay.setRound(true);
        this.btnPlay.getLabel().setFontScale(2.5f, 2.5f);
        this.btnPlay.getStyle().fontColor.set(Color.BLACK);

              //  if(this.btnPlay.i()) Gdx.app.log("app", "toquei");

        stage.draw();
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

        backgroundMusic.dispose();
    }
}
