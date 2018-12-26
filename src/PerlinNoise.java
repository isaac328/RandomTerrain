import javax.swing.JOptionPane;

import processing.core.*;

public class PerlinNoise extends PApplet 
{
	static int scl = 15;
	int cols, rows;
	float zOff = 0;
	Particle[] particles = new Particle[9000];;
	PVector[][] flowField;
	

	public static void main(String[] args) 
	{
		PApplet.main("PerlinNoise");
	}
	
	public void settings()
	{
		fullScreen(P2D);
	}
	
	public void setup()
	{
		background(0);
		colorMode(HSB, 255);
		cols = width/scl;
		rows = height/scl;
		flowField = new PVector[cols+1][rows+1];
		for(int i = 0; i < particles.length; i++)
		{
			particles[i] = new Particle(this);
		}
		for(int i = 0; i < flowField.length; i++)
		{
			for(int j = 0; j < flowField[i].length; j++)
			{
				flowField[i][j] = new PVector(0,0);
			}
		}
	}
	
	public void draw()
	{
		float yOff = 0;
		for(int y = 0; y < rows; y++)
		{
			float xOff = 0;
			for(int x = 0; x < cols; x++)
			{
				float angle = (noise(xOff, yOff, zOff) * 2*PI * 2);
				xOff+=0.1;
				PVector v = new PVector().fromAngle(angle);
				v.setMag(2);
				flowField[x][y] = v;
				//stroke(color, 50);
				pushMatrix();
				
				translate(x*scl, y*scl);
				rotate(v.heading());
				
				//line(0, 0, scl, 0);
				
				popMatrix();
			}
			yOff+=0.1;
			zOff+=0.0004;
		}
		
		for(int i = 0; i < particles.length; i++)
		{ 
			int color = (int) Math.floor(map(mouseX, 0, width, 0, 255));
			stroke(color, 255, 255, 2);
			particles[i].show();
			
			particles[i].update();
			particles[i].checkEdge();
			
			int x = (int) Math.floor(particles[i].pos.x/scl);
			int y = (int) Math.floor(particles[i].pos.y/scl);
			PVector force = flowField[x][y];
			particles[i].applyForce(force);
			
			//if(color > 255)
				//color = 0;
			//else if(frameCount % 30 == 0)
				//color+=1;
				
		}
	}
	
	public static int getScl()
	{
		return scl;
	}
	
//	public static PVector[][] getField()
//	{
//		return flowField;
//	}
}
