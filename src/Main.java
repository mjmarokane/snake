import dev.mjmarokane.gui.GamePane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GamePane root = new GamePane();
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(root, 500, 500));
        root.init();
        primaryStage.show();
    }
}