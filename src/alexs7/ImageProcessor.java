package alexs7;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Created by alex on 26/09/15.
 */
public class ImageProcessor {

    public Image grayscaleImage(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage((int) image.getWidth(),(int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int i = 0; i < wImage.getHeight(); i++) {
            for (int j = 0; j < wImage.getWidth(); j++) {
                double red = pixelReader.getColor(j,i).getRed();
                double green = pixelReader.getColor(j,i).getGreen();
                double blue = pixelReader.getColor(j,i).getBlue();
                double avg = (red + green + blue)/3;
                pixelWriter.setColor(j,i,new Color(avg,avg,avg,1));
            }
        }

        return wImage;
    }

}
