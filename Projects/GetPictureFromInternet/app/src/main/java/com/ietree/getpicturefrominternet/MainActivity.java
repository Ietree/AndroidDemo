package com.ietree.getpicturefrominternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText ed_path;
    private ImageView iv;
    private static final int SUCCESS = 0;
    private static final int NETWORK_ERROR = 1;
    private static final int ERROR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_path = findViewById(R.id.ed_path);
        iv = findViewById(R.id.iv);
    }

    //这就是小秘
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    //取出消息中的数据
                    Bitmap bitmap = (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    break;
                case NETWORK_ERROR:
                    // 通知UI，给用户一个友好提示
                    Toast.makeText(MainActivity.this, "链接错误...", Toast.LENGTH_SHORT);
                    break;
                case ERROR:
                    // 通知UI，给用户一个友好提示
                    Toast.makeText(MainActivity.this, "请求数据失败", Toast.LENGTH_SHORT);
                    break;
                default:
                    return;
            }
        }
    };

    public void getPicture(View v) {
        // 获取输入的地址
        final String path = ed_path.getText().toString().trim();

        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "输入的地址为空，请重新输入有效地址", Toast.LENGTH_SHORT);
            return;
        }
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(path);
                    // https://www.baidu.com/img/bd_logo1.png --由于这里 使用的 是 http协议去 获得连接, 所以获得的是
                    // HttpURLConnection 的一个 实例
                    //建立一个 连接 --- Connection 对象
                    //android 中,谷歌提供好的 用来连接网络的 api
                    // 这些 api  你看上去 就是 跟你小时候学的是一模一样的, 但是 方法中具体的实现细节 是与之前的  你学习的jdk 中的api 是不一样的.
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //连接网络的时候, 有很多 不确定性...
                    // 为了提供用户的感受, 会 设置一个 连接 超时的 时间
                    conn.setConnectTimeout(5000);
                    //设置请求的方式
                    conn.setRequestMethod("GET");
                    //拿到 返回的数据的类型
                    String contentType = conn.getContentType();
                    //数据的长度
                    int length = conn.getContentLength();
                    System.out.println("contentType :" + contentType);
                    System.out.println("length :" + length);
                    // Tengine/2.0.2
//    				conn.getHeaderField("Server");
                    // 获得 服务器 返回的 状态吗 , 根据 状态码 去判断 是否 成功
                    int code = conn.getResponseCode();
                    // 200 ,  404 ,500, 302, 304 ..
                    if (code == 200) {
                        //进来 则表示 成功的处理的 请求, 返回了 数据
                        // 获得返回的图片的 流数据
                        InputStream in = conn.getInputStream();
                        //如果去解析呢 ???  --如何解析成一个 图片显示
                        // 这个事儿经常要做 ,谷歌 工程师 已经帮咱们提供好了现成 的类, 可以将一个 流数据转换为 一个图片
                        //这已经是图片了
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
//    					iv.setImageBitmap(bitmap);
                        //子线程中通过小秘 去发一个消息 出去
                        // 这里内部会判断Message是否创建过，如果创建过，就获得原有的。然后去更改原有的msg的信息，从而这个msg又有了新的值，避免重复创建msg对象，节省了内存的开销
                        Message msg = Message.obtain();
//                        Message msg = new Message();
                        msg.what = SUCCESS;
                        msg.obj = bitmap;
                        //放到 了 消息 队列 , messageQuerue中,  有 循环器looper 去 取出消息 ,
                        // 然后 再通知 小秘 去处理一下
                        mHandler.sendMessage(msg);
                        in.close();
                    } else {
                        Message msg = Message.obtain();
                        msg.what = ERROR;
                        mHandler.sendMessage(msg);
                    }
                    //这里要连接网络, 会 耗费用户的 money, 所以需要去申请连接网络的权限
                } catch (Exception e) {
                    // 通知UI，给用户一个友好提示
//                    Toast.makeText(MainActivity.this, "链接错误...", Toast.LENGTH_SHORT);
                    Message msg = Message.obtain();
                    msg.what = NETWORK_ERROR;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
        // 连接网络的api  URL类
    }
}
