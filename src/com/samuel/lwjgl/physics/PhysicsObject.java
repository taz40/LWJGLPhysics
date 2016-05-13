package com.samuel.lwjgl.physics;
import static org.lwjgl.opengl.GL11.*;

public class PhysicsObject {
	
	float x, y, width, height;
	int fps, ups;
	float forceTimer = 0f;
	float mass = 10;
	float vy=0;
	float vx=0;
	long lastUpdate;
	float timeModifier = 1;
	float ForceY = -9.8f*mass;
	float ForceX = 0;
	
	public PhysicsObject(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(){
		glColor3f(1.0f,0f, 0f);
		glBegin(GL_QUADS);
     		glVertex2f(x,y);
     		glVertex2f(x+width, y);
     		glVertex2f(x+width,y+height);
     		glVertex2f(x,y+height);
     	glEnd();
	}
	
	public void addForce(float amount, float angle){
		float angleRaid = (float) Math.toRadians(angle);
		ForceX += amount*-Math.cos(angleRaid);
		ForceY += amount*-Math.sin(angleRaid);
	}
	
	public void update(){
		if(lastUpdate == 0){
			lastUpdate = System.nanoTime();
		}else{
			float deltaTime = ((long)System.nanoTime() - (lastUpdate))/1000000000f;
			deltaTime *= timeModifier;
			ForceY -= 9.8f*mass;
			if(forceTimer <= 0.1f){
				addForce(700, 270);
			}
			float a = ForceY/mass;
			float newY = ((a/2)*deltaTime*deltaTime)+(vy*deltaTime)+(y/100);
			float newV = (a*deltaTime)+vy;
			y = (newY*100);
			vy = newV;
			ForceY = 0;
			if(forceTimer >= 0.5f && forceTimer <= 0.7f){
				addForce(500, 180);
				
			}
			a = ForceX/mass;
			float newX = ((a/2)*deltaTime*deltaTime)+(vx*deltaTime)+(x/100);
			newV = (a*deltaTime)+vx;
			x = (newX*100);
			vx = newV;
			ForceX = 0;
			
			if(y <= 0){
				y = 0;
				vy = 0;
			}
			if(x >= 800-width){
				x = 800-width;
				vx = 0;
			}
			
			lastUpdate = System.nanoTime();
			forceTimer += deltaTime;
		}
	}
	
	
}
