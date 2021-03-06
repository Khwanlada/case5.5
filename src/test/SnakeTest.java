package test;


import javafx.geometry.Point2D;
import model.Direction;
import model.Food;
import model.Snake;
import model.SpecialFood;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {
    private Snake snake;

    @Before
    public void setup() {
        snake = new Snake(new Point2D(0, 0));
    }

    @Test
    public void snakeShouldBeSpawnAtTheCoordinateItWasCreated() {
        assertEquals(snake.getHead(), new Point2D(0, 0));
    }


    @Test
    public void snakeShouldMoveDownwardIfItIsHeadingDownward() {
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        assertEquals(snake.getHead(), new Point2D(0, 1));
    }

    @Test
    public void collisionFlagShouldRaiseIfSnakeCollideWithFood() {
        Food food = new Food(new Point2D(0, 0));
        //Add
        SpecialFood specialFood = new SpecialFood(new Point2D(0,0));
        assertTrue(snake.isCollidingWith(food));
        //Add
        assertTrue(snake.isCollidingWith(specialFood));
    }

    @Test
    public void foodShouldRespawnOnDifferentCoordinates() {
        Food food = new Food(new Point2D(0, 0));
        food.respawn();
        //Add
        SpecialFood specialFood = new SpecialFood(new Point2D(0,0));
        specialFood.respawn();

        assertNotSame(food.getPosition(), new Point2D(0, 0));

        //Add
        assertNotSame(specialFood.getPosition(), new Point2D(0, 0));
    }

    @Test
    public void snakeGrowthShouldAddItsLengthByOne() {
        snake.grow();
        assertEquals(snake.getLength(), 2);
    }
    //Add
    @Test
    public void snakeGrowthShouldAddItsLengthByFive() {
        snake.specialGrow();
        assertEquals(snake.getLength(), 6);
    }

    @Test
    public void bodyOfGrownSnakeShouldContainPreviousHead() {
        Point2D cur_head = snake.getHead();
        snake.update();
        snake.grow();
        assertTrue(snake.getBody().contains(cur_head));
    }
    @Test
 public void snakeWillDieIfItGoesBeyondTheGameBorder() {
         snake = new Snake(new Point2D(30,30));
         snake.setCurrentDirection(Direction.RIGHT);
         snake.update();
         assertTrue(snake.isDead());
         }
 @Test
 public void snakeWillDieIfItHitsItsBody() {
         snake = new Snake(new Point2D(0,0));
         snake.setCurrentDirection(Direction.DOWN);
         snake.update();
         snake.grow();
         snake.setCurrentDirection(Direction.LEFT);
         snake.update();
         snake.grow();
         snake.setCurrentDirection(Direction.UP);
         snake.update();
         snake.grow();
         snake.setCurrentDirection(Direction.RIGHT);
         snake.update();
         snake.grow();
         assertTrue(snake.isDead());
         }

}
