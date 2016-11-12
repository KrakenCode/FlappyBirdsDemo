package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daltonsumrall.game.FlappyDemo;

/**
 * Created by dalton on 10/2/16.
 */

public class MenuState extends State {
    private Texture playButton;




    public MenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture("playbtn.png");
    }



    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }



    @Override
    public void update(float deltaTime) {
        handleInput();
    }



    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(FlappyDemo.background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(playButton, camera.position.x - (playButton.getWidth()/2), camera.position.y);
        sb.end();
    }



    @Override
    public void dispose() {
        playButton.dispose();
    }


}
