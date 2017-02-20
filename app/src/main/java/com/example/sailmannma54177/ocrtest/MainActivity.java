package com.example.sailmannma54177.ocrtest;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/*
Copyright 2011 Robert Theis

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

public class MainActivity extends AppCompatActivity {

    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";
    int img;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = 1;

        final Map<String, Integer> map = new HashMap<>();
        map.put("img1", R.drawable.isochron1);
        map.put("img2", R.drawable.isochron2);
        map.put("img3", R.drawable.isochron3);
        map.put("img4", R.drawable.isochron4);
        map.put("img5", R.drawable.isochron5);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron1);
        mTess = new TessBaseAPI();
        datapath = getFilesDir() + "/tesseract/";
        checkFile(new File(datapath + "tessdata/"));

        String language = "eng";
        Log.d("Datapath", datapath);
        mTess.init(datapath, language);

        findViewById(R.id.OCRbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTess.setImage(image);
                String OCResult = mTess.getUTF8Text();
                TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
                OCRTextView.setText(OCResult);
            }
        });

        findViewById(R.id.buttonchange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img == 1) {
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron1);
                    imageView.setImageResource(map.get("img1"));
                    Log.d("BitmapMetrics","Height = " + image.getHeight());
                    Log.d("BitmapMetrics","Width = " + image.getWidth());

                    img = 2;
                } else if (img == 2) {
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron2);
                    imageView.setImageResource(map.get("img2"));
                    Log.d("BitmapMetrics","Height = " + image.getHeight());
                    Log.d("BitmapMetrics","Width = " + image.getWidth());

                    img = 3;
                }else if (img == 3) {
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron3);
                    imageView.setImageResource(map.get("img3"));
                    Log.d("BitmapMetrics","Height = " + image.getHeight());
                    Log.d("BitmapMetrics","Width = " + image.getWidth());

                    img = 4;
                }else if (img == 4) {
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron4);
                    imageView.setImageResource(map.get("img4"));
                    Log.d("BitmapMetrics","Height = " + image.getHeight());
                    Log.d("BitmapMetrics","Width = " + image.getWidth());

                    img = 5;
                }else if (img == 5) {
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.isochron5);
                    imageView.setImageResource(map.get("img5"));
                    Log.d("BitmapMetrics","Height = " + image.getHeight());
                    Log.d("BitmapMetrics","Width = " + image.getWidth());

                    img = 1;
                }
                pixelTest();
            }
        });
        pixelTest();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void copyFile() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outputStream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(File dir) {

        if (!dir.exists() && dir.mkdirs()) {
            copyFile();
        }
        if (dir.exists()) {
            String datafilepath = datapath + "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFile();
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private void pixelTest() {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        int width = bitmap.getHeight();
        int height = bitmap.getWidth();
        int counter = 0;

        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        for (int i = 0; i < width; i++) {
            for (int n = 0; n < height; n++) {
                int pixel = bitmap.getPixel(n, i);
                int red = Color.red(pixel);
                int blue = Color.blue(pixel);
                int green = Color.green(pixel);

                if(red<100 && blue < 100 && green < 100)
                {
                    //Log.d("Pixel no "+ counter, "isBlack == True");
                    mutableBitmap.setPixel(n,i,Color.BLACK);
                }
                else
                {
                    mutableBitmap.setPixel(n,i,Color.WHITE);
                }

                counter ++;

            }

            imageView.setImageBitmap(mutableBitmap);

        }
    }
}
