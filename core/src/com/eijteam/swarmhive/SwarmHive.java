package com.eijteam.swarmhive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


public class SwarmHive extends Game {

	private AssetManager manager;

	@Override
	public void create () {
		manager = new AssetManager();

		for(int i=1; i<=7;i++){
			manager.load("Felpudo/felpudo" + i + ".png", Texture.class);
		}

		setScreen(new SplashScreen(this, manager));
	}


	@Override
	public void dispose () {
		manager.dispose();
	}

}
