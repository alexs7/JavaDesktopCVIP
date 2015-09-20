package alexs7;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by alex on 20/09/15.
 */
public class Utils {
    public static void centerStage(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void resizeToImage(Stage stage, Image image) {
        stage.setWidth(image.getWidth() + 40);
        stage.setHeight(image.getHeight() + 140);
    }
}
