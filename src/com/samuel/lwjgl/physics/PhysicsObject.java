package com.samuel.lwjgl.physics;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class PhysicsObject {
	
	float x, y, width, height;
	int fps, ups;
	float forceTimer = 0f;
	float mass = 10;
	float vy=0;
	float vx=0;
	long lastUpdate;
	
	public PhysicsObject(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(){
		glBegin(GL_QUADS);
     		glVertex2f(x,y);
     		glVertex2f(x+width, y);
     		glVertex2f(x+width,y+height);
     		glVertex2f(x,y+height);
     	glEnd();
	}
	
	public void update(){
		if(lastUpdate == 0){
			lastUpdate = System.nanoTime();
		}else{
			float deltaTime = ((float)System.nanoTime() - (float)(lastUpdate))/1000000000f;
			float gravityForce = -9.8f * mass;
			float netForce = gravityForce;
			if(forceTimer <= .1f){
				netForce += 700;
			}
			float a = netForce/mass;
			float newY = ((a/2)*deltaTime*deltaTime)+(vy*deltaTime)+(y/100);
			float newV = (a*deltaTime)+vy;
			y = (newY*100);
			vy = newV;
			
			netForce = 0;
			if(forceTimer >= 0.5f && forceTimer <= 0.7f){
				netForce += 500;
				
			}
			a = netForce/mass;
			float newX = ((a/2)*deltaTime*deltaTime)+(vx*deltaTime)+(x/100);
			newV = (a*deltaTime)+vx;
			x = (newX*100);
			vx = newV;
			
			if(y <= 0){
				y = 0;
			}
			//if(x >= 800-width){
				//x = 800-width;
			//}
			
			lastUpdate = System.nanoTime();
			forceTimer += deltaTime;
		}
	}
	
	
}
