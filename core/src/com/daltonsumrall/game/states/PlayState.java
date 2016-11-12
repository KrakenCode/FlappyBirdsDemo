package com.daltonsumrall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.daltonsumrall.game.FlappyDemo;
import com.daltonsumrall.game.sprites.Bird;
import com.daltonsumrall.game.sprites.Tube;



/**
 * Created by dalton on 10/2/16.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -60;
    private Bird bird;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;
    private double iScore;
    private String scoreString;
    private BitmapFont scoreFont;



    public PlayState(GameStateManager gsm) {
        super(gsm);

        /************* Score *************/
        FlappyDemo.parameter.size = 20;
        scoreFont = FlappyDemo.generator.generateFont(FlappyDemo.parameter);
        scoreFont.setUseIntegerPositions(false); //stops score from shaking
        iScore = 0;
        scoreString = "0";
        /*********************************/

        bird = new Bird(50, 300);
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth/2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth/2)) + FlappyDemo.ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++){
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
        updateGround();
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x + 80;
        iScore+= .1;
        scoreString = "" + ((int) iScore);
        for(Tube tube: tubes) {
            if( (camera.position.x - (camera.viewportWidth/2)) >
                (tube.getPosTopTube().x + tube.getTopTube().getWidth()) )
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds())){
                gameOver();
                break;
            }
        }
        if(bird.getBounds().y <= FlappyDemo.ground.getHeight() + GROUND_Y_OFFSET){
            gameOver();
        }
        camera.update();
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(FlappyDemo.background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(FlappyDemo.ground, groundPos1.x, groundPos1.y);
        sb.draw(FlappyDemo.ground, groundPos2.x, groundPos2.y);

        scoreFont.draw(sb, scoreString, (camera.position.x - (camera.viewportWidth/2)) + 10,
                camera.viewportHeight - 10);
        sb.end();
    }


    @Override
    public void dispose() {
        bird.dispose();
        scoreFont.dispose();
        for (Tube tube: tubes){
            tube.dispose();
        }
    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth/2) > groundPos1.x + FlappyDemo.ground.getWidth()){
            groundPos1.add(FlappyDemo.ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth/2) > groundPos2.x + FlappyDemo.ground.getWidth()){
            groundPos2.add(FlappyDemo.ground.getWidth() * 2, 0);
        }
    }

    private void gameOver(){
        gsm.set(new GameOverState(gsm, scoreString));
    }
}
