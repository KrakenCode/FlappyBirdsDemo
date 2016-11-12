package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daltonsumrall.game.FlappyDemo;

/**
 * Created by dalton on 10/2/16.
 */

public class MenuState extends State {
    private Texture playButton;
    private BitmapFont font;
    private GlyphLayout name;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture("playbtn.png");

        FlappyDemo.parameter.size = 20;
        font = FlappyDemo.generator.generateFont(FlappyDemo.parameter);
        font.setUseIntegerPositions(false); //stops score from shaking

        name = new GlyphLayout();
        name.setText(font,"Created by Dalton Sumrall");

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
        font.draw(sb, name, camera.position.x - name.width/2, name.height*2);
        sb.end();
    }



    @Override
    public void dispose() {
        playButton.dispose();
        font.dispose();
    }


}
