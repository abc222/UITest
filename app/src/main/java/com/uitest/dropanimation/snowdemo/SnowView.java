package com.uitest.dropanimation.snowdemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.uitest.R;

import java.util.Random;

public class SnowView extends View {

    Bitmap bitmap_snows[] = new Bitmap[6];
    private final Paint mPaint = new Paint();
    private static final Random RNG = new Random();
    private Coordinate[] snows = new Coordinate[10];

    int view_height = 0;
    int view_width = 0;

    public SnowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void LoadSnowImage() {
        Resources r = this.getContext().getResources();
        bitmap_snows[0] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow0))
                .getBitmap();
        bitmap_snows[1] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow1))
                .getBitmap();
        bitmap_snows[2] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow2))
                .getBitmap();
        bitmap_snows[3] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow3))
                .getBitmap();
        bitmap_snows[4] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow4))
                .getBitmap();
        bitmap_snows[5] = ((BitmapDrawable) r.getDrawable(R.drawable.drop_snow5))
                .getBitmap();
    }

    public void SetView(int height, int width) {
        view_height = height - 100;
        view_width = width - 50;

    }

    public void addRandomSnow() {
        snows[0] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[1] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[2] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[3] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[4] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[5] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[6] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[7] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[8] = new Coordinate(RNG.nextInt(view_width), 0);
        snows[9] = new Coordinate(RNG.nextInt(view_width), 0);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < 10; x += 1) {
            if (snows[x].y >= view_height) {
                snows[x].y = 0;
                addRandomSnow();
            }
            snows[x].y += 5;
            if (RNG.nextBoolean()) {
                int rng = RNG.nextInt(3);
                snows[x].x += 2 - rng;
            }
            canvas.drawBitmap(bitmap_snows[x / 6], ((float) snows[x].x),
                    ((float) snows[x].y), mPaint);
        }

    }

    private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int newX, int newY) {
            x = newX;
            y = newY;
        }

        // public boolean equals(Coordinate other) {
        // if (x == other.x && y == other.y) {
        // return true;
        // }
        // return false;
        // }

        @Override
        public String toString() {
            return "Coordinate: [" + x + "," + y + "]";
        }
    }
}
