package screens;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import main.ScubaDiving;
import java.io.File;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

public class InitialScene extends BasicGameState {

    private Animation diver, diverMovementLeft, diverMovementRight;
    private Animation enemyAnimation1, enemyAnimation2;
    private Image background;
    private float xDiver, yDiver;
    private float xBubble, yBubble;
    private float xBackground, yBackground;
    private static float speedDiver = 0.2f;
    private static float speedBackground = 0.07f;
    private static final float BUOYANT_FORCE = 0.05f;
    private static final int ANIMATION_SPEED_DIVER = 150;
    private static final int ANIMATION_SPEED_ENEMY = 150;
    private ParticleSystem systemBubbleParticleRight;
    private ParticleSystem systemBubbleParticleLeft;
    private ConfigurableEmitter emitterBubbleParticleRight;
    private ConfigurableEmitter emitterBubbleParticleLeft;
    private Image shit, banana, apple, fish;
    private float xShit, yShit, xBanana, yBanana, xApple, yApple, xFish, yFish;
    Shape div;
    Rectangle banRect, shitRect, appleRect, fishRect;
    public int points = 0;
    private Sound coin;
    private Music underWater;
    private Sound gameOver;
    private float xEnemy1, yEnemy1, xEnemy2, yEnemy2;
    private Rectangle enemyRect1, enemyRect2;
    private int diverHealth = 100;
    private Random ran;
    private Color collider;

    // ID we return to class 'Application'
    public static final int ID = 2;    

    // init-method for initializing all resources
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException { 
        //stop music of main menu
        
        
        //load and set initial scene/state sounds    
        coin = new Sound("Assets\\InitialScene\\coin.ogg");       
        gameOver = new Sound("Assets\\GamerOver\\GameOver.ogg");
        underWater = new Music("Assets\\InitialScene\\UnderWater.ogg");        
        //underWater.loop();
        underWater.setVolume(0.3f);
        
        //set up garbage from the scene as well as your collision body
        banana = new Image("Assets\\InitialScene\\Garbage\\banana.png");
        banRect = new Rectangle(xBanana, yBanana, banana.getWidth(), banana.getHeight());
        shit = new Image("Assets\\InitialScene\\Garbage\\shit.png");
        shitRect = new Rectangle(xShit, yShit, 64, 64);
        apple = new Image("Assets\\InitialScene\\Garbage\\apple.png");
        appleRect = new Rectangle(xApple, yApple, 64, 64);
        fish = new Image("Assets\\InitialScene\\Garbage\\fish.png");
        fishRect = new Rectangle(xFish, yShit, 64, 64);
        
        try {
            //background initial scene
            //background property contains 5785 of width and 1600 of height
            background = new Image("Assets\\InitialScene\\BackgroundUnderWater\\backgroundInitialScene.png");
        } catch (SlickException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
               set the image arrays needed for the animation of diver
         */
        Image[] movementRight = {
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\00.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\01.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\02.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\03.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\04.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\05.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\06.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\07.png"),};
        Image[] movementLeft = {
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\08.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\09.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\10.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\11.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\12.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\13.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\14.png"),
            new Image("Assets\\InitialScene\\SpriteScubaCharacter\\15.png"),};
        
        //load enemy data
        loadEnemy();
        
        /*
                set the animations with images arrays presets
         */
        diverMovementLeft = new Animation(movementRight, ANIMATION_SPEED_DIVER);
        diverMovementRight = new Animation(movementLeft, ANIMATION_SPEED_DIVER);
        float diverCoord[] = {80, 50, 185, 20, 290, 25, 280, 80, 110, 80};
        div = new Polygon(diverCoord);

        //set initial animation of diver
        diver = diverMovementLeft;
        //set position x and y based on application property
        xDiver = ScubaDiving.HEIGHT / 6;
        yDiver = ScubaDiving.WIDTH / 2;
        //bubble particle position
        xBubble = xDiver + 200;
        yBubble = yDiver;
        /*
                plotting background initial scene x = 0 and 
                1600 being background height and 600 being application height
                1600-600 = 1000 -> -1000 to start on the y = 0 axis 
         */
        ran = new Random();
        xBackground = 0;
        yBackground = 0;
        
        //configure x and y initial for garbage and enemies through random generation
        xBanana = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yBanana = ran.nextInt((int) (540));

        xApple = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yApple = ran.nextInt((int) (540));

        xFish = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yFish = ran.nextInt((int) (540));

        xShit = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yShit = ran.nextInt((int) (540));

        xEnemy1 = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yEnemy1 = ran.nextInt((int) (540));

        xEnemy2 = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
        yEnemy2 = ran.nextInt((int) (540));
        //configuring particle system and emitter
        try {
            //bubble particle image 
            Image bubbleImage = new Image("data/bubbleparticle.png", false);
            //load emitter for bubble particle right                
            systemBubbleParticleRight = new ParticleSystem(bubbleImage, 3000);
            File xmlFileEmitterBubbleParticleRight;
            xmlFileEmitterBubbleParticleRight = new File("data/bubbleParticleRight.xml");
            emitterBubbleParticleRight = ParticleIO.loadEmitter(xmlFileEmitterBubbleParticleRight);
            //load emitter for bubble particle left                
            systemBubbleParticleLeft = new ParticleSystem(bubbleImage, 3000);
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
        //set color hack collider
        collider = new Color(Color.transparent);
    }

    // render-method for all the things happening on-screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //draw background initial scene
        g.drawImage(background, xBackground, yBackground);
        g.drawImage(banana, xBanana, yBanana);
        g.drawImage(shit, xShit, yShit);
        g.drawImage(apple, xApple, yApple);
        g.drawImage(fish, xFish, yFish);
        enemyAnimation1.draw(xEnemy1, yEnemy1);
        enemyAnimation2.draw(xEnemy2, yEnemy2);
        //g.draw(enemyRect);
        //render the particle system
        systemBubbleParticleRight.render();
        systemBubbleParticleLeft.render();
        //draw diver character 
        diver.draw((int) xDiver, (int) yDiver);
        //g.draw(div);
        g.setColor(Color.yellow);
        g.drawString("Points:" + "\nHealth: ", 10, 32);
        g.setColor(new Color(6, 82, 221));
        g.drawString(Integer.toString(points), 75, 30);
        g.setColor(new Color(234, 32, 39));
        g.drawString(Integer.toString(diverHealth) + "%", 75, 52);
        g.setColor(collider); 
        g.draw(div);           
        g.draw(enemyRect1);
        g.draw(enemyRect2);
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //location settings for diver, junk and enemy polygons
        div.setLocation(xDiver, yDiver);
        banRect.setLocation(xBanana, yBanana);
        shitRect.setLocation(xShit, yShit);
        fishRect.setLocation(xFish, yFish);
        appleRect.setLocation(xApple, yApple);
        enemyRect1.setLocation(xEnemy1, yEnemy1);
        enemyRect2.setLocation(xEnemy2, yEnemy2);

        //check if diver collided with any of the objects in scene, 
        //if yes, count point and are repositioned
        if (div.intersects(banRect)) {
            xBanana = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yBanana = ran.nextInt((int) (540));
            points += 10;
            coin.play(1.0f, 0.5f);
            speedBackground += 0.009f;
            speedDiver += 0.009f;        
        }
        if (div.intersects(shitRect)) {
            xShit = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yShit = ran.nextInt((int) (540));
            points += 10;
            coin.play(1.0f, 0.5f);
            speedBackground += 0.01f;
           speedDiver += 0.009f;
        } 
        if (div.intersects(fishRect)) {
            xFish = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yFish = ran.nextInt((int) (540));
            points += 10;
            coin.play(1.0f, 0.5f);
            speedBackground += 0.01f;
            speedDiver += 0.009f;
        } 
        if (div.intersects(appleRect)) {
            xApple = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yApple = ran.nextInt((int) (540));
            points += 10;
            coin.play(1.0f, 0.5f);
            speedBackground += 0.01f;
            speedDiver += 0.009f;
        } 
        
        //check if litter has reached bottom, otherwise continue to go down and spin
        if ((xDiver - xBanana) < 600) {
            if (!(yBanana >= 536)) {
                banana.rotate(+0.01f);
                yBanana += 0.008f;
            }
        }
        if ((xDiver - xShit) < 600) {
            if (!(yShit >= 536)) {
                shit.rotate(+0.01f);
                yShit += 0.008f;
            }
        }
        if ((xDiver - xApple) < 600) {
            if (!(yApple >= 536)) {
                apple.rotate(+0.01f);
                yApple += 0.008f;
            }
        }if ((xDiver - xFish) < 600) {
            if (!(yFish >= 536)) {
                fish.rotate(+0.01f);
                yFish += 0.008f;
            }
        } 
        
        //check if objects have left the scene, if yes,
        //diver loses life and objects are repositioned
        if (xApple < -32) {
            diverHealth -= 5;
            xApple = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yApple = ran.nextInt((int) (540));
        } else if (xFish < -32) {
            diverHealth -= 5;
            xFish = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yFish = ran.nextInt((int) (540));
        } else if ((xBanana) < -32) {
            diverHealth -= 5;
            xBanana = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yBanana = ran.nextInt((int) (540));
        }else if (xShit < -32) {
            diverHealth -= 5;
            xShit = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yShit = ran.nextInt((int) (540));
        }else if (xEnemy1 < -32) {
            xEnemy1 = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yEnemy1 = ran.nextInt((int) (540));
        }else if (xEnemy2 < -32) {
            xEnemy2 = ran.nextInt((int) (xDiver + (800 - xDiver))) + 800;
            yEnemy2 = ran.nextInt((int) (540));
        }
        
        //setClearEachFrame
        gc.setClearEachFrame(true);
        //background has motion according to delta and speed background scaling
        xBackground -= delta * speedBackground;
        xBanana -= delta * speedBackground;
        xFish -= delta * speedBackground;
        xApple -= delta * speedBackground;
        xShit -= delta * speedBackground;
        xEnemy1 -= delta * speedBackground;
        xEnemy2 -= delta * speedBackground;
        /*
                    movement of the character in relation to the movement of the background                
                    When a character does not move, the buoyant force is also used (through a quadratic equation).
         */
        xDiver -= ((delta * speedBackground) / 2);
        yDiver -= Math.pow(((delta * BUOYANT_FORCE)), 2) + Math.pow(((delta * BUOYANT_FORCE)), 2);
        yBubble = yDiver;
        if (diver == diverMovementLeft) {
            xBubble = xDiver + 200;
        } else {
            xBubble = xDiver;
        }
        /*
                    If the background case reaches its end = -4985, 
                    return it to the initial position of the axis. 
                    This coordinate is in relation to 5785 
                    (background image width) - 600 (application width)
         */
        if (xBackground <= -2831) {
            xBackground = 0;
        }
        
        //****---- get user input        
        Input in = gc.getInput();
        /**
         * get user input and execute the animation need to save last user input
         * for diver animation
         */
        //user input for up
        if (in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_W)) {
            //move character in y
            yDiver -= delta * speedDiver;
            //block character in container 
            if (yDiver <= 0) {
                yDiver += delta * speedDiver;
            }
            //move bubble particle with character
            yBubble = yDiver;
        }
        //user input for down
        if (in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S)) {
            //move character in y
            yDiver += delta * speedDiver;
            //block character in container
            if (yDiver >= 520) {
                yDiver -= delta * speedDiver;
            }
            //move bubble particle with character
            yBubble = yDiver;
        }
        //user input for left
        if (in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_A)) {
            //move character in x and cancel buoyant force
            xDiver -= delta * speedDiver;
            yDiver += Math.pow(((delta * BUOYANT_FORCE)), 2) + Math.pow(((delta * BUOYANT_FORCE)), 2);
            //move bubble particle with character
            xBubble = xDiver;
            //set visible false for particle system right and true for particle system right
            systemBubbleParticleRight.setVisible(false);
            systemBubbleParticleLeft.setVisible(true);
            //load animation for character
            diver = diverMovementRight;
            //update animation speed
            diver.update(delta);
        }
        //user input for right
        if (in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D)) {
            //move character in x and cancel buoyant force 
            xDiver += delta * speedDiver;
            yDiver += Math.pow(((delta * BUOYANT_FORCE)), 2) + Math.pow(((delta * BUOYANT_FORCE)), 2);
            //block character in container
            if (xDiver >= 588) {
                xDiver -= delta * speedDiver;
            }
            //move bubble particle with character
            xBubble = xDiver + 200;
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
        if (((xDiver <= -200) || (div.intersects(enemyRect1))) || (div.intersects(enemyRect2))
                || diverHealth < 0) {
            underWater.stop();
            gameOver.play(1f, 0.3f);            
            try {
                gc.pause();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(InitialScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            gc.resume();
            sbg.enterState(3, new transition.MosaicTransitionOut(450f), new transition.MosaicTransitionIn(0.3f));
        }
        if (yDiver <= 0) {
            yDiver += delta * speedDiver;
        }
        //escape means, close the program
        if (in.isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
        //hack collider
        if (in.isKeyPressed(Input.KEY_G)){
            if (collider.getRed() == 0 && collider.getGreen() == 0 && collider.getBlue() == 0){
                collider = new Color(142, 68, 173);
            }else{
                collider = new Color(Color.transparent);
            }
        }
    }

    // Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
        return InitialScene.ID;
    }

    public int randomOnX() {
        Random generator = new Random();
        return generator.nextInt(5000);
    }

    public int randomOnY() {
        Random generator = new Random();
        return generator.nextInt(600);
    }

    public void loadEnemy() throws SlickException {
        Image[] enemyImage = {
            new Image("Assets\\InitialScene\\Enemies\\01.png"),
            new Image("Assets\\InitialScene\\Enemies\\02.png"),
            new Image("Assets\\InitialScene\\Enemies\\03.png"),
            new Image("Assets\\InitialScene\\Enemies\\04.png"),
            new Image("Assets\\InitialScene\\Enemies\\05.png"),
            new Image("Assets\\InitialScene\\Enemies\\06.png"),
            new Image("Assets\\InitialScene\\Enemies\\07.png"),
            new Image("Assets\\InitialScene\\Enemies\\08.png"),
            new Image("Assets\\InitialScene\\Enemies\\09.png"),
            new Image("Assets\\InitialScene\\Enemies\\10.png"),
            new Image("Assets\\InitialScene\\Enemies\\11.png"),
            new Image("Assets\\InitialScene\\Enemies\\12.png"),
            new Image("Assets\\InitialScene\\Enemies\\13.png"),
            new Image("Assets\\InitialScene\\Enemies\\14.png"),
            new Image("Assets\\InitialScene\\Enemies\\15.png"),
            new Image("Assets\\InitialScene\\Enemies\\16.png"),
            new Image("Assets\\InitialScene\\Enemies\\17.png"),
            new Image("Assets\\InitialScene\\Enemies\\18.png"),
            new Image("Assets\\InitialScene\\Enemies\\19.png"),
            new Image("Assets\\InitialScene\\Enemies\\20.png"),
            new Image("Assets\\InitialScene\\Enemies\\21.png"),
            new Image("Assets\\InitialScene\\Enemies\\22.png"),
            new Image("Assets\\InitialScene\\Enemies\\23.png"),
            new Image("Assets\\InitialScene\\Enemies\\24.png"),
            new Image("Assets\\InitialScene\\Enemies\\25.png"),
            new Image("Assets\\InitialScene\\Enemies\\26.png"),
            new Image("Assets\\InitialScene\\Enemies\\27.png"),
            new Image("Assets\\InitialScene\\Enemies\\28.png"),
            new Image("Assets\\InitialScene\\Enemies\\29.png"),
            new Image("Assets\\InitialScene\\Enemies\\30.png"),
            new Image("Assets\\InitialScene\\Enemies\\31.png"),};
        enemyAnimation1 = new Animation(enemyImage, ANIMATION_SPEED_ENEMY);
        enemyAnimation2 = enemyAnimation1;
        enemyRect1 = new Rectangle(xEnemy1, yEnemy1, enemyImage[0].getWidth(), enemyImage[0].getHeight());
        enemyRect2 = new Rectangle(xEnemy2, yEnemy2, enemyImage[0].getWidth(), enemyImage[0].getHeight());
    }

    /**
     * @return the underWater
     */
    public void setUnderWaterLoop() {
         underWater.loop();
    }
}
