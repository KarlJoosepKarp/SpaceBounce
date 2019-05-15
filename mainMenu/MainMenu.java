package mainMenu;

import bounce.maingameloop.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import mainMenu.instructions.Instructions;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MainMenu extends Application {
    private Main lvl2 = new Main();
    private Scene scene2 = new Scene(lvl2.getAppRoot());
    private Main lvl3 = new Main();
    private Scene scene3 = new Scene(lvl3.getAppRoot());


    private Button exitbutton(Group parent){
        Button exit = new Button("EXIT");
        exit.setLayoutX(280);
        exit.setLayoutY(550);
        exit.getStyleClass().add("buttons");
        parent.getChildren().add(exit);
        return exit;
    }
    private Button newGameButton(Group parent){
        Button start = new Button("NEW GAME");
        start.setLayoutX(200);
        start.setLayoutY(350.0);
        start.getStyleClass().add("buttons");
        parent.getChildren().add(start);
        return start;

    }
    /*
    private Button settingsButton(Group parent){
        Button settings = new Button("Seaded");
        settings.setLayoutX(298);
        settings.setLayoutY(460);
        settings.setStyle("-fx-font: bold italic 20pt \"Arial\";\n" +
                "    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        parent.getChildren().add(settings);
        return settings;
    }
    */
    private Button instructionsButton(Group parent){
        Button instructions = new Button("GUIDE");
        instructions.setLayoutX(260);
        instructions.setLayoutY(450);
        instructions.getStyleClass().add("buttons");
        parent.getChildren().add(instructions);
        return instructions;
    }

    private void title(Group parent){
        String path = "C:\\Users\\Karl Joosep\\Desktop\\space.mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(950);
        mediaView.setFitWidth(950);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        Text name = new Text("SPACE BOUNCE");
        name.setCache(true);
        name.setX(35);
        name.setY(270);
        name.setFill(Color.DARKORANGE);
        name.setFont(Font.font("monospace", FontWeight.EXTRA_BOLD, 90));
        name.setBoundsType(TextBoundsType.VISUAL);
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        name.setEffect(r);
        parent.getChildren().add(mediaView);
        parent.getChildren().add(name);

    }


    @Override
    public void start(Stage stage){
        Instructions instruct = new Instructions();
        Group parent = new Group();
        Group info = new Group();
        Scene infoScene = new Scene(info,700,700);
        title(parent);
        Button exit = exitbutton(parent);
        Button newgame = newGameButton(parent);
        Button instructions = instructionsButton(parent);
        StackPane infoWindows = instruct.InfoWindow();
        info.getChildren().addAll(infoWindows);
        Scene sceneMenu = new Scene(parent, 700, 700);
        MainMenu menu = new MainMenu();
        Main lvl = new Main();
        lvl.gameBuild(1);
        Scene scene = new Scene(lvl.getAppRoot());
        scene.setOnKeyPressed(event -> lvl.getKeys().put(event.getCode(), true));
        scene.setOnKeyReleased(event -> lvl.getKeys().put(event.getCode(), false));
        lvl2.gameBuild(2);
        scene2.setOnKeyPressed(event -> lvl2.getKeys().put(event.getCode(), true));
        scene2.setOnKeyReleased(event -> lvl2.getKeys().put(event.getCode(), false));
        lvl3.gameBuild(3);
        scene3.setOnKeyPressed(event -> lvl3.getKeys().put(event.getCode(), true));
        scene3.setOnKeyReleased(event -> lvl3.getKeys().put(event.getCode(), false));
        newgame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scene);
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if(stage.getScene().equals(scene)) {
                            stage.setScene(scene);
                            lvl.movementGameloop();
                        }
                        if (lvl.getPlayer().getBoundsInParent().intersects(lvl.getPortal().getRectangle().getBoundsInParent())) {
                            stage.setScene(scene2);
                            lvl2.movementGameloop();
                        }
                        if (lvl2.getPlayer().getBoundsInParent().intersects(lvl2.getPortal().getRectangle().getBoundsInParent())) {
                            stage.setScene(scene3);
                            lvl3.movementGameloop();
                        }
                    }
                };
                timer.start();
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
            }
        });
        instructions.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(infoScene);
                info.getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        stage.setScene(sceneMenu);
                    }
                });
            }
        });
        infoScene.getStylesheets().add("Css.css");
        sceneMenu.getStylesheets().add("Css.css");
        stage.setScene(sceneMenu);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

