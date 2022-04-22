package jp.ac.titech.itpro.sdl.hilbert;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HilbertView extends View {

    private final Paint paint = new Paint();

    private Canvas canvas;

    private int order = 1;

    private final HilbertTurtle turtle = new HilbertTurtle((x0, y0, x1, y1) ->
            canvas.drawLine((float) x0, (float) y0, (float) x1, (float) y1, paint));

    public HilbertView(Context context) {
        this(context, null);
    }

    public HilbertView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HilbertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        int w = getWidth();
        int h = getHeight();
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(0, 0, w, h, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        int size = Math.min(w, h);
        double step = (double) size / (1 << order);
        turtle.setPos((w - size + step) / 2, (h + size - step) / 2);
        turtle.setDir(HilbertTurtle.E);
        turtle.draw(order, step, HilbertTurtle.R);
    }

    public void setOrder(int n) {
        order = n;
        invalidate();
    }
}