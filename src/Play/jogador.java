package Play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Play.Mundo;

public class jogador extends Rectangle{

	public int spd = 9;
	public boolean moveLeft, moveRight;
	
	public jogador(int x, int y) {
		super(250,608-20,160,8);
	}
	
	public void tick() {
		if(moveRight && Mundo.isfree(x+spd)) {
			x+=spd;
		}else if(moveLeft && Mundo.isfree(x-spd)){
			x-=spd;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
