package Main;

import LVL2Data.Lvl2Data;
import LVL1Data.Lvl1Data;
import LVL3Data.Lvl3Data;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<Node> platforms = new ArrayList<>();
    private ArrayList<Node> asteroids = new ArrayList<>();

    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private boolean canJump = true;
    private Node player;
    private Point2D playerPosistsion = new Point2D(0,0);
    private int levelWidth1 = Lvl2Data.LEVEL2[0].length()*60;


    private void gameBuild(int lvlNumber){
        Node background = this.background(lvlNumber);
        System.out.println("ez");
        if(lvlNumber == 1){
            this.Lvl1Build();
        }
        else if(lvlNumber == 2){
            this.Lvl2Build();
        }
        else if(lvlNumber == 3){
            this.Lvl3Build();
        }
        player = createCircle(50,300,20,20,13, Color.RED);
        player.translateXProperty().addListener((obs, old, newValue)->{
                    int offset = newValue.intValue();

                    if(offset > 640 && offset < levelWidth1 -640){
                        gameRoot.setLayoutX(-(offset-640));
                    }
                }
        );
        appRoot.getChildren().addAll(background, gameRoot, uiRoot);
    }


    private void Lvl2Build() {
        for (int j = 0; j < Lvl2Data.LEVEL2.length; j++) {
            for (int i = 0; i < Lvl2Data.LEVEL2[j].length(); i++) {
                switch (Lvl2Data.LEVEL2[j].charAt(i)) {
                    case '1':
                        Image img = new Image("file:src/CSS/Pictures/step.png");
                        Node platform = createRectangle(i * 30, j * 30, 60, 20,img);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node asteroid = createCircle(i * 30, j * 30, 30, 10, 17, Color.YELLOW);
                        asteroids.add(asteroid);
                        break;
                    case '0':
                        break;
                }
            }
        }
    }

    private void Lvl1Build() {
        for (int j = 0; j < Lvl1Data.LEVEL1.length; j++) {
            for (int i = 0; i < Lvl1Data.LEVEL1[j].length(); i++) {
                switch (Lvl1Data.LEVEL1[j].charAt(i)) {
                    case '1':
                        Image image = new Image("file:src/CSS/Pictures/earth_ground.jpg");
                        Node platform = createRectangle(i * 30, j * 30, 60, 20, image);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node asteroid = createCircle(i * 30, j * 30, 30, 10, 17, Color.YELLOW);
                        asteroids.add(asteroid);
                        break;
                    case '0':
                        break;
                }
            }
        }
    }
    private void Lvl3Build() {
        for (int j = 0; j < Lvl3Data.LEVEL3.length; j++) {
            for (int i = 0; i < Lvl3Data.LEVEL3[j].length(); i++) {
                switch (Lvl3Data.LEVEL3[j].charAt(i)) {
                    case '1':
                        Image image = new Image("file:src/CSS/Pictures/venus_ground.png");
                        Node platform = createRectangle(i * 30, j * 30, 60, 20,image);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node asteroid = createCircle(i * 30, j * 30, 30, 10, 17, Color.YELLOW);
                        asteroids.add(asteroid);
                        break;
                    case '0':
                        break;
                }
            }
        }
    }

    private Node background(int lvlNumber){
        Rectangle shape = new Rectangle(1280,720);
        if(lvlNumber == 1){
            Image image = new Image("file:src/CSS/Pictures/earth.jpg");
            shape.setFill(new ImagePattern(image));
        }
        else if(lvlNumber == 2){
            Image image = new Image("file:src/CSS/Pictures/mars.jpg");
            shape.setFill(new ImagePattern(image));
        }
        else if(lvlNumber == 3){
            Image image = new Image("file:src/CSS/Pictures/venus.jpg");
            shape.setFill(new ImagePattern(image));
        }
        gameRoot.getChildren().add(shape);
        return shape;
    }

    private Node createRectangle(int x, int y, int z, int h,Image image){
        Rectangle shape = new Rectangle(z,h);
        shape.setArcHeight(15.0d);
        shape.setArcWidth(15.0d);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        shape.setFill(new ImagePattern(image));
        gameRoot.getChildren().add(shape);
        return shape;
    }

    private Node createCircle(int x, int y, int z, int h,int radius, Color color) {
        Circle circle = new Circle(z, h, radius);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
        if(color.equals(Color.RED)){
            circle.setFill(Color.RED);
            gameRoot.getChildren().add(circle);
            return circle;
        }
        Image image = new Image("file:src/CSS/Pictures/asteroids.png");
        circle.setFill(new ImagePattern(image));
        gameRoot.getChildren().add(circle);
        return circle;
        }


    private void gameloop(){
        if(isPressed(KeyCode.W) && player.getTranslateY() >= 5){
            jumpPlayer();
        }
        if(isPressed(KeyCode.A) && player.getTranslateX() >= 5){
            moveX(-5);
        }
        if(isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth1 - 5){
            moveX(5);
        }
        if(playerPosistsion.getY() < 10){
            playerPosistsion = playerPosistsion.add(0,1);
        }
        moveY((int)playerPosistsion.getY());

    }

    private void jumpPlayer() {
        if (canJump) {
            playerPosistsion= playerPosistsion.add(0, -24);
            canJump = false;
        }
    }

    private void moveX(int value){
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            for(Node asteroid : asteroids){
                if(player.getBoundsInParent().intersects(asteroid.getBoundsInParent())){
                    player.setTranslateX(50);
                    player.setTranslateY(301);
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void moveY(int value){
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + 30 == platform.getTranslateY()) {
                            player.setTranslateY(player.getTranslateY() - 1);
                            canJump = true;
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateY() == platform.getTranslateY() + 30) {
                            return;
                        }
                    }
                }
            }
            for(Node asteroid : asteroids){
                if(player.getBoundsInParent().intersects(asteroid.getBoundsInParent())){
                    player.setTranslateX(50);
                    player.setTranslateY(300);
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }


    private boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }

    @Override
    public void start(Stage primaryStage){
        gameBuild(2);
        Scene scene = new Scene(appRoot);
        scene.getStylesheets().add("CSS/Lvl1.css");
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        primaryStage.setTitle("Space bounce");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameloop();
            }
        };
        timer.start();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
