package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.daltonsumrall.game.FlappyDemo;
import com.daltonsumrall.game.sprites.Bird;
import com.daltonsumrall.game.sprites.Tube;

import static java.awt.SystemColor.text;

/**
 * Created by dalton on 10/2/16.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        background = new Texture("bg.png");
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube( i * (TUBE_SPACING + Tube.TUBE_WIDTH) ) );
        }
    }



    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }


    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x + 80;
        for(Tube tube: tubes) {
            if( (camera.position.x - (camera.viewportWidth/2)) >
                (tube.getPosTopTube().x + tube.getTopTube().getWidth()) )
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        camera.update();
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube tube: tubes){
            tube.dispose();
        }
    }


}
