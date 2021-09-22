package controller;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Food;
import model.Snake;
import model.SpecialFood;
import view.Platform;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(platform.WIDTH / 2, platform.HEIGHT / 2));
        Food food = new Food();
        SpecialFood specialFood = new SpecialFood();
        GameLoop gameLoop = new GameLoop(platform, snake, food,specialFood);
        Scene scene = new Scene(platform, platform.WIDTH * platform.TILE_SIZE, platform.HEIGHT * platform.TILE_SIZE);
        scene.setOnKeyPressed(event -> platform.setKey(event.getCode()));
        scene.setOnKeyReleased(event -> platform.setKey(null));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        (new Thread(gameLoop)).start();
    }
}
