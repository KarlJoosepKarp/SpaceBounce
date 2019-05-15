package bounce.lvlbuilds.healts;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Health {
    private Pane gameRoot;

    public Health(Pane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void createHealth() {
        Circle health1 = new Circle(15, 15, 15);
        health1.setTranslateX(925);
        health1.setTranslateY(20);
        health1.setFill(Color.RED);
        Circle health2 = new Circle(15, 15, 15);
        health2.setTranslateX(958);
        health2.setTranslateY(20);
        health2.setFill(Color.RED);
        Circle health3 = new Circle(15, 15, 15);
        health3.setTranslateX(991);
        health3.setTranslateY(20);
        health3.setFill(Color.RED);
        gameRoot.getChildren().addAll(health1,health2,health3);
    }
}
