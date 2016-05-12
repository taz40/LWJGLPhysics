package com.samuel.lwjgl.physics;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.samuel.lwjgl.physics.Input.KeyListener;
import com.samuel.lwjgl.physics.graphics.Window;
 
public class Main implements Runnable{
 
    // We need to strongly reference callback instances.
    PhysicsObject o = new PhysicsObject(100,100,10,10);
    
    int ups = 60;
    long updateTimer = 0;
    long updateCooldown = (long) (1e+9/ups);
    long lastTime = 0;
    
    Window window;
    public Thread updateThread;
 
    public void start() {
        
        try {
            init();
            loop();
 
            // Release window and window callbacks
            window.Destroy();
            try {
				updateThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
            // Terminate GLFW and release the GLFWerrorfun
            glfwTerminate();
        }
    }
 
    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
        
        window = new Window(800,600,"LWJGL Game");
        
        window.vSync(1);
        
    	System.out.println("GL_VENDOR: " + glGetString(GL_VENDOR));
		System.out.println("GL_RENDERER: " + glGetString(GL_RENDERER));
		System.out.println("GL_VERSION: " + glGetString(GL_VERSION));
		
        updateThread = new Thread(this);
        updateThread.start();
        
    }
 
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities(); // valid for latest build
        //GLContext.createFromCurrent(); // use this line instead with the 3.0.0a build
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,800,0,600,1,-1);
        glMatrixMode(GL_MODELVIEW);
        
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
 
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        
        while ( glfwWindowShouldClose(window.getWindowID()) == GL_FALSE ) {
        	
        	render();
            
        }
        
    }
    
    public void run(){
    	lastTime = System.nanoTime();
    	while ( glfwWindowShouldClose(window.getWindowID()) == GL_FALSE ) {
	    	if(updateTimer <= 0){
	    		update();
	    		updateTimer = updateCooldown;
	    		//lastTime = System.nanoTime();
	    	}else{
	    		long now = System.nanoTime();
	    		updateTimer -= (now - lastTime);
	    		lastTime = now;
	    		if(updateTimer > 0){
		    		try {
						Thread.sleep((long) (updateTimer*1e-6));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
    	}
    }
    
    public void update(){
    	window.update();
    	
	    if ( KeyListener.getKey(GLFW_KEY_ESCAPE) )
	    	glfwSetWindowShouldClose(window.getWindowID(), GL_TRUE); // We will detect this in our rendering loop
	    
    	o.update();
    }
    
    public void render(){
    	window.clear();
        
        o.render();
        
        window.render();
    }
 
    public static void main(String[] args) {
        new Main().start();
    }
 
}