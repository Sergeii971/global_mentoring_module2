package com.os.module2.task7;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage hugeImage = ImageIO.read(new File("/Users/Siarhei_Viarbouski/IdeaProjects/global_mentoring_module2/src/main/resources/imagematrix.png"));
        int[] pixels = convertByteArrayToIntArray(((DataBufferByte) hugeImage.getRaster().getDataBuffer()).getData());

        ForkBlur fb = new ForkBlur(pixels, 0, pixels.length, pixels);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(fb);


    }

    public static int[] convertByteArrayToIntArray(byte[] data) {
        if (data == null || data.length % 4 != 0) return null;
        // ----------
        int[] ints = new int[data.length / 4];
        for (int i = 0; i < ints.length; i++)
            ints[i] = (convertByteArrayToInt(new byte[]{
                    data[(i * 4)],
                    data[(i * 4) + 1],
                    data[(i * 4) + 2],
                    data[(i * 4) + 3],
            }));
        return ints;
    }


    private static int convertByteArrayToInt(byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        // ----------
        return (int) ( // NOTE: type cast not necessary for int
                (0xff & data[0]) << 24 |
                        (0xff & data[1]) << 16 |
                        (0xff & data[2]) << 8 |
                        (0xff & data[3]) << 0
        );
    }
}

