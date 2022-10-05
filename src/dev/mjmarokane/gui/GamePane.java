package dev.mjmarokane.gui;

import dev.mjmarokane.model.GameWorld;
import dev.mjmarokane.gui.GameCanvas;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public class GamePane extends StackPane
{
    private GameWorld world;
    public GamePane() 
    {
        BorderPane root = new BorderPane();
        world = new GameWorld(50, 50);
        GameCanvas canvas = new GameCanvas(500, 500, world);
        root.setCenter(canvas);
        this.getChildren().add(root);
    }

    public void init()
    {
        setUpKeyControls();
    }

    private void setUpKeyControls()
    {
        this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getText().toLowerCase())
                {
                case "a":
                    world.getSnake().setDirectionLeft();
                    break;
                case "s":
                    world.getSnake().setDirectionDown();
                    break;
                case "d":
                    world.getSnake().setDirectionRight();
                    break;
                case "w":
                    world.getSnake().setDirectionUp();
                    break;
                }   
            }
        });
    }
}