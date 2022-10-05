package dev.mjmarokane.gui;

import dev.mjmarokane.model.GameWorld;
import dev.mjmarokane.model.Snake;
import dev.mjmarokane.model.Food;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrawGraphicsVisitor implements IDrawVisitor
{
    private GraphicsContext gc;
    private int elementSize;
    
    public DrawGraphicsVisitor(GraphicsContext gc, int elementSize)
    {
        this.gc = gc;
        this.elementSize = elementSize;
    }

    public void visit(GameWorld world)
    {
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, world.getColumns() * elementSize, world.getRows() * elementSize);
        gc.setStroke(Color.WHITE);
        gc.setFont(Font.font("Monospaced", FontWeight.NORMAL, 16));
        gc.strokeText("Score: " + world.getScore(), 10, 465);
        gc.strokeText("Head(" + world.getSnake().getHeadX() + "," 
                              + world.getSnake().getHeadY() + ")", 10, 485);

        if (world.isGameOver())
        {
            gc.setFont(Font.font("Monospaced", FontWeight.BOLD, 40));
            gc.setFill(Color.RED);
            gc.fillText("Game Over!", 140, 240);
        }
    }

    public void visit(Snake snake)
    {
        gc.setFill(Color.YELLOW);
        for(int i = 0; i < snake.getCells().size(); i++)
        {
            int x = snake.getCells().get(i).getX() * elementSize;
            int y = snake.getCells().get(i).getY() * elementSize;
            gc.fillOval(x, y, elementSize, elementSize);
        }
    }

    public void visit(Food food)
    {
        gc.setFill(Color.RED);
        int x = food.getX() * elementSize;
        int y = food.getY() * elementSize;
        gc.fillOval(x, y, elementSize, elementSize);
    }
}