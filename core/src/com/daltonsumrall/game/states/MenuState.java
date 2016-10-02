package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daltonsumrall.game.FlappyDemo;

/**
 * Created by dalton on 10/2/16.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playButton;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }



    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }



    @Override
    public void update(float deltaTime) {
        handleInput();
    }



    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        spriteBatch.draw(playButton, (FlappyDemo.WIDTH / 2)-(playButton.getWidth()/2), FlappyDemo.HEIGHT/2);
        spriteBatch.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }


}
