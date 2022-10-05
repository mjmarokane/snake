package dev.mjmarokane.model;

import dev.mjmarokane.gui.IDrawVisitor;
import dev.mjmarokane.gui.IDrawable;
import java.util.ArrayList;

public class Snake implements IDrawable
{
    private ArrayList<Cell> cells = new ArrayList<>();
    private Direction direction;
    private int speed;
    private static boolean crossed = false;

    public Snake(int headX, int headY, int speed)
    {
        for (int i = 0; i < 100; i++)
        {
            cells.add(new Cell(headX + i, headY));
        }
        this.direction = Direction.LEFT;
        this.speed = speed;
    }

    public void move()
    {
        //move the tail to follow the head
        for(int i = cells.size() - 1; i > 0; i--)
        {
            cells.get(i).setX(cells.get(i - 1).getX());
            cells.get(i).setY(cells.get(i - 1).getY());
        }
        //change position of heead according to direction
        switch(direction)
        {
        case UP:
            cells.get(0).setY(cells.get(0).getY() - 1);
            break;
        case DOWN:
            cells.get(0).setY(cells.get(0).getY() + 1);
            break;
        case RIGHT:
            cells.get(0).setX(cells.get(0).getX() + 1);
            break;
        case LEFT:
            cells.get(0).setX(cells.get(0).getX() - 1);
            break;
        }

        //check if the snake bit itself
        for (int i = 1; i < cells.size(); i++)
        {
            if (cells.get(0).getX() == cells.get(i).getX() &
                cells.get(0).getY() == cells.get(i).getY())
            {
                crossed = true;
            }
        }
    }

    //method for growing the snake
    public void grow()
    {
        cells.add(new Cell(-1, -1));
    }  
    
    public int getHeadX()
    {
        return this.cells.get(0).x;
    }

    public int getHeadY()
    {
        return this.cells.get(0).y;
    }

    public boolean isCrossed()
    {
        return crossed;
    }

    public ArrayList<Cell> getCells()
    {
        return this.cells;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void setHeadX(int x)
    {
        cells.get(0).setX(x);
    }

    public void setHeadY(int y)
    {
        cells.get(0).setY(y);
    }

    public void setDirectionUp()
    {
        if(direction == Direction.DOWN) return;
        direction = Direction.UP;
    }

    public void setDirectionDown()
    {
        if(direction == Direction.UP) return;
        direction = Direction.DOWN;
    }

    public void setDirectionLeft()
    {
        if(direction == Direction.RIGHT) return;
        direction = Direction.LEFT;
    }

    public void setDirectionRight()
    {
        if(direction == Direction.LEFT) return;
        direction = Direction.RIGHT;
    }

    @Override
    public void accept(IDrawVisitor visitor)
    {
        visitor.visit(this);
    }

    public enum Direction
    {
        UP, DOWN, LEFT, RIGHT
    }

    public class Cell
    {
        private int x;
        private int y;

        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX()
        {
            return this.x;
        }

        public int getY()
        {
            return this.y;
        }

        public void setX(int x)
        {
            this.x = x;
        }
        
        public void setY(int y)
        {
            this.y = y;
        }
    }
}