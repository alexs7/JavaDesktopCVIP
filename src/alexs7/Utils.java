package alexs7;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by alex on 20/09/15.
 */
public class Utils {

    public Utils(){}

    public void setBrowseFileAction(Button btn) {
        btn.setOnAction((event) -> {
            Object source = event.getSource();
            Scene scene = ((Node) source).getScene();
            Stage stageOfEvent = (Stage) ((Node) source).getScene().getWindow();
            BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");
            ImageView imageView = new ImageView();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
            );
            fileChooser.setTitle("Choose file...");
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
            );
            File file = fileChooser.showOpenDialog(stageOfEvent);

            if (file != null) {
                Image image = new Image("file:" + file.getAbsolutePath());
                imageView.setImage(image);
                mainBorderPane.setCenter(imageView);
                resizeToImage(stageOfEvent, image);
                centerStage(stageOfEvent);
            }
        });
    }

    public void setSobelEdgeDetection(Button btn) {
        btn.setOnAction((event) -> {
            Object source = event.getSource();
            Scene scene = ((Node) source).getScene();
            BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

            ImageView imageView = (ImageView) mainBorderPane.getCenter();
            MImage mImage = new MImage(imageView.getImage());

            imageView.setImage(mImage.sobelEdgeDetection());
        });
    }

    private void centerStage(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void resizeToImage(Stage stage, Image image) {
        stage.setWidth(image.getWidth() + 40);
        stage.setHeight(image.getHeight() + 140);
    }
}
