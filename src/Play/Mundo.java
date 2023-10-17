package Play;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Play.blocos;

public class Mundo {
	
	public static List<blocos> blocos = new ArrayList<blocos>();
	
	public Mundo() {
		//borda de cima
		for (int a = 0; a < 70 ; a ++) {
			blocos.add(new blocos(a*10,0));
		}
		//borda esquerda
		for (int b = 0; b < 60 ; b ++) {
			blocos.add(new blocos(0,b*10));
		}
		//borda baixo
		for (int c = 0; c < 70 ; c ++) {
			blocos.add(new blocos(c*10,Gameplay.HEIGHT -10));
		}
		//borda direita
		for (int d = 0; d < 61 ; d ++) {
			blocos.add(new blocos(Gameplay.WIDTH -10,d*10));
		}
	}
	
	public static boolean isfree(int x) {
		if (x >= 540 || x <= 6) {
				return false;
			}
		return true;
	}
	public void render(Graphics g) {
		for(int i = 0; i < blocos.size(); i++ ) {
			blocos.get(i).render(g);
		}
	}
}