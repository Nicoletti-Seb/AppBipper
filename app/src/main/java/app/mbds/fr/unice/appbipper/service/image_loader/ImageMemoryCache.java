package app.mbds.fr.unice.appbipper.service.image_loader;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by 53js-Seb on 29/12/2016.
 */

//https://developer.android.com/training/displaying-bitmaps/cache-bitmap.html
public class ImageMemoryCache extends LruCache<String, Bitmap> {

    private static final String TAG = "ImageMemoryCache";

    private static final int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);

    private static final int SIZE_MEMORY = MAX_MEMORY / 8;

    private static ImageMemoryCache self;

    //Singleton
    private ImageMemoryCache(){
         super(SIZE_MEMORY);
    }

    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount() / 1024;
    }

    public static ImageMemoryCache getInstance(){
        if(self == null){
            self = new ImageMemoryCache();
        }

        return self;
    }

}
