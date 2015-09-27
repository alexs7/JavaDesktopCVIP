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
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by alex on 20/09/15.
 */
public class Utils {

    private Image defaultImage;

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
                defaultImage = image;
                mainBorderPane.setCenter(imageView);
                resizeToImage(stageOfEvent, image);
                centerStage(stageOfEvent);
                loadControls(stageOfEvent);
            }
        });
    }

    private void loadControls(Stage stage) {
        BorderPane mainBorderPane = (BorderPane) stage.getScene().lookup("#mainBorderPane");
        HBox hBox = (HBox) mainBorderPane.getBottom();

        Button prewittXEdgeDetectionBtn  = new Button("Prewitt X Edge Detection");
        prewittXEdgeDetectionBtn.setMaxWidth(Double.MAX_VALUE);
        setPrewittEdgeDetectionAction(prewittXEdgeDetectionBtn, "X");

        Button prewittYEdgeDetectionBtn  = new Button("Prewitt Y Edge Detection");
        prewittYEdgeDetectionBtn.setMaxWidth(Double.MAX_VALUE);
        setPrewittEdgeDetectionAction(prewittYEdgeDetectionBtn, "Y");

        hBox.getChildren().add(prewittXEdgeDetectionBtn);
        hBox.getChildren().add(prewittYEdgeDetectionBtn);
    }

    public void setPrewittEdgeDetectionAction(Button btn, String direction) {
        btn.setOnAction((event) -> {
            Object source = event.getSource();
            Scene scene = ((Node) source).getScene();
            BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");

            ImageView imageView = (ImageView) mainBorderPane.getCenter();
            Image image = imageView.getImage();
            EdgeDetector edgeDetector = new EdgeDetector();

            imageView.setImage(edgeDetector.prewittEdgeDetector(image, direction));
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

    public void setResetAction(Button btn) {
        btn.setOnAction((event) -> {
            if( defaultImage != null) {
                Object source = event.getSource();
                Scene scene = ((Node) source).getScene();
                BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");
                ImageView imageView = (ImageView) mainBorderPane.getCenter();
                imageView.setImage(defaultImage);
            }
        });
    }
}
