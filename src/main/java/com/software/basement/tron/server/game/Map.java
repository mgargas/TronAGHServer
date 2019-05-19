package com.software.basement.tron.server.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {

        private int width;
        private int height;

        public Map(int height, int width){
            this.height = height;
            this.width = width;
        }

        public void fillBoard(int[][] board){

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(getClass().getClassLoader().getResource("AGH.png").getFile()));
                int height = img.getHeight();
                int width = img.getWidth();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //System.out.println(height + "  " + width + " " + img.getRGB(30, 30));

            for (int h = 1; h < height; h++) {
                for (int w = 1; w < width; w++) {

                    int rgb = img.getRGB(w, h);
                    int red = (rgb >> 16) & 0x000000FF;
                    int green = (rgb >> 8) & 0x000000FF;
                    int blue = (rgb) & 0x000000FF;
                    //System.out.println(rgb);

                    if (red == 0 && green == 0 && blue == 0) {
                        board[w][h] = 1;
                    }
                }
            }
        }


}