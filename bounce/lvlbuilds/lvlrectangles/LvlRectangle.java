package bounce.lvlbuilds.lvlrectangles;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LvlRectangle {
    private Pane gameRoot;
    private Rectangle rectangle;

    public LvlRectangle(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public Node background(int lvlNumber){
        Rectangle shape = new Rectangle(1024,600);
        if(lvlNumber == 1){
            Image image = new Image("file:src/bounce/pictures/earth.jpg");
            shape.setFill(new ImagePattern(image));
        }
        else if(lvlNumber == 2){
            Image image = new Image("file:src/bounce/pictures/mars.jpg");
            shape.setFill(new ImagePattern(image));
        }
        else if(lvlNumber == 3){
            Image image = new Image("file:src/bounce/pictures/venus.jpg");
            shape.setFill(new ImagePattern(image));
        }
        gameRoot.getChildren().add(shape);
        return shape;
    }

    public Rectangle createRectangle(int x, int y, int z, int h,Image image){
        Rectangle shape = new Rectangle(z,h);
        shape.setArcHeight(15.0d);
        shape.setArcWidth(15.0d);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        shape.setFill(new ImagePattern(image));
        gameRoot.getChildren().add(shape);
        rectangle = shape;
        return shape;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
