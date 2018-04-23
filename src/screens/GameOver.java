/*
 * The MIT License
 *
 * Copyright 2018 leona.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package screens;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import main.ScubaDiving;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class GameOver extends BasicGameState{

	// ID we return to class 'Application'
	public static final int ID = 3;
        private int points;
        InitialScene initialScene;
        
        public GameOver(InitialScene _initialScene){
            initialScene = _initialScene;
        }        
        
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
                g.drawString("      GAME OVER"
                           + "\nPress escape to close"
                           + "\n  Points record: "  + initialScene.points, 300, ScubaDiving.HEIGHT/2);
                g.setColor(Color.yellow);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
                Input in = gc.getInput();
                //escape means, close the program
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}
	}                

	// Returning 'ID' from class 'SplasScreen'
	@Override
	public int getID() {
		return GameOver.ID;
	}
}

