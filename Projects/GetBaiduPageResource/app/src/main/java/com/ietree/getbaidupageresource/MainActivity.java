package com.ietree.getbaidupageresource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ietree.getbaidupageresource.utils.StreamUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText ed_path;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_path = findViewById(R.id.ed_path);
        tv = findViewById(R.id.tv);
    }

    String path = "";

    public void getPageResource() {
        path = ed_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "输入的路径有问题，请重新输入正确的访问路径", Toast.LENGTH_SHORT);
            return;
        }

        new Thread() {
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
                    conn.setConnectTimeout(5000);
                    String type = conn.getContentType();
                    int code = conn.getResponseCode();
                    if (200 == code) {
                        InputStream in = conn.getInputStream();
                        String s = StreamUtil.decodeStream(in);
                        
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
