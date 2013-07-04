package de.futjikato.srpg;

import de.futjikato.segine.SegineException;
import de.futjikato.segine.TextureManager;
import de.futjikato.segine.game.Player;
import de.futjikato.segine.game.camera.CenterCamera;
import de.futjikato.segine.game.camera.StaticCamera;
import de.futjikato.segine.map.Map;
import de.futjikato.segine.rendering.FrameCounter;
import de.futjikato.segine.rendering.Renderer;
import de.futjikato.segine.rendering.Viewport;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

public class Main {

    public static void main(String[] args) {

        try {
            defineLwjglLibraryPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // initialize viewport
            Viewport vp = new Viewport(1, 0, 0, 10, 10, 800, 600);
            vp.createWindow("SRPG - Segine Tech Demo");

            // initialize renderer
            Renderer renderer = new Renderer(vp);

            // create player
            Player player = new Hero();
            renderer.addRenderContainer(player);
            renderer.addObserver(player);

            // create camera
            CenterCamera camera = new CenterCamera(vp, player);
            renderer.setCamera(camera);

            // add framecounter
            renderer.setFrameCounter(new FrameCounter() {
                @Override
                public void update(int frames) {
                    System.out.println(frames);
                }
            });

            TextureManager.getInstance().setColorKey(0, 0, 0, "game/ground/dev.png");
            TextureManager.getInstance().setColorKey(255, 255, 255, "game/ground/grass02.png");

            // create map object and add to renderer
            Map map = new Map("assets\\levels\\test01.png");
            renderer.addRenderContainer(map);

            renderer.start();
        } catch (SegineException e) {
            e.printStackTrace();
        }
    }

    public static void defineLwjglLibraryPath() throws Exception {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "\\..\\segine\\libs\\lwjgl-2.9.0\\native\\windows");
        } else if (os.toLowerCase().contains("mac")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/../segine/libs/lwjgl-2.9.0/native/macosx");
        } else {
            throw new Exception("For this os we haven´t jet assigned the native libs : \"" + os + "\"");
        }
    }
}
