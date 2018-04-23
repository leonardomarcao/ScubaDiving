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
package transition;

/**
 *
 * @author leona
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

public class MosaicTransitionIn implements Transition
{
   private float Speed;      // Speed of the transition
   private float Multiplier; // Stage of the transition
   private boolean Complete; // Is transition comlpete
   
   // Create a new mosaic transition
   // Size - Initial size of the multiplier
   public MosaicTransitionIn (float Size)
   {
      this (450f, Size);
   }
   
   // Create a new mosaic transition
   // Speed - The time it takes for the transition to finish
   // Size - Initial size of the multiplier
   public MosaicTransitionIn (float Speed, float Size)
   {
      Multiplier = Size;
      Complete = false;
      this.Speed = (float) 500 / Speed;
   }
   
   // Check if this transtion has been completed
   // Return - True if the transition has been completed
   public boolean isComplete()
   {
      return Complete;
   }
   
   // Render the transition over the existing state rendering
   // game - The game this transition is being rendered as part of
   // container - The container holding the game
   // g - The graphics context to use when rendering the transiton
   // Throws - SlickException: Indicates a failure occured during the render 
   public void postRender (StateBasedGame game, GameContainer container, Graphics g)
   {
      for (int y = 0; y <= container.getHeight(); y += Multiplier)
         for (int x = 0; x <= container.getWidth(); x += Multiplier)
         {
            g.setColor (g.getPixel (x + 1, y + 1));
            g.fillRect (x, y, Multiplier, Multiplier);
         }
   }
   
   // Update the transition. Cause what ever happens in the transition to happen
   // game - The game this transition is being rendered as part of
   // container - The container holding the game
   // delta - The amount of time passed since last update
   // Throws - SlickException: Indicates a failure occured during the update 
   public void update (StateBasedGame game, GameContainer container, int delta)
   {
      if (Multiplier > 25)
         Multiplier /= Speed;
      
      else Complete = true;
   }
   
   // Render the transition before the existing state rendering
   // game - The game this transition is being rendered as part of
   // container - The container holding the game
   // delta - The amount of time passed since last update
   // Throws - SlickException: Indicates a failure occured during the update 
   public void preRender (StateBasedGame game, GameContainer container, Graphics g)
   {
   }
   
   // Initialise the transition
   // firstState - The first state we're rendering (this will be rendered by the framework)
   // secondState - The second stat we're transitioning to or from (this one won't be rendered)
   public void init (GameState firstState, GameState secondState)
   {
   }
}