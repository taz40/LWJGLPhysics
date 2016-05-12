package com.samuel.lwjgl.physics.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyListener extends GLFWKeyCallback{

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
	    if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
	    	glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
	}
	
}
