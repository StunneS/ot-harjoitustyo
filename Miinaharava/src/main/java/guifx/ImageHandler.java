/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import javafx.scene.image.Image;

/**
 *
 * @author Sade-Tuuli
 */
public class ImageHandler {
    private Image[] images;
    public ImageHandler() {
        images = new Image[14];
        images[0] = new Image("file:resources\\exposed.png");
        images[1] = new Image("file:resources\\number1.png");
        images[2] = new Image("file:resources\\number2.png");
        images[3] = new Image("file:resources\\number3.png");
        images[4] = new Image("file:resources\\number4.png");
        images[5] = new Image("file:resources\\number5.png");
        images[6] = new Image("file:resources\\number6.png");
        images[7] = new Image("file:resources\\number7.png");
        images[8] = new Image("file:resources\\number8.png");
        images[9] = new Image("file:resources\\blank.png");
        images[10] = new Image("file:resources\\flag.png");
        images[11] = new Image("file:resources\\mine.png");
        images[12] = new Image("file:resources\\hitmine.png");
        images[13] = new Image("file:resources\\wrongmine.png");
    }
    public Image setImage(int x) {
        return images[x];
    }
    public int getImageNumber(Image x) {
        for(int i = 0; i < images.length; i++) {
            if(x.toString().equals(images[0].toString())) {
                return 1;
            }
        }
        return -1;
    }
}
