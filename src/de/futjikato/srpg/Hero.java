package de.futjikato.srpg;

import de.futjikato.segine.game.Player;
import de.futjikato.segine.rendering.Dimension;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Hero extends Player {

    private SpriteSheet playerSprite;

    public Hero() {
        super();

        try {
            Image playerSpriteBase = new Image("game/player.png");
            playerSprite = new SpriteSheet(playerSpriteBase, 50, 50, 1, 1);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getBasicDimension() {
        return new Dimension(0, 0, 0.45f, 1.2f, true, false);
    }

    @Override
    public Image getImage() {
        return playerSprite.getSprite(0, 0);
    }
}
