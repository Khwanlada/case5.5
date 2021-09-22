package controller;

import com.sun.javafx.tk.FontMetrics;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import model.Direction;
import model.Food;
import model.Snake;
import model.SpecialFood;
import view.Platform;

import javax.swing.*;
import java.awt.*;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private float interval = 1000.0f / 10;
    private boolean running;

    public GameLoop(Platform platform, Snake snake, Food food, SpecialFood specialFood) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        this.specialFood = specialFood;
        running = true;
    }

    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
        }
        if (snake.isCollidingWith(specialFood)) {
            snake.grow();
            specialFood.respawn();
        }
        if (snake.isDead()) {
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake, food , specialFood);
    }

    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Platform getPlatform() {
        return platform;
    }



}
