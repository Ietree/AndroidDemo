package com.ietree.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText ed_name;
    private RadioGroup rgb;
    private ListView listView;
    private StudentDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_name = findViewById(R.id.ed_name);
        rgb = findViewById(R.id.rgb);
        listView = findViewById(R.id.lv);

        dao = new StudentDao(this);
//        Student tester = new Student("tester", ed_name, sex);
//        List<Student> students = dao.findAll();
//        listView.setAdapter(new MyAdapter());
        refreshView();
    }

    List<Student> students = new ArrayList<>();

    private void refreshView() {
        students = dao.findAll();
        listView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {
        // m:你要绑定到屏幕上的数据
        // v:ListView
        // c:Adapter

        // 每个item要显示在listView上的时候，会调用到的方法
        // position：当前的item是处于第几个位置被传递进来的
        // convertView：这个用于做优化的
        // parent：当前的父控件
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            Student student = students.get(position);
            TextView tv = new TextView(MainActivity.this);
            tv.setText(student.getName() + "，位置：" + position);

            String sex = student.getSex();
            ImageView iv = new ImageView(MainActivity.this);
            if ("gentleman".equals(sex)) {
                iv.setImageResource(R.drawable.nan);
            } else {
                iv.setImageResource(R.drawable.nv);
            }

            linearLayout.addView(tv);
            linearLayout.addView(iv, 20, 20);
            return linearLayout;
        }

        // 这个方法是最开始被调用的，用来计算到底有多少个item需要显示在ListView上
        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    public void save(View v) {

        String name = ed_name.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String sex = "gentleman";
        int id = rgb.getCheckedRadioButtonId();
        if (id == R.id.gentleman) {
            sex = "gentleman";
        } else {
            sex = "lady";
        }

        Student st = new Student(name, sex);
        dao.add(st);
        Toast.makeText(this, "保存  " + name + "成功", Toast.LENGTH_SHORT).show();
        refreshView();
    }
}
