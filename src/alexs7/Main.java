package alexs7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        primaryStage.setTitle("Load an Image");
        primaryStage.setScene(new Scene(root));

        BorderPane mainBorderPane = (BorderPane) primaryStage.getScene().lookup("#mainBorderPane");
        mainBorderPane.setPadding(new Insets(8));

        HBox controls = new HBox();
        Button loadBtn = new Button("Load Image");
        Button sobelEdgeDetectionBtn  = new Button("Sobel Edge Detection");
        loadBtn.setMaxWidth(Double.MAX_VALUE);
        sobelEdgeDetectionBtn.setMaxWidth(Double.MAX_VALUE);

        setBrowseFileAction(loadBtn);

        controls.setSpacing(8);
        controls.getChildren().addAll(loadBtn,sobelEdgeDetectionBtn);
        mainBorderPane.setBottom(controls);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void setBrowseFileAction(Button loadBtn) {
        loadBtn.setOnAction((event) -> {
            Object source = event.getSource();
            Scene scene = ((Node) source).getScene();
            Stage stageOfEvent = (Stage) ((Node) source).getScene().getWindow();
            BorderPane mainBorderPane = (BorderPane) scene.lookup("#mainBorderPane");
            ImageView imageView = new ImageView();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG","*.jpg"),
                    new FileChooser.ExtensionFilter("JPEG","*.jpeg")
            );
            fileChooser.setTitle("Choose file...");
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
            );
            File file = fileChooser.showOpenDialog(stageOfEvent);

            if(file != null) {
                Image image = new Image("file:"+file.getAbsolutePath());
                imageView.setImage(image);
                mainBorderPane.setCenter(imageView);
                Utils.resizeToImage(stageOfEvent,image);
                Utils.centerStage(stageOfEvent);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}
