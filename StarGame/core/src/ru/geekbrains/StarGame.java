package ru.geekbrains;

import com.badlogic.gdx.Game;

import ru.geekbrains.Screen.MenuScreen;

public class StarGame extends Game {
	@Override
	public void create() {

		setScreen(new MenuScreen());
	}
}
