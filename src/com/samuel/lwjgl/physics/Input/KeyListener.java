package com.samuel.lwjgl.physics.Input;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyListener extends GLFWKeyCallback{
	
	public static boolean[] keys = new boolean[500];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		
		if(action == GLFW_RELEASE){
			keys[key] = false;
		}else if(action == GLFW_PRESS){
			keys[key] = true;
		}
		
	}
	
	public static boolean getKey(int keycode){
		return keys[keycode];
	}
	
}
