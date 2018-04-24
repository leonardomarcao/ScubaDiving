package screens;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import transition.*;

public class MainMenu extends BasicGameState {

	// ID we return to class 'Application'
	public static final int ID = 1;
        private Image background;
        //animation for mainMenu
        private Animation diver;
        private static final int ANIMATIONSPEED = 150;
        private Music mainMenuMusic;
        private Sound startGame;
        private InitialScene initialScene;
        
        public MainMenu(InitialScene _initialScene){
            initialScene=_initialScene;
        }
            
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {                 
            //background of main menu
            background = new Image("\\Assets\\MainMenu\\BackgroundMainMenu\\backgroundMainMenu.png");
            //music of main menu
            mainMenuMusic = new Music("\\Assets\\MainMenu\\MainMenuMusic.ogg");
            mainMenuMusic.setVolume(0.5f);
            mainMenuMusic.loop();
            //sound for start game (initial scene)
            startGame = new Sound("\\Assets\\MainMenu\\StartGame.ogg");
            
            /*
                set the image arrays needed for the animation of diver
            */
            Image[] iddle = {
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\00.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\01.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\02.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\03.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\04.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\05.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\06.png"),
              new Image("Assets\\InitialScene\\SpriteScubaCharacter\\07.png"),
            };  
            //diver animation for state iddle
            diver = new Animation(iddle, ANIMATIONSPEED);            
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
                //load main menu background 
		g.drawImage(background, 0, 0);   
                //load main menu animation 
                diver.draw(20, 180);                
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
                //wait for key press of user and enter state ID = 2
                Input in = gc.getInput();
		if (in.isKeyPressed(Input.KEY_ENTER)){
                    startGame.play(1.0f, 0.3f);
                    sbg.enterState(2, new MosaicTransitionOut(450f), new MosaicTransitionIn(0.3f));             
                    initialScene.setUnderWaterLoop();                    
                }  
                //escape means, close the program
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}                   
	}

	// Returning 'ID' from class 'SplasScreen'
	@Override
	public int getID() {
		return MainMenu.ID;
	}

    /**
     * @return the mainMenuMusic
     */
    public Music getMainMenuMusic() {
        return mainMenuMusic;
    }
}
