package com.samuel.lwjgl.physics.maths;

public class vec2 {
	float x, y;
	
	public vec2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public vec2 add(vec2 other){
		return new vec2(x+other.x, y+other.y);
	}
	
	public vec2 subtract(vec2 other){
		return new vec2(x-other.x, y-other.y);
	}
	
	public vec2 multiply(vec2 other){
		return new vec2(x*other.x, y*other.y);
	}
	
	public vec2 divide(vec2 other){
		return new vec2(x/other.x, y/other.y);
	}
	
	public boolean equals(Object other){
		
		if(other instanceof vec2){
			vec2 otherv2 = (vec2)other;
			if(x==otherv2.x && y==otherv2.y)
				return true;
		}
		
		return false;
		
	}
	
}
