package SpaceInvader;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import SpaceInvader.GamePanel.KEY;

public class GamePanel extends JPanel {

	private BufferedImage myImage;
	private Graphics myBuffer;
	private Timer t1, t2, t3,t4;
	private int playerX = 0;

	private int monsterY = 210;
	private int ap=0;
	private int ap1=0;
	private int icX=9;
	private int icY=4;
	private int count=0;
	private int lives=10;
	private int moveX=0;
	private int moveY=24;
	private int RandomX=(int)(Math.random()*9);
	private int RandomY=(int)(Math.random()*4);
	private Monster[][] ic;
	
	private int x;
	
	private int yPos = -50;
	
	
	private int shooting = 0;
	public boolean spacepress=false;
	public boolean escpress=false;

	private static final ImageIcon Player = new ImageIcon("src/SpaceInvader/player.png");
	private static final ImageIcon Start = new ImageIcon("src/SpaceInvader/Space+Invaders.jpg");
	private static final ImageIcon gameOver = new ImageIcon("src/SpaceInvader/gameover.jpg");
	public GamePanel() {

		myImage = new BufferedImage(700, 500, BufferedImage.TYPE_INT_RGB);
		myBuffer = (Graphics2D) myImage.getGraphics();
		t1= new Timer(20, new Listener1());
		 t2= new Timer(15,new Listener2());
		 t3= new Timer(20,new Listener3());
		 
		 
	
		ic= new Monster[icX][icY];
		for(int i=0;i<icX;i++)
		{
			for(int j=0;j<icY;j++)
			{
				ic[i][j] =new Monster(55*i+moveX,50*j+moveY);
				
			}	
				
		}
		
		addKeyListener(new KEY());
		setFocusable(true);
		t1.start();

	}

	public void paintComponent(Graphics g) {
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);

	}

	private class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SoundBase sb1 =new SoundBase("src/SpaceInvader/1234.wav");
			sb1.play();
			myBuffer.setColor(Color.BLACK);
			myBuffer.fillRect(0, 0, 700, 700);
			spacepress = false;
			myBuffer.drawImage(Player.getImage(), playerX, 430, 70, 64, null);
			myBuffer.setColor(Color.WHITE);
			monsterAttack();
			if(ap>=60)
			{
				ap1=1;
			}
			if(ap==0)
			{
				ap1=0;
			}
			if(ap<=60 && ap1==0)
			{
				ap=ap+1;
			for(int i=0;i<icX;i++)
			{
				
				for(int j=0;j<icY;j++)
				{
				
						ic[i][j].setX(ic[i][j].getX()+3);
					    
				}
			}
			
			}
			if(ap>0 && ap1==1)
			{
				ap=ap-1;
			for(int i=0;i<icX;i++)
			{
				
				for(int j=0;j<icY;j++)
				{
				
						ic[i][j].setX(ic[i][j].getX()-3);
					    
				}
			}
			
			}
			

			for(int i=0;i<icX;i++)
			{
				for(int j=0;j<icY;j++)
				{
					
					ic[i][j].draw(myBuffer);
					
					if(x+35>ic[i][j].getX() && x+35<ic[i][j].getX()+ic[i][j].getXwidth() && yPos<ic[i][j].getY()+ic[i][j].getYwidth() && yPos>ic[i][j].getY() )
					{
						ic[i][j].setX(-100);
						ic[i][j].setY(-100);
						x=-100;
						yPos=-100;
						count=count+1;
					}
				}
			}
			
			myBuffer.setColor(Color.WHITE);
			myBuffer.setFont(new Font("Press Start 2P", Font.PLAIN, 15));
			myBuffer.drawString("SCORE:", 50, 500);
			myBuffer.drawString(""+count, 175, 500);
			myBuffer.drawString("LIVES:", 400, 500);
			myBuffer.drawString(""+lives, 500, 500);
			if (shooting == 1 && yPos >= -50) {
				myBuffer.setColor(Color.WHITE);
				myBuffer.fillRect(x + 36, yPos, 5, 20);
				yPos = yPos - 5;
				
				
			}
			if (yPos < -40) {
				shooting = 0;
			}
			if (shooting == 0) {
				x = playerX;
			}
			
			myBuffer.setColor(Color.GREEN);
			myBuffer.drawLine(0, 480, 800, 480);
			repaint();
			// myBuffer.setColor(Color.BLACK);
			// myBuffer.fillRect(0, 0, 700, 700);
			// myBuffer.setColor(Color.YELLOW);
			// myBuffer.setFont(new Font("Monospaced", Font.BOLD, 60));
			// myBuffer.setColor(Color.BLUE);
			// myBuffer.fillRect(100, 250, 510, 62);
			// myBuffer.setColor(Color.YELLOW);
			// myBuffer.drawString("Press Enter to Play!" , 100, 300);
			if(lives==0 || count==36)
			{
			sb1.stop();
			t2.stop();
			 t3.start();
			 
			}
			
		}

	}

	 private class Listener1 implements ActionListener
	 {
	
	 @Override
	 public void actionPerformed(ActionEvent arg0)
	 {
		 
	 myBuffer.setColor(Color.BLACK);
	 myBuffer.fillRect(0, 0, 700, 700);
	 myBuffer.drawImage(Start.getImage(), 30, 100, 630, 400, null);
	 
	 repaint();
	if(spacepress)
	{
	 t1.stop();
	 t2.start();
	}
	
	 }
	
	 }

	public class KEY extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				playerX = playerX - 5;

			if (e.getKeyCode() == KeyEvent.VK_D)
				playerX = playerX + 5;

			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				spacepress = true;
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				escpress=true;
			if (e.getKeyCode() == KeyEvent.VK_SPACE && shooting == 0) {

				yPos = 400;
				shooting = 1;
				
			}
		}

	}
	public void monsterAttack()
	{
		myBuffer.setColor(Color.WHITE);
		if(ic[RandomX][RandomY].getX()>0 && ic[RandomX][RandomY].getY()>0)
		{	
			myBuffer.fillRect(ic[RandomX][RandomY].getX()+20,ic[RandomX][RandomY].getY()+monsterY, 5, 20);
			
			
		}
		else
		{
			RandomX=(int)(Math.random()*9);
			RandomY=(int)(Math.random()*4);
		}
	    monsterY=monsterY+5;
		if(ic[RandomX][RandomY].getX()>playerX && ic[RandomX][RandomY].getX( )<playerX+64 && monsterY+20>450 && monsterY+20<510)
		{
			lives=lives-1;
			RandomX=(int)(Math.random()*9);
			RandomY=(int)(Math.random()*4);
			monsterY=0;
		}
		if(monsterY>700)
		{
			RandomX=(int)(Math.random()*9);
			RandomY=(int)(Math.random()*4);
			monsterY=0;
			
		}
		
	}
	
	

	

	 private class Listener3 implements ActionListener
	 {
	
	 @Override
	 public void actionPerformed(ActionEvent arg0) {
		 SoundBase sb2 =new SoundBase("src/SpaceInvader/Final_Fantasy_IV_-_Victory_Music_(8_bit_Version).wav");
		 sb2.play();
		 myBuffer.setColor(Color.BLACK);
		 myBuffer.fillRect(0, 0, 700, 700);
		 myBuffer.drawImage(gameOver.getImage(),65, 0, 550, 550, null);
		 myBuffer.setColor(Color.WHITE);
		 myBuffer.setFont(new Font("Press Start 2P", Font.PLAIN, 15));
		 myBuffer.drawString("YOUR SCORE:"+count, 250, 400);
		 
	 repaint();
	 if(escpress)
	 {
		 sb2.stop();
		 t3.stop();
		 System.exit(WHEN_IN_FOCUSED_WINDOW);
	 }
	 }
	
	 }
	 
	
	
	 

}
