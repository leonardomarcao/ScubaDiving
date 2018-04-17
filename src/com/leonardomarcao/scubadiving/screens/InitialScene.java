package com.leonardomarcao.scubadiving.screens;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import com.leonardomarcao.scubadiving.main.ScubaDiving;
import java.io.File;
import javax.swing.JOptionPane;
import org.newdawn.slick.Input;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class InitialScene extends BasicGameState{
    
        private Animation diver, diverMovementLeft, diverMovementRight;
        private Image background;          
        private float xDiver, yDiver;
        private float xBubble, yBubble;
        private float xBackground, yBackground;
        private static final float SPEED = 0.2f;
        private static final float SPEEDBACKGROUND = 0.07f;
        private static final int ANIMATIONSPEED = 150;
        private ParticleSystem systemBubbleParticleRight;
        private ParticleSystem systemBubbleParticleLeft;
	private ConfigurableEmitter emitterBubbleParticleRight;
        private ConfigurableEmitter emitterBubbleParticleLeft;

	// ID we return to class 'Application'
	public static final int ID = 2;

	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            //background initial scene
            //background property contains 5785 of width and 1600 of height
            background = new Image("Assets\\BackgroundUnderWater\\backgroundInitialScene.png");
            /*
                set the image arrays needed for the animation of diver
            */
            Image[] movementRight = {
              new Image("Assets\\SpriteScubaCharacter\\00.png"),
              new Image("Assets\\SpriteScubaCharacter\\01.png"),
              new Image("Assets\\SpriteScubaCharacter\\02.png"),
              new Image("Assets\\SpriteScubaCharacter\\03.png"),
              new Image("Assets\\SpriteScubaCharacter\\04.png"),
              new Image("Assets\\SpriteScubaCharacter\\05.png"),
              new Image("Assets\\SpriteScubaCharacter\\06.png"),
              new Image("Assets\\SpriteScubaCharacter\\07.png"),
            };
            Image[] movementLeft = {
              new Image("Assets\\SpriteScubaCharacter\\08.png"),
              new Image("Assets\\SpriteScubaCharacter\\09.png"),
              new Image("Assets\\SpriteScubaCharacter\\10.png"),
              new Image("Assets\\SpriteScubaCharacter\\11.png"),
              new Image("Assets\\SpriteScubaCharacter\\12.png"),
              new Image("Assets\\SpriteScubaCharacter\\13.png"),
              new Image("Assets\\SpriteScubaCharacter\\14.png"),
              new Image("Assets\\SpriteScubaCharacter\\15.png"),  
            };
            /*
                set the animations with images arrays presets
            */
            diverMovementLeft = new Animation(movementRight, ANIMATIONSPEED);
            diverMovementRight = new Animation(movementLeft, ANIMATIONSPEED);
            
            //set initial animation of diver
            diver = diverMovementLeft;
            //set position x and y based on application property
            xDiver = ScubaDiving.HEIGHT/6;
            yDiver = ScubaDiving.WIDTH/2; 
            //bubble particle position
            xBubble =  xDiver+200;
            yBubble = yDiver;
            /*
                plotting background initial scene x = 0 and 
                1600 being background height and 600 being application height
                1600-600 = 1000 -> -1000 to start on the y = 0 axis 
            */            
            xBackground = 0;
            yBackground = -1000;
            //configuring particle system and emitter
            try {
                //bubble particle image 
                Image bubbleImage = new Image("data/bubbleparticle.png", false);
                //load emitter for bubble particle right                
                systemBubbleParticleRight = new ParticleSystem(bubbleImage,1500);			
                File xmlFileEmitterBubbleParticleRight;
                xmlFileEmitterBubbleParticleRight = new File("data/bubbleParticleRight.xml");
                emitterBubbleParticleRight = ParticleIO.loadEmitter(xmlFileEmitterBubbleParticleRight);                
                //load emitter for bubble particle left                
                systemBubbleParticleLeft = new ParticleSystem(bubbleImage,1500);			
                File xmlFile = new File("data/bubbleParticleLeft.xml");
                emitterBubbleParticleLeft = ParticleIO.loadEmitter(xmlFile);                
            } catch (Exception ex) {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }     
             //initialization with particle bubble particle right                    
            systemBubbleParticleRight.addEmitter(emitterBubbleParticleRight); 
            systemBubbleParticleLeft.addEmitter(emitterBubbleParticleLeft);
            //set visible system bubble particle left like false
            systemBubbleParticleLeft.setVisible(false);
            //system use set points
            systemBubbleParticleRight.setUsePoints(true);
            systemBubbleParticleLeft.setUsePoints(true);
            //blend mode of system particle
            systemBubbleParticleRight.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);	                
            systemBubbleParticleLeft.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);	                                
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
                //draw background initial scene
		g.drawImage(background, xBackground, yBackground);
                //render the particle system
		systemBubbleParticleRight.render();
                systemBubbleParticleLeft.render();
                //draw diver character 
                diver.draw((int) xDiver, (int) yDiver);                 
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {                                
                //background has motion according to delta and speed background scaling
                xBackground -= delta * SPEEDBACKGROUND;
                //movement of the character in relation to the movement of the background
                xDiver -= ((delta * SPEEDBACKGROUND)/2);
                if (diver == diverMovementLeft)
                    xBubble=xDiver+200;
                else
                    xBubble=xDiver;                
                /*
                    If the background case reaches its end = -4985, 
                    return it to the initial position of the axis. 
                    This coordinate is in relation to 5785 
                    (background image width) - 600 (application width)
                */
                if (xBackground <= -4985){
                    xBackground = 0;
                }                
                //get user input
		Input in = gc.getInput();                
                /**
                * get user input and execute the animation
                * need to save last user input for diver animation 
                */
                if (in.isKeyDown(Input.KEY_UP)|| in.isKeyDown(Input.KEY_W)){  
                    //move character in y
                    yDiver -= delta * SPEED;  
                    //block character in container
                    if (yDiver <= 0){
                        yDiver += delta * SPEED;
                    }
                    //move bubble particle with character
                    yBubble=yDiver;
                }else if (in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S)){
                    //move character in y
                    yDiver += delta * SPEED;
                    //block character in container
                    if (yDiver >= 520){
                        yDiver -= delta * SPEED;
                    }
                    //move bubble particle with character
                    yBubble=yDiver;                    
                }else if (in.isKeyDown(Input.KEY_LEFT)|| in.isKeyDown(Input.KEY_A)){
                    //move character in x
                    xDiver -= delta * SPEED;
                    //move bubble particle with character
                    xBubble = xDiver;                    
                    //set visible false for particle system right and true for particle system right
                    systemBubbleParticleRight.setVisible(false);
                    systemBubbleParticleLeft.setVisible(true);
                    //load animation for character
                    diver = diverMovementRight;
                    //update animation speed
                    diver.update(delta);
                }else if (in.isKeyDown(Input.KEY_RIGHT)|| in.isKeyDown(Input.KEY_D)){
                    //move character in x
                    xDiver += delta * SPEED;                    
                    //block character in container
                    if (xDiver >= 588){
                        xDiver -= delta * SPEED;
                    }
                    //move bubble particle with character
                    xBubble = xDiver+200;                   
                    //set visible true for particle system right and false for particle system right
                    systemBubbleParticleRight.setVisible(true);
                    systemBubbleParticleLeft.setVisible(false);
                    //load animation for character
                    diver = diverMovementLeft;
                    //update animation speed
                    diver.update(delta);                    
                }    
                //reload position of bubble particle
                systemBubbleParticleRight.setPosition(xBubble, yBubble);
                systemBubbleParticleLeft.setPosition(xBubble, yBubble);
                //update system particle
                systemBubbleParticleRight.update(delta);
                systemBubbleParticleLeft.update(delta);
                /*
                    Calling game over character case stay out of the container.
                    -200 because it is the width of the character (200 px) in 
                    relation to the width of the application
                */
                if (xDiver <= -200){
                    sbg.enterState(3);
                }                
                //escape means, close the program
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}                                
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return InitialScene.ID;
	}
}