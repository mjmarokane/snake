package dev.mjmarokane.gui;

import dev.mjmarokane.model.GameWorld;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameCanvas extends Canvas
{
    private DrawGraphicsVisitor visitor;
    private GameWorld world;
    private int updateSpeed;
    private int frameCounter;
    private AnimationTimer timer;

    public GameCanvas(int width, int height, GameWorld world)
    {
        setWidth(width);
        setHeight(height);
        this.world = world;
        this.visitor = new DrawGraphicsVisitor(getGraphicsContext2D(), width/world.getColumns());
        this.updateSpeed = world.getSnake().getSpeed();
        this.frameCounter = 0;
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                redraw();
                frameCounter++;
            }
        };
        timer.start();
    }

    private void redraw()
    {
        world.accept(visitor);
        world.getSnake().accept(visitor);
        world.getFood().accept(visitor);
        if (world.isGameOver()) 
        {
            timer.stop();
        }
        if (frameCounter == updateSpeed)
        {
            world.update();
            frameCounter = 0;   
            
        }
        
    }
}