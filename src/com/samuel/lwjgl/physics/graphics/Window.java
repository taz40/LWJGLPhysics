package com.samuel.lwjgl.physics.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.samuel.lwjgl.physics.Input.KeyListener;
 

public class Window {
	
    // The window handle
    private long window;
    int width, height;
    String title;
    private GLFWKeyCallback   keyCallback = new KeyListener();
    public int fps;
    public int ups;
    int frames;
    int updates;
    long timer = 0;
    long lasttime = 0;
    
    public Window(int width, int height, String title){
    	this.width = width;
    	this.height = height;
    	this.title = title;
    	
    	// Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback);
 
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - width) / 2,
            (vidmode.height() - height) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
        
        GL.createCapabilities();
    	lasttime = System.nanoTime();
    	
    }
    
    public void setupForThread(){
    	glfwMakeContextCurrent(window);
    }
    
    public void vSync(int vSync){
    	glfwSwapInterval(vSync);
    }
    
    public void Destroy(){
    	glfwDestroyWindow(window);
        keyCallback.release();
    }
    
    public long getWindowID(){
    	return window;
    }
    
    public void clear(){
    	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }
    
    public void render(){
    	frames++;
        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
	
    public void update(){
    	long now = System.nanoTime();
    	timer += now - lasttime;
    	lasttime = now;
    	updates++;
    	if(timer >= 1e+9){
    		timer = 0;
    		fps = frames;
    		frames = 0;
    		ups = updates;
    		updates = 0;
    	}
    }
}
