package com.uitest.dropanimation.coindemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import java.util.HashMap;

/**
 * This class represents a single coin, with properties representing its
 * size, rotation, location, and speed.
 */
public class Coin {

    // These are the unique properties of any coin: its size, rotation, speed,
    // location, and its underlying Bitmap object
    float x, y;
    float rotation;
    float speed;
    float rotationSpeed;
    int width, height;
    Bitmap bitmap;

    // This map stores pre-scaled bitmaps according to the width. No reason to
    // create
    // new bitmaps for sizes we've already seen.
    static HashMap<Integer, Bitmap> bitmapMap = new HashMap<Integer, Bitmap>();

    /**
     * Creates a new droidcoin in the given xRange and with the given bitmap.
     * Parameters of location, size, rotation, and speed are randomly
     * determined.
     */
    static Coin createCoin(float xRange, Bitmap originalBitmap,
                           Context Context) {
        Coin coin = new Coin();
        // Size each coin with a width between 5 and 55 and a proportional
        // height
        DisplayMetrics metrics = DvAppUtil.getDisplayMetrics(Context);
        if (metrics.widthPixels >= 1080) {
            coin.width = (int) (5 + (float) Math.random() * 80);
            float hwRatio = originalBitmap.getHeight()
                    / originalBitmap.getWidth();
            coin.height = (int) (coin.width * hwRatio + 60);
        } else {
            coin.width = (int) (5 + (float) Math.random() * 50);
            float hwRatio = originalBitmap.getHeight()
                    / originalBitmap.getWidth();
            coin.height = (int) (coin.width * hwRatio + 40);

        }
        // Position the coin horizontally between the left and right of the
        // range
        coin.x = (float) Math.random() * (xRange - coin.width);
        // Position the coin vertically slightly off the top of the display
        coin.y = 0 - (coin.height + (float) Math.random() * coin.height);

        // Each coin travels at 50-200 pixels per second
        coin.speed = 50 + (float) Math.random() * 150;

        // Coins start at -90 to 90 degrees rotation, and rotate between -45
        // and 45
        // degrees per second
        coin.rotation = (float) Math.random() * 180 - 90;
        coin.rotationSpeed = (float) Math.random() * 90 - 45;

        // Get the cached bitmap for this size if it exists, otherwise create
        // and cache one
        coin.bitmap = bitmapMap.get(coin.width);
        if (coin.bitmap == null) {
            coin.bitmap = Bitmap.createScaledBitmap(originalBitmap,
                    (int) coin.width, (int) coin.height, true);
            bitmapMap.put(coin.width, coin.bitmap);
        }
        return coin;
    }
}
