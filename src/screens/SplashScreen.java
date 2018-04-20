package screens;
import main.ScubaDiving;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;


public class SplashScreen extends BasicGameState {

	// ID we return to class 'Application'
	public static final int ID = 0;
        private Image logoImg;
       
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
                logoImg = new Image("Assets\\SplashScreen\\leonardo-marcao-logo.png");
	}
        @Override
	// render-method for all the things happening on-screen        
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            //initialize logo image in splash screen  
            g.fillRect(0, 0, 800, 600);
            g.drawImage(logoImg, 200, 200);                                   
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
            try {
                //sleep 3 seconds
                Thread.sleep(3000);
                //enter state ID = 1
                sbg.enterState(1);                
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }

	// Returning 'ID' from class 'SplasScreen'
	@Override
	public int getID() {
		return SplashScreen.ID;
	}
}
