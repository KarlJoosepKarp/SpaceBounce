package bounce.lvlbuilds.lvl3build;

import bounce.lvl2data.Lvl2Data;
import bounce.lvl3data.Lvl3Data;
import bounce.lvlbuilds.lvlcircles.LvlCircle;
import bounce.lvlbuilds.lvlrectangles.LvlRectangle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Lvl3Build {
    private ArrayList<Node> platforms;
    private ArrayList<Node> badGuys;
    private Rectangle portal;
    private LvlCircle circle;
    private LvlRectangle rectangle;


    public Lvl3Build(ArrayList<Node> platforms, ArrayList<Node> badGuys, LvlCircle circle, LvlRectangle rectangle) {
        this.platforms = platforms;
        this.badGuys = badGuys;
        this.portal = portal;
        this.circle = circle;
        this.rectangle = rectangle;
    }

    public void build() {
        for (int j = 0; j < Lvl3Data.LEVEL3.length; j++) {
            for (int i = 0; i < Lvl3Data.LEVEL3[j].length(); i++) {
                switch (Lvl3Data.LEVEL3[j].charAt(i)) {
                    case '1':
                        Image image = new Image("file:src/bounce/pictures/venus_ground.png");
                        Node platform = rectangle.createRectangle(i * 30, j * 30, 60, 20,image);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node asteroid = circle.createCircle(i * 30, j * 30, 30, 10, 17, Color.YELLOW);
                        badGuys.add(asteroid);
                        break;
                    case'3':
                        Node alien = circle.createCircle(i * 30, j * 30, 30, 10, 25, Color.GREEN);
                        badGuys.add(alien);
                        break;
                    case '0':
                        break;
                }
            }
        }
    }
}
