package mainMenu.instructions;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Instructions {

    public StackPane InfoWindow(){
        Image img = new Image("file:src/mainMenu/background.png");
        Rectangle rectangle = new Rectangle(700,700);
        rectangle.setFill(new ImagePattern(img));
        rectangle.setArcHeight(20.0d);
        rectangle.setArcWidth(20.0d);
        rectangle.setStrokeWidth(5);
        Text text = new Text("WELCOME TO SPACE BOUNCE.\n " +
                "BE READY FOR THE AMAZING ADVENTURE IN THE SPACE \n " +
                "WE HAVE 3 LEVELS *EARTH *MARS AND VENUS\n " +
                "AVOID ASTEROIDS AND ALIENS\n " +
                "COMPLETE LEVELS TO COMPLETE THE GAME \n \n " +
                "PRESS(W) -> TO JUMP \n" +
                "PRESS(A) -> TO MOVE LEFT \n" +
                "PRESS(D) -> TO MOVE RIGHT \n \n" +
        "GOOD LUCK AND HAVE FUN \n \n \n" +
                "                                         CLICK ANYWHERE TO GO BACK");
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font("Impact", FontWeight.LIGHT, 30));
        text.setFill(Color.PURPLE);
        StackPane stack = new StackPane();
        stack.setLayoutX(0);
        stack.setLayoutY(0);
        stack.getChildren().addAll(rectangle,text);
        return stack;
    }
}
