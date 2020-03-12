package com.uitest.dropanimation.coindemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import com.uitest.R;

import java.util.ArrayList;

public class CoinView extends View {

    Bitmap droid; // The bitmap that all coins use
    int numCoins = 0; // Current number of coins
    ArrayList<Coin> coins = new ArrayList<Coin>(); // List of current coins

    // Animator used to drive all separate coin animations. Rather than have
    // potentially
    // hundreds of separate animators, we just use one and then update all
    // coins for each
    // frame of that single animation.
    public ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
    long startTime, prevTime; // Used to track elapsed time for animations and
    // fps
    int frames = 0; // Used to track frames per second
    Paint textPaint; // Used for rendering fps text
    float fps = 0; // frames per second
    Matrix m = new Matrix(); // Matrix used to translate/rotate each coin
    // during rendering
    String fpsString = "";
    String numCoinsString = "";

    /**
     * Constructor. Create objects used throughout the life of the View: the
     * Paint and the animator
     */
    public CoinView(Context context) {
        super(context);
        droid = BitmapFactory.decodeResource(getResources(), R.drawable.drop_money);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(24);

        // This listener is where the action is for the flak animations. Every
        // frame of the
        // animation, we calculate the elapsed time and update every coin's
        // position and rotation
        // according to its speed.
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                long nowTime = System.currentTimeMillis();
                float secs = (float) (nowTime - prevTime) / 100f;
                prevTime = nowTime;
                for (int i = 0; i < numCoins; ++i) {
                    Coin coin = coins.get(i);
                    coin.y += (coin.speed * secs);
                    if (coin.y > getHeight()) {
                        // If a coin falls off the bottom, send it back to the
                        // top
                        coin.y = 0 - coin.height;
                    }
                    coin.rotation = coin.rotation
                            + (coin.rotationSpeed * secs);
                }
                // Force a redraw to see the coins in their new positions and
                // orientations
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(3000);
    }

    int getNumCoins() {
        return numCoins;
    }

    private void setNumCoins(int quantity) {
        numCoins = quantity;
        numCoinsString = "numCoins: " + numCoins;
    }

    /**
     * Add the specified number of droidcoins.
     */
    public void addCoins(int quantity) {
        for (int i = 0; i < quantity; ++i) {
            coins.add(Coin.createCoin(getWidth(), droid, getContext()));
        }
        setNumCoins(numCoins + quantity);
    }

    /**
     * Subtract the specified number of droidcoins. We just take them off the
     * end of the list, leaving the others unchanged.
     */
    void subtractCoins(int quantity) {
        for (int i = 0; i < quantity; ++i) {
            int index = numCoins - i - 1;
            coins.remove(index);
        }
        setNumCoins(numCoins - quantity);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Reset list of droidcoins, then restart it with 8 coins
        coins.clear();
        numCoins = 0;
        addCoins(16);
        // Cancel animator in case it was already running
        animator.cancel();
        // Set up fps tracking and start the animation
        startTime = System.currentTimeMillis();
        prevTime = startTime;
        frames = 0;
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // For each coin: back-translate by half its size (this allows it to
        // rotate around its center),
        // rotate by its current rotation, translate by its location, then draw
        // its bitmap
        for (int i = 0; i < numCoins; ++i) {
            Coin coin = coins.get(i);
            m.setTranslate(-coin.width / 2, -coin.height / 2);
            m.postRotate(coin.rotation);
            m.postTranslate(coin.width / 2 + coin.x, coin.height / 2
                    + coin.y);
            canvas.drawBitmap(coin.bitmap, m, null);
        }
        // fps counter: count how many frames we draw and once a second
        // calculate the
        // frames per second
        ++frames;
        long nowTime = System.currentTimeMillis();
        long deltaTime = nowTime - startTime;
        if (deltaTime > 1000) {
            float secs = (float) deltaTime / 1000f;
            fps = (float) frames / secs;
            // fpsString = "fps: " + fps;
            startTime = nowTime;
            frames = 0;
        }
        // canvas.drawText(numCoinsString, getWidth() - 200, getHeight() - 50,
        // textPaint);
        // canvas.drawText(fpsString, getWidth() - 200, getHeight() - 80,
        // textPaint);
    }

    public void pause() {
        // Make sure the animator's not spinning in the background when the
        // activity is paused.
        animator.cancel();
    }

    public void resume() {
        animator.start();
    }
}
