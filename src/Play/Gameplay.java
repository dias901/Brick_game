package Play;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Gameplay extends Canvas implements Runnable, KeyListener, ActionListener{
	
	public jogador j;
	public blocos b;
	public Mundo m;
	public Inimigo in;
	public boolean play = false;
	
	public int ballposx = 336;
	public int ballposy = 304;
	public int balldirx = -1;
	public int balldiry = -1;
	
	public int score;
	public int totalblocos = 21;
	
	public static int WIDTH = 704;
	public static int HEIGHT = 608;
	
	private Timer t;
	private int delay = 1;

	public Gameplay() {
		in = new Inimigo(3, 7);
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		j = new jogador(32,32);
		m = new Mundo();
		t = new Timer(delay,this);
		t.start();
	}
	
	public void tick() {
			j.tick();
		}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH, HEIGHT);
	
		j.render(g);
		m.render(g);
		in.render((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(ballposx, ballposy, 20, 20);
		
		//scores
		g.setColor(Color.magenta);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString(""+score,660,40);
		g.setColor(Color.white);
		g.drawRect(655, 11, 40, 40);
		
		if(ballposy > 590) {
			play=false;
			balldirx = 0;
			balldiry = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Game Over, Scores: "+score,300,300);
			
		}
		
		if(totalblocos == 0 ) {
			play=false;
			balldirx = 0;
			balldiry = 0;
			
			g.setColor(Color.blue);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Parabens!! Pontos: "+score,200,300);
			
		}
		
		g.dispose();
		bs.show();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		if(play) {
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(j.x,j.y,160,8))) {
				balldiry= -balldiry;
				
			}
			for(int i = 0; i<in.map.length;i++) {
				for (int j = 0; j < in.map[0].length;j++){
					if(in.map[i][j] > 0) {
						int Bx = j* in.Blargura+80;
						int By = i* in.Baltura+50;
						int Blargura =in.Blargura;
						int Baltura =in.Baltura;
						
						Rectangle rect = new Rectangle(Bx,By,Baltura,Blargura);
						Rectangle Ballrect = new Rectangle(ballposx,ballposy,20,20);
						Rectangle brickrect = rect;
						
						if(Ballrect.intersects(brickrect)) {
							in.Bvalor(0, i, j);
							totalblocos --;
							score +=5;
							
							if(ballposx +19 <= brickrect.x || ballposx + 1 >= brickrect.x +brickrect.width) {
								balldirx = - balldirx;
							}else {
								balldiry = -balldiry;
							}
						}
						
					}
				}
			}
			
			ballposx += balldirx;
			ballposy += balldiry;
			if(ballposx < 0) {
				balldirx = -balldirx;
				
			}
			if(ballposy < 0) {
				balldiry = -balldiry;
				
			}
			if(ballposx > 695) {
				balldirx = -balldirx;
				
			}
		}
	}
	
	public static void main (String[] args) {
			JFrame jf = new JFrame();
			Gameplay gameplay = new Gameplay();
			
			jf.add(gameplay);
			jf.setTitle("Brick Game");
			jf.pack();
			jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			jf.setResizable(false);
			
			new Thread(gameplay).start();
	}
	
	@Override
	public void run() {
		while (true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			j.moveRight = true;
			play = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			j.moveLeft = true;
			play = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposx = 120;
				ballposy = 350;
				balldirx = -1;
				balldiry = -1;
				score = 0;
				j.x = 250;
				in = new Inimigo(3,7);
				m = new Mundo();
				j = new jogador(32,32);
				totalblocos = 21;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			j.moveRight = false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			j.moveLeft = false;
		}
	}

}
