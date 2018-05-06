package com.ietree.xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Product p = new Product();
        try {
            InputStream in = getAssets().open("result.xml");
            XmlPullParser pullParser = Xml.newPullParser();
            // 解析源
            pullParser.setInput(in, "utf-8");
            // 获得一个事件的类型
            int eventType = pullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    // 判断是否是元素的开始
                    // 获得当前解析到的元素名称
                    if ("product".equals(pullParser.getName())) {
                        // 封装数据
                        String type = pullParser.getAttributeValue(0);
                        p.setType(type);
                    } else if ("phonenum".equals(pullParser.getName())) {
                        String phonenum = pullParser.nextText();
                        p.setPhonenum(phonenum);
                    } else if ("location".equals(pullParser.getName())) {
                        String location = pullParser.nextText();
                        p.setLocation(location);
                    } else if ("phoneJx".equals(pullParser.getName())) {
                        String phoneJx = pullParser.nextText();
                        p.setPhoneJx(phoneJx);
                    }
                }
                // 手动挪动指针
                eventType = pullParser.next();
            }
            if (p != null) {
                System.out.println(p.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
