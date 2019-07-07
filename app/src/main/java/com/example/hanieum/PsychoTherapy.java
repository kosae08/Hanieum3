package com.example.hanieum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.net.ProtocolException;

public class PsychoTherapy extends AppCompatActivity {

    class Point{
        float x;
        float y;
        boolean check;
        int color;

        public Point(float x, float y, boolean check,int color)
        {
            this.x = x;
            this.y = y;
            this.check = check;
            this.color = color;
        }
    }

    class MyView extends View
    {
        public MyView(Context context) { super(context); }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setStrokeWidth(10);
            for(int i=1 ; i<points.size() ; i++)
            {
                p.setColor(points.get(i).color);
                if(!points.get(i).check)
                    continue;
                canvas.drawLine(points.get(i-1).x,points.get(i-1).y,points.get(i).x,points.get(i).y,p);
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    points.add(new Point(x,y,false , color));
                case MotionEvent.ACTION_MOVE :
                    points.add(new Point(x,y,true , color));
                    break;
                case MotionEvent.ACTION_UP :
                    break;
            }
            invalidate();
            return true;
        }
    }

    ArrayList<Point> points = new ArrayList<Point>();
    Button Draw_red_button,Draw_blue_button,Draw_black_button,Erase_button;
    LinearLayout Drawlinear;
    int color = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psycho_therapy);

        final Button bt_Send = findViewById(R.id.Send);
        bt_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.Send) {
                    //bt_Send.buildDrawingCache();
                    //Bitmap imageView = bt_Send.getDrawingCache();
                    //FileOutputStream FOS;
                    File file = new File("data/test.png"); //임의로 sdcard에 test.png로 저장
                    OutputStream outputStream = null;
                    try {
                        file.createNewFile();
                        outputStream = new FileOutputStream(file);

                        v.buildDrawingCache();
                        Bitmap bitmap = v.getDrawingCache();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),"저장했습니다", Toast.LENGTH_LONG).show();
                }
            }
        });

        final MyView m = new MyView(this);
        /* ----- 색 변경 ------ */
        findViewById(R.id.Draw_red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.RED ;
            }
        });
        findViewById(R.id.Draw_blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLUE ;
            }
        });
        findViewById(R.id.Draw_black_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLACK ;
            }
        });

        Erase_button = findViewById(R.id.Erase_button);
        Drawlinear = findViewById(R.id.Draw_linear);
        Erase_button.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v){
                points.clear();
                m.invalidate();
            }
        });
        Drawlinear.addView(m);
    }
}