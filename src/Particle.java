import processing.core.*;

public class Particle 
{
	PApplet p;
	PVector pos;
	PVector vel;
	PVector acc;
	PVector prevPos;
	float maxSpeed = 0.1f;
	
	public Particle(PApplet p)
	{
		this.p = p;
		pos = new PVector(p.random(0,p.width),p.random(0,p.height));
		vel = new PVector(0,0);
		acc = new PVector(0,0);
		prevPos = new PVector(pos.x, pos.y);
		
	}
	
	public void update()
	{
		vel.add(acc);
		pos.add(vel);
		vel.limit(maxSpeed);
		acc.mult(0);
	}
	
	public void show()
	{
		p.fill(0);
		p.line(pos.x, pos.y, prevPos.x, prevPos.y);
		updatePrevious();
	}
	
	public void applyForce(PVector force)
	{
		acc.add(force);
	}
	
	public void checkEdge()
	{
		if(pos.x > p.width)
		{
			pos.x = 0;
			updatePrevious();
		}
		
		if(pos.x < 0)
		{
			pos.x = p.width;
			updatePrevious();
		}
			
		if(pos.y > p.height)
		{
			pos.y = 0;
			updatePrevious();
		}
			
		if (pos.y < 0)
		{
			pos.y = p.height;
			updatePrevious();
		}
	}
	
	public void updatePrevious()
	{
		prevPos.x = pos.x;
		prevPos.y = pos.y;
	}
}
