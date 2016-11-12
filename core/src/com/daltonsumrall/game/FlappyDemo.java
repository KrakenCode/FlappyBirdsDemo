package com.daltonsumrall.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.daltonsumrall.game.sprites.Animation;
import com.daltonsumrall.game.states.GameStateManager;
import com.daltonsumrall.game.states.MenuState;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Flappy Bird";
    private GameStateManager gsm;
    private SpriteBatch batch; //only ever create one...very heavy files
    private Music music;
    public static Sound flap;
    public static Texture background;
    public static Texture ground;
    public static Texture bird;
    public static Texture topTube, bottomTube;
    public static Animation birdAnimation;
    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontParameter parameter;

	@Override
	public void create() {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        bird = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(bird), 3, 0.5f);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        parameter = new FreeTypeFontParameter();

        gsm.push(new MenuState(gsm));
    }



	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}



	@Override
	public void dispose() {
        super.dispose();
        music.dispose();
        flap.dispose();
        background.dispose();
        ground.dispose();
        bird.dispose();
        topTube.dispose();
        bottomTube.dispose();
        generator.dispose();
        batch.dispose();
	}


}
