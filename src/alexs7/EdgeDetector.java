package alexs7;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

/**
 * Created by alex on 26/09/15.
 */
public class EdgeDetector {

    public EdgeDetector() {}

    public Image prewittEdgeDetector(Image image, String direction) {
        ImageProcessor imageProcessor = new ImageProcessor();
        image = imageProcessor.grayscaleImage(image);
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage((int) image.getWidth(),(int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        alexs7.Template prewittTemplate;

        switch(direction) {
            case "X":   prewittTemplate = new alexs7.Template(-1,0,1,-1,0,1,-1,0,1);
                        break;
            case "Y":   prewittTemplate = new alexs7.Template(-1,-1,-1,0,0,0,1,1,1);
                        break;
            default:    prewittTemplate = new alexs7.Template(1,1,1,1,1,1,1,1,1); // do nothing
                        break;
        }

        int templateXCenter = (int) Math.floor(prewittTemplate.getColumns()/2);
        int templateYCenter = (int) Math.floor(prewittTemplate.getRows()/2);

        //Convolution starts here!
        double sum = 0;
        for (int i = templateYCenter; i < image.getHeight()-templateYCenter; i++) {
            for (int j = templateXCenter; j < image.getWidth()-templateXCenter; j++) {

                sum = 0;

                for (int k = 0; k < prewittTemplate.getRows(); k++) {
                    for (int l = 0; l < prewittTemplate.getColumns(); l++) {
                        sum = sum + prewittTemplate.valueAt(k,l) * getGreyScaleImagePixel((j-1)+k,(i-1)+l,pixelReader);
                    }
                }
                if(sum < 0 ) sum = 0;
                if(sum > 1 ) sum = 1;
                pixelWriter.setColor(j,i,new Color(sum,sum,sum,1));
            }
        }

        return wImage;
    }

    private double getGreyScaleImagePixel(int j, int i, PixelReader pixelReader) {
        return pixelReader.getColor(j,i).getRed(); //any (R,G,B) will be the same since it is greyscaled
    }
}
