package com.kilobolt.zombiebird;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kilobolt.zombiebird.ZBGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		config.title = "Zombie Bird";
//		config.width = 272;
//		config.height = 408;
		initialize(new ZBGame(), config);
	}
}
