package bounce.maingameloop;

import bounce.lvl2data.Lvl2Data;
import bounce.lvl1data.Lvl1Data;
import bounce.lvl3data.Lvl3Data;
import bounce.lvlbuilds.healts.Health;
import bounce.lvlbuilds.lvl1build.Lvl1Build;
import bounce.lvlbuilds.lvl2build.Lvl2Build;
import bounce.lvlbuilds.lvl3build.Lvl3Build;
import bounce.lvlbuilds.lvlcircles.LvlCircle;
import bounce.lvlbuilds.lvlrectangles.LvlRectangle;
import javafx.animation.RotateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private HashMap<KeyCode, Boolean> keys;
    private ArrayList<Node> platforms;
    private ArrayList<Node> badGuys;
    private Pane appRoot;
    private Pane gameRoot;
    private boolean canJump;
    private Circle player;
    private Point2D playerPosistsion;
    private int levelWidth;
    private LvlRectangle portal;
    private Health health;

    public Main() {
        this.appRoot = new Pane();
        this.gameRoot = new Pane();
        this.keys = new HashMap<>();
        this.platforms = new ArrayList<>();
        this.badGuys = new ArrayList<>();
        this.canJump = true;
        this.player = new Circle();
        this.playerPosistsion = new Point2D(0, 0);
        this.levelWidth = 0;
        this.portal = new LvlRectangle(gameRoot);
        this.health = new Health(gameRoot);
    }


    public void gameBuild(int lvlNumber) {
        Node background = this.background(lvlNumber);
        System.out.println("ez");
        if (lvlNumber == 1) {
            this.Lvl1Build();
            this.levelWidth = Lvl1Data.LEVEL1[0].length() * 30;
            this.health.createHealth();
        } else if (lvlNumber == 2) {
            this.Lvl2Build();
            this.levelWidth = Lvl2Data.LEVEL2[0].length() * 30;
        } else if (lvlNumber == 3) {
            this.Lvl3Build();
            this.levelWidth = Lvl3Data.LEVEL3[0].length() * 30;
        }
        player = createCircle(50, 300, 20, 20, 13, Color.BLUE);
        player.translateXProperty().addListener((obs, old, newValue) -> {
                    int offset = newValue.intValue();

                    if (offset > 480 && offset < levelWidth - 480) {
                        gameRoot.setLayoutX(-(offset - 480));
                    }
                }
        );
        appRoot.getChildren().addAll(background, gameRoot);
    }


    private void Lvl2Build() {
        Lvl2Build lvl2build = new Lvl2Build(platforms, badGuys, portal, new LvlCircle(gameRoot), new LvlRectangle(gameRoot));
        lvl2build.build();
    }

    private void Lvl1Build() {
        Lvl1Build lvl1build = new Lvl1Build(platforms, badGuys, portal, new LvlCircle(gameRoot), new LvlRectangle(gameRoot));
        lvl1build.Lvl1Build();
    }

    private void Lvl3Build() {
        Lvl3Build lvl3build = new Lvl3Build(platforms, badGuys, new LvlCircle(gameRoot), new LvlRectangle(gameRoot));
        lvl3build.build();
    }


    private Node background(int lvlNumber) {
        LvlRectangle rectangle = new LvlRectangle(gameRoot);
        return rectangle.background(lvlNumber);
    }

    private Rectangle createRectangle(int x, int y, int z, int h, Image image) {
        LvlRectangle rectangle = new LvlRectangle(gameRoot);
        return rectangle.createRectangle(x, y, z, h, image);
    }

    private Circle createCircle(int x, int y, int z, int h, int radius, Color color) {
        LvlCircle circle = new LvlCircle(gameRoot);
        return circle.createCircle(x, y, z, h, radius, color);
    }


    public void movementGameloop() {
        if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
            jumpPlayer();
        }
        if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
            moveXcoordinates(-5);
            RotateTransition rt = new RotateTransition(Duration.millis(20), player);
            rt.setByAngle(-15);
            rt.setCycleCount(1);
            rt.play();

        }
        if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
            moveXcoordinates(5);
            RotateTransition rt = new RotateTransition(Duration.millis(20), player);
            rt.setByAngle(15);
            rt.setCycleCount(1);
            rt.play();
        }
        if (playerPosistsion.getY() < 10) {
            playerPosistsion = playerPosistsion.add(0, 1);
        }
        moveYcoordinates((int) playerPosistsion.getY());

    }

    private void jumpPlayer() {
        if (canJump) {
            playerPosistsion = playerPosistsion.add(0, -24);
            canJump = false;
        }
    }

    private void moveXcoordinates(int value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    } else {
                        if (player.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            for (Node asteroid : badGuys) {
                if (player.getBoundsInParent().intersects(asteroid.getBoundsInParent())) {
                    player.setTranslateX(50);
                    player.setTranslateY(301);
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void moveYcoordinates(int value) {
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
                    } else {
                        if (player.getTranslateY() == platform.getTranslateY() + 30) {
                            return;
                        }
                    }
                }
            }
            for (Node asteroid : badGuys) {
                if (player.getBoundsInParent().intersects(asteroid.getBoundsInParent())) {
                    player.setTranslateX(50);
                    player.setTranslateY(300);
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }


    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public Circle getPlayer() {
        return player;
    }

    public LvlRectangle getPortal() {
        return portal;
    }

    public HashMap<KeyCode, Boolean> getKeys() {
        return keys;
    }

    public Pane getAppRoot() {
        return appRoot;
    }
}


