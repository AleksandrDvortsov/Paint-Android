package com.example.sasha.paint;

import android.content.ClipData;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private DrawCanvasView drawCanvasView;
    Button DellAll;
    Button curvas, rectange, cirlec, ellipce, line;
    Button size10, size20, size40;
    Button idColorRed, idColorGreen, idColorBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }


    public void init()
    {

        drawCanvasView = findViewById(R.id.canvas);
        DellAll = findViewById(R.id.cheakid);
        curvas = findViewById(R.id.idCurvas);
        rectange = findViewById(R.id.idRectangel);
        cirlec = findViewById(R.id.idCircle);
        ellipce = findViewById(R.id.idEllipse);
        line = findViewById(R.id.idLine);
        size10 = findViewById(R.id.idSize10);
        size20 = findViewById(R.id.idSize20);
        size40 = findViewById(R.id.idSize40);
        idColorRed = findViewById(R.id.idColorRed);
        idColorGreen = findViewById(R.id.idColorGreen);
        idColorBlue = findViewById(R.id.idColorBlue);

        curvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.TypeF(true, false, false, false, false);
            }
        });

        rectange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.TypeF(false, true, false,false,false);
            }
        });

        cirlec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.TypeF(false,false,true,false,false);
            }
        });

        ellipce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.TypeF(false,false,false,true,false);
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.TypeF(false,false, false, false, true);
            }
        });


        size10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setWidth(10);
            }
        });
        size20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setWidth(20);
            }
        });
        size40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setWidth(40);
            }
        });
        idColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setPenColor(Color.RED);
            }
        });
        idColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setPenColor(Color.GREEN);
            }
        });
        idColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCanvasView.setPenColor(Color.BLUE);
            }
        });
    }

    public void clear(View v)
    {
        drawCanvasView.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.width10:
                drawCanvasView.setWidth(10);
                break;
            case R.id.width20:
                drawCanvasView.setWidth(20);
                break;
            case R.id.width40:
                drawCanvasView.setWidth(40);
                break;
            case R.id.clear:
                drawCanvasView.clear();
                break;
            case R.id.ColorRed:
                drawCanvasView.setPenColor(Color.RED);
                break;
            case R.id.ColorGreen:
                drawCanvasView.setPenColor(Color.GREEN);
                break;
            case R.id.ColorBlue:
                drawCanvasView.setPenColor(Color.BLUE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
