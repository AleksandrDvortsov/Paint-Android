package com.example.sasha.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawCanvasView extends View
{
    Bitmap bitmapp;
    Canvas canvas;
    ArrayList<Path> mPaths;
    ArrayList<Paint> mPaints;
    Paint paintt;
    Path pathh;
    final int DefaultColor = Color.BLUE;
    final int DefaultWidthPen = 40;
    int PeintColor;
    int WidthPen;
    boolean Type = false;
    int xz1;
    float X1, Y1;
    Context context;

    public DrawCanvasView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        PeintColor = DefaultColor;
        WidthPen = DefaultWidthPen;

        mPaths = new ArrayList<Path>();
        mPaints = new ArrayList<Paint>();
        pathh = new Path();
        addNewPathAndPain();
        X1 = Y1 = (float)0.0;
    }

    private void addNewPathAndPain()
    {
        pathh = new Path();
        paintt = new Paint();
        mPaths.add(pathh);
        mPaints.add(paintt);

        paintt.setColor(PeintColor);
        paintt.setStyle(Paint.Style.STROKE);
        paintt.setStrokeWidth(WidthPen);
    }

    public void setPenColor ( int idColor )
    {
        this.PeintColor = idColor;
    }

    public void setWidth ( int width )
    {
        WidthPen = width;
    }

    public void TypeF ( boolean Curvec, boolean Rectangle, boolean Cirles, boolean Ellipse, boolean Line )
    {
        if ( Curvec == true )
        {
            Type = Curvec;
            xz1 = 1;
        }
        else if ( Rectangle == true )
        {
            Type = Rectangle;
            xz1 = 2;
        }
        else if ( Cirles == true )
        {
            Type = Cirles;
            xz1 = 3;
        }
        else if ( Ellipse == true )
        {
            Type = Ellipse;
            xz1 = 4;
        }
        else if ( Line == true )
        {
            Type = Line;
            xz1 = 5;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmapp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmapp);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for (int i = 0; i < mPaths.size(); ++i )
        {
            canvas.drawPath(mPaths.get(i), mPaints.get(i));
        }
    }

    private void clickInTauch (float x, float y, int xz)
    {
        pathh.moveTo(x,y);
        X1 = x;
        Y1 = y;
        this.xz1 = xz;
        if ( xz == 4 )
        {
            paintt.setStyle(Paint.Style.FILL_AND_STROKE);
            pathh.addCircle(X1,Y1,100, Path.Direction.CW);
//            invalidate();
        }
    }

    private void moveTauch ( float x, float y )
    {
        X1 = x;
        Y1 = y;

        if ( xz1 == 1 )
        {
            paintt.setStyle(Paint.Style.STROKE);
            pathh.quadTo(X1,Y1,(x+X1)/2, (y+Y1)/2);
        }

    }

    private void upTouch(float x, float y, int xz)
    {
        this.xz1 = xz;

        X1 = x;
        Y1 = y;
        if ( xz1 == 5 )
        {
            paintt.setStyle(Paint.Style.STROKE);
            pathh.lineTo(X1, Y1);
        }
    }

       @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                addNewPathAndPain();
                if ( xz1 == 1 || xz1 == 3 || xz1 == 4 || xz1 == 5 )
                clickInTauch(x,y,xz1);
                else if ( xz1 == 2 )
                {
                    this.pathh.moveTo(X1,Y1);
                }
                X1 = x;
                Y1 = y;
                break;

            case MotionEvent.ACTION_MOVE:
                if ( xz1 == 1 )
                moveTauch(x,y);
                else
                {
                    break;
                }
                break;
            case MotionEvent.ACTION_UP:
                if ( xz1 == 5 )
                upTouch(x,y,xz1);
                if ( xz1 == 2 )
                this.pathh.addRect(X1, Y1, x, y, Path.Direction.CW);

                break;
        }
        invalidate();
        return true;
    }

    public void clear()
    {
        init();
        invalidate();
    }
}
