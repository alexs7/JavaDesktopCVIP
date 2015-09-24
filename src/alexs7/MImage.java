package alexs7;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Created by ar1v13 on 24/09/15.
 */
public class MImage {

    private final WritableImage wImage;
    private int width;
    private int height;

    public MImage(Image image){
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(pixelReader,(int) image.getWidth(),(int) image.getHeight());
        this.wImage = writableImage;
        this.width = (int) writableImage.getWidth();
        this.height = (int) writableImage.getHeight();
    }

    public WritableImage sobelEdgeDetection() {
        

        return wImage;
    }
}