package com.ietree.getbaidupageresource.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Root on 2018/5/8.
 */
public class StreamUtil {
    public static String decodeStream(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf)) > 0) {
            baos.write(buf, 0, len);
        }
        String s = baos.toString();
        return s;
    }
}
