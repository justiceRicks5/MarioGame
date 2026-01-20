package Jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollx;
    private double scrolly;
    private double xPos, yPos, lastY, lastX;
    private boolean isDragging;
    private boolean mouseButtonPressed[] = new boolean[3];

    private MouseListener() {
        this.scrollx = 0.0;
        this.scrolly = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get() {
        if (instance == null) {
            instance = new MouseListener();
        }
        return instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }

    }

    public static void mouseScrollCallBack(long window, double xOffset, double yOffset) {
        get().scrollx = xOffset;
        get().scrolly = yOffset;

    }

    public static void endFrame() {
        get().scrollx = 0;
        get().scrolly = 0;
        get().lastY = get().yPos;
        get().lastX = get().xPos;
    }

    public static float getx() {
        return (float) get().xPos;
    }

    public static float gety() {
        return (float) get().yPos;
    }

    public static float getDx() {
        return (float) (get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float) (get().scrollx);
    }

    public static float getScrollY() {
        return (float) (get().scrolly);
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if ((button < get().mouseButtonPressed.length)) {

            return get().mouseButtonPressed[button];
        } else {
            return false;

        }
    }
}
