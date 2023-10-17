package Play;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Inimigo extends Rectangle{
	
	public int map [][];
	public int Blargura;
	public int Baltura;
	
	public Inimigo (int linha, int coluna){
		map = new int[linha][coluna];
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				map[i][j]= 1;
			}
			
		}
		Blargura = 540/coluna;
		Baltura = 150/linha;
	}

	public void render (Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.green);
					g.fillRect(j*Blargura+80, i*Baltura+50, Blargura, Baltura);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*Blargura+80, i*Baltura+50, Blargura, Baltura);
					}
				}
			}
		}
	
	public void Bvalor(int valor,int linha, int coluna) {
		map[linha][coluna] = valor;
	}
}