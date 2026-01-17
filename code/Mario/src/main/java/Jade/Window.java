package Jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window
{
    private int width;
    private int height;
    String title;

    private long glfwWindow;

    private static Window window= null;

    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title ="Mario";
    }
    public static Window get(){
        if (Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }
    public void run(){
        System.out.println("Hello LWJGL" + Version.getVersion()+ "!");
        init();
        loop();
    }
    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width,this.height,this.title,NULL, NULL);
            if(glfwWindow == NULL){
                throw new IllegalStateException("Failed to create the GLFW window");
    }
            glfwMakeContextCurrent(glfwWindow);
            glfwSwapInterval(1);

            glfwShowWindow(glfwWindow);
        GL.createCapabilities();
    }
    public void loop(){
    while (!glfwWindowShouldClose(glfwWindow)){
        glfwPollEvents();
        glClearColor(1.0f,0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glfwSwapBuffers(glfwWindow);
    }
    }
}
