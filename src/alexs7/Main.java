package alexs7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Utils utils = new Utils();

        primaryStage.setTitle("Load an Image");
        primaryStage.setScene(new Scene(root));

        BorderPane mainBorderPane = (BorderPane) primaryStage.getScene().lookup("#mainBorderPane");
        mainBorderPane.setPadding(new Insets(8));

        HBox controls = new HBox();
        Button loadBtn = new Button("Load Image");
        Button sobelEdgeDetectionBtn  = new Button("Sobel Edge Detection");
        loadBtn.setMaxWidth(Double.MAX_VALUE);
        sobelEdgeDetectionBtn.setMaxWidth(Double.MAX_VALUE);

        utils.setBrowseFileAction(loadBtn);
        utils.setSobelEdgeDetection(sobelEdgeDetectionBtn);

        controls.setSpacing(8);
        controls.getChildren().addAll(loadBtn,sobelEdgeDetectionBtn);
        mainBorderPane.setBottom(controls);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
