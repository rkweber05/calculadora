package com.example.calculatorfeevale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View {

    private Paint paint;
    private float startX, startY, stopX, stopY;

    public LineView(Context context) {
        super(context);
        init();
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF000000); // Cor preta
        paint.setStrokeWidth(5); // Largura da linha

        // Posição inicial e final da linha
        startX = 50;
        startY = 50;
        stopX = 300;
        stopY = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    public float getLineWidth() {
        return Math.abs(stopX - startX);
    }
}
