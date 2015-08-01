package pixelstoarray;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author Paweł Janeczek
 */
public class PixelsToArray {

    public static void main(String[] args) throws IOException {

        /* Pierwszy PNG -> Array */
        File imageFileFirst = new File("C://Users/Paweł/Desktop/proba1.png");
        BufferedImage imageFirst = ImageIO.read(imageFileFirst);

        System.out.println("Szerokość obrazka = " + imageFirst.getWidth() + " "
                + "Wysokość obrazka = " + imageFirst.getHeight());

        /* wymiary pierwszego */
        int width = imageFirst.getWidth();
        int height = imageFirst.getHeight();
        int pix = width * height;
        int Array[] = new int[pix];

        /* Drugi PNG -> Array2 */
        File imageFile2 = new File("C://Users/Paweł/Desktop/proba1.png");
        BufferedImage imageSecond = ImageIO.read(imageFile2);
        System.out.println("Szerokość obrazka = " + imageSecond.getWidth() + " "
                + "Wysokość obrazka = " + imageSecond.getHeight());

        /* wymiary drugiego */
        int width2 = imageSecond.getWidth();
        int height2 = imageSecond.getHeight();
        int pix2 = width2 * height2;
        int Array2[] = new int[pix2];
        int widthO = 0;
        int heightO = 0;
        /* Klon pierwszego */
        BufferedImage imagebuffcopy = ImageIO.read(imageFileFirst);

        /* Ustawienie rozmiarów obrazów wg większego */
        if (width > width2) {
            widthO = width;
            heightO = height;
            try {
                BufferedImage bufImage = ImageIO.read(imageFileFirst);
                BufferedImage smallToBig = Scalr.resize(bufImage, Scalr.Mode.AUTOMATIC, width, height);
                ImageIO.write(smallToBig, "png", imageFileFirst);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            widthO = width2;
            heightO = height2;
            try {
                BufferedImage bufImage = ImageIO.read(imageFileFirst);
                BufferedImage smallToBig = Scalr.resize(bufImage, Scalr.Mode.AUTOMATIC, width2, height2);
                ImageIO.write(smallToBig, "png", imageFileFirst);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /* Wpisanie kazdego pixela z osobna do tablicy dla obrazu pierwszego */
        int elTab = 0;
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Array[elTab] = imageFirst.getRGB(w, h);
                elTab++;
            }
        }


        /* Pobranie nasycenia RGB z każdego pixela dla obrazu pierwszego */
        pngImage[] png = new pngImage[Array.length];
        for (int i = 0; i < Array.length; i++) {

            png[i] = new pngImage();
            png[i].redColor = (Array[i] & 0x00ff0000) >> 16;
            png[i].greenColor = (Array[i] & 0x0000ff00) >> 8;
            png[i].blueColor = Array[i] & 0x000000ff;

        }

        /* Wpisanie kazdego pixela z osobna do tablicy dla obrazu drugiego*/
        int elTab2 = 0;
        for (int w = 0; w < width2; w++) {
            for (int h = 0; h < height2; h++) {
                Array2[elTab2] = imageSecond.getRGB(w, h);
                elTab2++;
            }
        }

        /* Pobranie nasycenia RGB z każdego pixela dla obrazu drugiego */
        pngImage[] png2 = new pngImage[Array2.length];
        for (int i = 0; i < Array2.length; i++) {

            png2[i] = new pngImage();
            png2[i].redColor = (Array2[i] & 0x00ff0000) >> 16;
            png2[i].greenColor = (Array2[i] & 0x0000ff00) >> 8;
            png2[i].blueColor = Array2[i] & 0x000000ff;

        }

        int arrayTest[][] = new int[widthO][heightO];
        for (int w = 0; w < widthO; w++) {
            for (int h = 0; h < heightO; h++) {
                arrayTest[w][h] = imageFirst.getRGB(w, h);

            }
        }

        int arrayTest2[][] = new int[widthO][heightO];
        for (int w = 0; w < widthO; w++) {
            for (int h = 0; h < heightO; h++) {
                arrayTest2[w][h] = imageSecond.getRGB(w, h);

            }
        }

        int arrayTest3[][] = new int[widthO][heightO];
        for (int w = 0; w < widthO; w++) {
            for (int h = 0; h < heightO; h++) {
                arrayTest3[w][h] = imagebuffcopy.getRGB(w, h);

            }
        }

        int rgbWhite = new Color(255, 255, 255).getRGB();
        int rgbBlack = new Color(0, 0, 0).getRGB();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {

                Color colorPNGFirst = new Color(arrayTest[w][h], true);
                Color colorPNGSecond = new Color(arrayTest2[w][h], true);

                if (colorPNGFirst.getRed() == colorPNGSecond.getRed() && colorPNGFirst.getGreen() == colorPNGSecond.getGreen() && colorPNGFirst.getBlue() == colorPNGSecond.getBlue()) {

                    imagebuffcopy.setRGB(w, h, rgbWhite);

                } else {

                    imagebuffcopy.setRGB(w, h, rgbBlack);

                }

            }
        }

        File imageFile = new File("C://Users/Paweł/Desktop/proba5.png");
        ImageIO.write(imagebuffcopy, "png", imageFile);

    }
}
