package main;
import screens.MainMenu;
import screens.GameOver;
import screens.InitialScene;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class ScubaDiving extends StateBasedGame{
         // Game state identifiers
        public static final int SPLASHSCREEN  = 0;
        public static final int MAINMENU      = 1;
        public static final int INITIAL_SCENE = 2;
        public static final int GAMEOVER = 3;
        
        // Application Properties
        public static final int WIDTH   = 800;
        public static final int HEIGHT  = 600;
        public static final double VERSION = 1.0;

        //constructor
	public ScubaDiving(String gamename)
	{
            //calling super with parameter string gamename
	     super(gamename);
	}

        // Initialize game states (calls init method of each gamestate, and set's the state ID)
        @Override
        public void initStatesList(GameContainer gc) throws SlickException {
            /* The first state added will be the one that is loaded first, 
            when the application is launched. After, other states are add.*/
            
            //state for splash screen
            //this.addState(new SplashScreen());
            //state for mainmenu           
            this.addState(new MainMenu());
            //state for initial scene
            this.addState(new InitialScene());
            //state for game over
            this.addState(new GameOver());
        }   

        //main
	public static void main(String[] args)
	{
		try
		{
                        //AppGameContainer for game stand-alone application
			AppGameContainer appgc;
                        /*calling super as parameter of AppGameContainer and 
                        pass string gamename}*/
			appgc = new AppGameContainer(new ScubaDiving("Scuba Diving V" + VERSION));
                        //setDisplayMode with applications properties 
			appgc.setDisplayMode(WIDTH, HEIGHT, false);                        
                        //always render the application, even if we don't focus it                        
                        appgc.setAlwaysRender(true);                        
                        //starting game application
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(ScubaDiving.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
