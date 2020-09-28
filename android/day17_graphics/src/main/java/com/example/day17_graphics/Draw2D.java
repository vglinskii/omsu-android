package com.example.day17_graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Draw2D  extends View {

    private Paint currentPaint = new Paint();
    private Rect rect = new Rect();
    private Bitmap houseBitmap;
    private boolean firstTime = true;

    public Draw2D(Context context) {
        super(context);
        houseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.house);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(firstTime) {
            houseBitmap = Bitmap.createScaledBitmap(houseBitmap, getWidth() / 2, getHeight() / 2, false);
            firstTime = false;
        }
        currentPaint.setStyle(Paint.Style.FILL);
        currentPaint.setColor(Color.BLUE);
        canvas.drawPaint(currentPaint);
        drawCircle(canvas, 0, 0, 500, Color.YELLOW);
        float x = 500 * 0.7f;
        float y = x;
        currentPaint.setColor(Color.YELLOW);
        currentPaint.setTextSize(48);
        String beam = "Лучик солнца!";
        canvas.save();
        canvas.rotate(45, x, y);
        currentPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(beam, x, y, currentPaint);
        canvas.restore();
        drawRect(canvas, 0, getHeight() - 700, getWidth(), getHeight(), Color.GREEN);
        currentPaint.setColor(Color.DKGRAY);
        canvas.drawBitmap(houseBitmap, getWidth() / 2f, getHeight() / 2f - 350, currentPaint);
    }

    private void drawCircle(Canvas canvas, int x, int y, int radius, int color) {
        currentPaint.setColor(color);
        currentPaint.setAntiAlias(true);
        canvas.drawCircle(x, y, radius, currentPaint);
        currentPaint.setAntiAlias(false);
    }

    private void drawRect(Canvas canvas, int left, int top, int right, int bottom, int color) {
        currentPaint.setColor(color);
        canvas.drawRect(left, top, right, bottom, currentPaint);
    }
}
