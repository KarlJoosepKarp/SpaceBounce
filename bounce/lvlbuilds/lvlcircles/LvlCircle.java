package bounce.lvlbuilds.lvlcircles;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class LvlCircle extends Circle{
    private Pane gameRoot;

    public LvlCircle(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public Circle createCircle(int x, int y, int z, int h, int radius, Color color) {
        Circle circle = new Circle(z, h, radius);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
        if(color.equals(Color.BLUE)){
            Image img1 = new Image("file:src/bounce/pictures/ago.jpg");
            circle.setFill(new ImagePattern(img1));
            gameRoot.getChildren().add(circle);
            return circle;
        }
        if(color.equals(Color.RED)){
            circle.setFill(Color.RED);
            gameRoot.getChildren().add(circle);
            return circle;
        }
        if(color.equals(Color.GREEN)){
            Image img = new Image("file:src/bounce/pictures/tulnukas.png");
            circle.setFill(new ImagePattern(img));
            gameRoot.getChildren().add(circle);
            return circle;
        }
        Image image = new Image("file:src/bounce/pictures/asteroids.png");
        circle.setFill(new ImagePattern(image));
        gameRoot.getChildren().add(circle);
        return circle;
    }
}
