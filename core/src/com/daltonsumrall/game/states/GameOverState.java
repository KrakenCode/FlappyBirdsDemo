package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daltonsumrall.game.FlappyDemo;


/**
 * Created by dalton on 11/11/16.
 */

public class GameOverState extends State {

    private BitmapFont font;
    private String scoreString;


    public GameOverState(GameStateManager gsm, String scoreString) {
        super(gsm);

        FlappyDemo.parameter.size = 30;
        font = FlappyDemo.generator.generateFont(FlappyDemo.parameter);
        font.setUseIntegerPositions(false); //stops score from shaking
        this.scoreString = "Final Score: " +  scoreString;
    }

    @Override
    protected void handleInput() {
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
        font.draw(sb, "Game Over", camera.position.x - 45, camera.position.y + 50);
        font.draw(sb, scoreString,
                camera.position.x - 58,
                camera.position.y + 25);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
