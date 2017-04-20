package com.dce.puja.imagedownload;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;




public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class MyThread extends Thread { // java thread ....
        Bitmap image = null;

        String u;

        public void downloadImageFromServer(View v) { // here  download image...

            String image1 = "http://www.queness.com/resources/images/png/apple_ex.png";
            String image2 = "http://www.wpclipart.com/weather/sun/sun_hot/bright_sun.png";

            ImageView view1 = (ImageView) findViewById(R.id.imageView);

            ImageView view2 = (ImageView) findViewById(R.id.imageView2);

            MyThread img1 = new MyThread(image1, view1);
            img1.start();//i will start asyn in worker thread...


            MyThread img2 = new MyThread(image2, view2);
            img2.start();//i will start asyn in worker thread...
        }


        public MyThread(String url, ImageView imageView) {
            this.u = url;
            this.imageView = imageView;
        }

        @Override
        public void run() { // ill worker
            super.run();
            try {
                URL url = new URL(u);
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream steam = connection.getInputStream();//image u getting from server
                image = BitmapFactory.decodeStream(steam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() { // main thread...
                    imageView.setImageBitmap(image);

                }
            });
        }
    }
}

