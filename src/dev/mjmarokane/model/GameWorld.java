package dev.mjmarokane.model;

import dev.mjmarokane.model.Snake;
import dev.mjmarokane.gui.IDrawable;
import dev.mjmarokane.gui.IDrawVisitor;
import java.lang.Math;
import java.util.Random;

public class GameWorld implements IDrawable
{
    private int columns;
    private int rows;
    private boolean gameOver;
    private Snake snake;
    private Food food;
    private int score;
    public GameWorld(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        snake = new Snake(Math.round(columns/2), Math.round(rows/2), 4);
        food = new Food();
        placeNewFood();
        this.gameOver = false;
        this.score = 110;
    }

    public void checkEndState()
    {
        if (snake.isCrossed())
        {
            gameOver = true;
        }
    }

    public int getRows()
    {
        return this.rows;
    }

    public int getColumns()
    {
        return this.columns;
    }

    public boolean isGameOver()
    {
        return this.gameOver;
    }

    public Snake getSnake()
    {
        return this.snake;
    }

    public Food getFood()
    {
        return this.food;
    }

    public int getScore()
    {
        return this.score;
    }

    public void update()
    {
        if (snake.getHeadX() == food.getX() & snake.getHeadY() == food.getY())
        {
            this.score++;
            snake.grow();
            placeNewFood();
        }
        snake.move();
        wrapSnakeAround();
        checkEndState();
    }

    private void wrapSnakeAround()
    {
        if (snake.getHeadX() < 0)
        {
            snake.setHeadX(this.columns - 1);
        }
        if (snake.getHeadY() < 0)
        {
            snake.setHeadY(this.rows - 1);
        }
        if (snake.getHeadX() >= this.columns)
        {
            snake.setHeadX(0);
        }
        if (snake.getHeadY() >= this.rows)
        {
            snake.setHeadY(0);
        }
    }

    private void placeNewFood()
    {
        int x;
        int y;
        Random r = new Random();
        do
        {
            x = r.nextInt(this.columns);
            y = r.nextInt(this.rows);
        } while (!isEmpty(x, y));
        food.setX(x);
        food.setY(y);
    }

    //checks if a position is empty
    private boolean isEmpty(int x, int y)
    {
        for(int i = 0; i < snake.getCells().size(); i++)
        {
            if (snake.getCells().get(i).getX() == x & 
                snake.getCells().get(i).getY() == y)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void accept(IDrawVisitor visitor)
    {
        visitor.visit(this);
    }
}