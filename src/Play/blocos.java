package Play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class blocos extends Rectangle {

	public blocos(int x, int y) {
		super(x,y,10,10);
	}
	
	public void render (Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
}
