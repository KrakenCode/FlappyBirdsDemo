package com.daltonsumrall.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.daltonsumrall.game.FlappyDemo;


/**
 * Created by dalton on 10/2/16.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;



    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, FlappyDemo.bird.getWidth()/3, FlappyDemo.bird.getHeight());
    }



    public void update(float deltaTime){
        FlappyDemo.birdAnimation.update(deltaTime);
        if(position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);
        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/deltaTime);
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }



    public TextureRegion getTexture() {
        return FlappyDemo.birdAnimation.getFrame();
    }



    public void jump(){
        velocity.y = 250;
        FlappyDemo.flap.play(0.5f);
    }

    public void dispose(){

    }
}
