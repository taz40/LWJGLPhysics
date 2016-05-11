package com.samuel.lwjgl.physics.maths;

public class vec4 {
	float x, y, z, w;
	
	public vec4(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public vec4 add(vec4 other){
		return new vec4(x+other.x, y+other.y, z+other.z, w+other.w);
	}
	
	public vec4 subtract(vec4 other){
		return new vec4(x-other.x, y-other.y, z-other.z, w-other.w);
	}
	
	public vec4 multiply(vec4 other){
		return new vec4(x*other.x, y*other.y, z*other.z, w*other.w);
	}
	
	public vec4 divide(vec4 other){
		return new vec4(x/other.x, y/other.y, z/other.z, w/other.w);
	}
	
	public boolean equals(Object other){
		
		if(other instanceof vec4){
			vec4 otherv4 = (vec4)other;
			if(x==otherv4.x && y==otherv4.y && z==otherv4.z && w==otherv4.w)
				return true;
		}
		
		return false;
		
	}
	
}
