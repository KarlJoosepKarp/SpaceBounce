package bounce.lvlbuilds.lvl1build;

import bounce.lvl1data.Lvl1Data;
import bounce.lvlbuilds.lvlcircles.LvlCircle;
import bounce.lvlbuilds.lvlrectangles.LvlRectangle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Lvl1Build {
    private ArrayList<Node> platforms;
    private ArrayList<Node> badGuys;
    private LvlRectangle portal;
    private LvlCircle circle;
    private LvlRectangle rectangle;


    public Lvl1Build(ArrayList<Node> platforms, ArrayList<Node> badGuys, LvlRectangle portal, LvlCircle circle, LvlRectangle rectangle) {
        this.platforms = platforms;
        this.badGuys = badGuys;
        this.portal = portal;
        this.circle = circle;
        this.rectangle = rectangle;
    }

    public void Lvl1Build() {
        for (int j = 0; j < Lvl1Data.LEVEL1.length; j++) {
            for (int i = 0; i < Lvl1Data.LEVEL1[j].length(); i++) {
                switch (Lvl1Data.LEVEL1[j].charAt(i)) {
                    case '1':
                        Image image = new Image("file:src/bounce/pictures/earth-ground.jpg");
                        Node platform = rectangle.createRectangle(i * 30, j * 30, 60, 20, image);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node asteroid = circle.createCircle(i * 30, j * 30, 30, 10, 17, Color.YELLOW);
                        badGuys.add(asteroid);
                        break;
                    case '4':
                        Image img = new Image("file:src/bounce/pictures/portal.png");
                        Node port = portal.createRectangle(i * 30, j * 30, 40, 90, img);
                    case '0':
                        break;
                }
            }
        }
    }
}
