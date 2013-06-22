package de.futjikato.srpg;

import de.futjikato.segine.SegineException;
import de.futjikato.segine.TextureManager;
import de.futjikato.segine.map.Map;
import de.futjikato.segine.map.MapReader;
import de.futjikato.segine.rendering.DebugOption;
import de.futjikato.segine.rendering.FrameCounter;
import de.futjikato.segine.rendering.Renderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main {

    public static Input input;

    public static void main(String[] args) {

        try {
            defineLwjglLibraryPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            createWindow();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        try {
            // load map
            Image img = new Image("levels/test01.png");
            Map map = new MapReader(img).readMap();

            // init map renderer
            final Renderer renderer = new Renderer(map);
            renderer.setFrameCounter(new FrameCounter() {
                @Override
                public void update(int frames) {
                    System.out.println(String.format("FPS : %d", frames));
                }
            });
            renderer.setDebug(DebugOption.INFO);

            // init textures
            TextureManager.getInstance().setColorKey(0, 0, 0, new Image("game/ground/dev.png"));
            TextureManager.getInstance().setColorKey(255, 255, 255, new Image("game/ground/grass02.png"));

            renderer.start(new Runnable() {
                @Override
                public void run() {
                    if(Display.isCloseRequested()) {
                        renderer.end();
                    }
                }
            });
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (SegineException e) {
            e.printStackTrace();
        }

        // remove window on close
        Display.destroy();
    }

    public static void defineLwjglLibraryPath() throws Exception {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "\\libs\\lwjgl-2.9.0\\native\\windows");
        } else if (os.toLowerCase().contains("mac")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/libs/lwjgl-2.9.0/native/macosx");
        } else {
            throw new Exception("For this os we haven´t jet assigned the native libs : \"" + os + "\"");
        }
    }

    protected static void createWindow() throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(700, 700));
        Display.setTitle("SRPG - Tech demonstration");
        Display.create();

        input = new Input(Display.getHeight());
    }
}
