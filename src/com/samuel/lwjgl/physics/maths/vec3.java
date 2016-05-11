package com.samuel.lwjgl.physics.maths;

public class vec3 {
	float x, y, z;
	
	public vec3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public vec3 add(vec3 other){
		return new vec3(x+other.x, y+other.y, z+other.z);
	}
	
	public vec3 subtract(vec3 other){
		return new vec3(x-other.x, y-other.y, z-other.z);
	}
	
	public vec3 multiply(vec3 other){
		return new vec3(x*other.x, y*other.y, z*other.z);
	}
	
	public vec3 divide(vec3 other){
		return new vec3(x/other.x, y/other.y, z/other.z);
	}
	
	public boolean equals(Object other){
		
		if(other instanceof vec3){
			vec3 otherv3 = (vec3)other;
			if(x==otherv3.x && y==otherv3.y && z==otherv3.z)
				return true;
		}
		
		return false;
		
	}
	
}
