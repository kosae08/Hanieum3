package com.example.hanieum;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.v4.graphics.TypefaceCompatUtil.getTempFile;

public class PsychoTherapy extends AppCompatActivity {

    public class Cache {
        Context context;
        public Cache(Context co) {
            context = co;
        }
        public File getCachedir(Context context){
            File cacheDir = null;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheDir = new File(Environment.getExternalStorageDirectory(),"cachefolder");
                if(!cacheDir.isDirectory()) {
                    cacheDir.mkdirs();
                }
            }
            if(!cacheDir.isDirectory()) {
                cacheDir = context.getCacheDir();
            }
            return cacheDir;
        }
        public void Write(String obj) throws IOException {
            File cacheDir = getCacheDir();
            File cacheFile = new File(cacheDir, "Cache.jpg");
            if(!cacheFile.exists())cacheFile.createNewFile();
            FileWriter fileWriter = new FileWriter(cacheFile);
            fileWriter.write(obj);
            fileWriter.flush();
            fileWriter.close();
        }
    }

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

    /*private void screenshot(Bitmap bm) {
        try {
            File path = new File("/mycache");

            if(!path.isDirectory()) {
                path.mkdirs();
            }


            FileOutputStream out = new FileOutputStream(temp);

            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        } catch (FileNotFoundException e) {
            Log.d("FileNotFoundException:", e.getMessage());
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psycho_therapy);

        Button bt_Send = findViewById(R.id.Send);
        bt_Send.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PsychoTherapy";
                final LinearLayout capture = findViewById(R.id.Draw_linear);//캡쳐할영역(프레임레이아웃)
                File file = new File(path);

                if(!file.isDirectory()) file.mkdirs();

                SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                capture.buildDrawingCache();
                Bitmap CaptureView = capture.getDrawingCache();

                try{
                    FileOutputStream FOS = null;
                    FOS = new FileOutputStream(path+"/Capture"+day.format(date)+".jpeg");
                    CaptureView.compress(Bitmap.CompressFormat.JPEG, 100, FOS);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path + "/Capture" + day.format(date) + ".JPEG")));
                    FOS.flush();
                    FOS.close();
                    capture.destroyDrawingCache();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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

        //mUri를 JSP 서버 주소를 입력하면 된다.
        //file은
        /*try{
            String FileName = mUri.getLastPathSegment();
            file = File.createTempFile(FileName, null, getCacheDir());
        } catch(IOException e) {
            e.printStackTrace();
        }*/

        /*final Button bt_Send = findViewById(R.id.Send);
        bt_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.Send) {
                    //bt_Send.buildDrawingCache();
                    //Bitmap imageView = bt_Send.getDrawingCache();
                    //FileOutputStream FOS;
                    Drawlinear.setDrawingCacheEnabled(true);    // 캐쉬허용
                    // 캐쉬에서 가져온 비트맵을 복사해서 새로운 비트맵(스크린샷) 생성
                    Bitmap screenshot = Bitmap.createBitmap(Drawlinear.getDrawingCache());
                    Drawlinear.setDrawingCacheEnabled(false);   // 캐쉬닫기

                    // SDCard(ExternalStorage) : 외부저장공간
                    // 접근하려면 반드시 AndroidManifest.xml에 권한 설정을 한다.
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    // 폴더가 있는지 확인 후 없으면 새로 만들어준다.
                    if(!dir.exists())
                        dir.mkdirs();
                    FileOutputStream fos;

                    try {
                        fos = new FileOutputStream(new File(dir, "Image.png"));
                        screenshot.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                    } catch (Exception e) {
                        Log.e("phoro","그림저장오류",e);
                    }
                }
        }
    });*/