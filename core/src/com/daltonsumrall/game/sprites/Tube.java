package com.daltonsumrall.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daltonsumrall.game.FlappyDemo;

import java.util.Random;


/**
 * Created by dalton on 10/2/16.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Vector2 posTopTube, posBottomTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;



    public Tube(float x){

        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - FlappyDemo.bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, FlappyDemo.topTube.getWidth(), FlappyDemo.topTube.getHeight());
        boundsBot = new Rectangle(posBottomTube.x, posBottomTube.y, FlappyDemo.bottomTube.getWidth(), FlappyDemo.bottomTube.getHeight());
    }



    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - FlappyDemo.bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }


    public Texture getTopTube() {
        return FlappyDemo.topTube;
    }



    public Texture getBottomTube() {
        return FlappyDemo.bottomTube;
    }



    public Vector2 getPosTopTube() {
        return posTopTube;
    }



    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void dispose(){

    }

}
