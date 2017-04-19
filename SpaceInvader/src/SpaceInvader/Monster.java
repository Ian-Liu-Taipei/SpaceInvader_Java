package SpaceInvader;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Monster {

	private int myX;   // x and y coordinates of center
    private int myY;
    private int myXwidth;
    private int myYwidth;
    public Graphics myBuffer;
    
    private static final ImageIcon Monster = new ImageIcon("src/SpaceInvader/monster.jpg");
   // constructors
    public Monster()     //default constructor
    {
       myX = 200;
       myY = 200;
      myXwidth=49;
      myYwidth=38;
       
       
    }
    public Monster(int x, int y)
    {
       myX = x;
       myY = y;
       myXwidth=49;
       myYwidth=38;
       
    }
  // accessor methods
    public int getX() 
    { 
       return myX;
    }
    public int getY()      
    { 
       return myY;
    }
    public int getXwidth()
    {
    	return myXwidth;
    }
    public int getYwidth()
    {
    	return myYwidth;
    }
 // modifier methods
    public void setX(int x)
    {
       myX = x;
    } 
    public void setY(int y)
    {
       myY = y;
    } 
   
  //	 instance methods
   
    public void draw(Graphics myBuffer) 
    {
       myBuffer.drawImage(Monster.getImage(),myX,myY, null);
    }

}
