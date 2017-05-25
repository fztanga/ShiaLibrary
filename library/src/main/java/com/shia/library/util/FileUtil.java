package com.shia.library.util;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hehz on 2017/4/8.
 */
public class FileUtil {

    public static String readAssertsFile(Context context, String fileName) {
        String text = "";
        try {
            // Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer, "utf-8");
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
        return text;
    }
}
