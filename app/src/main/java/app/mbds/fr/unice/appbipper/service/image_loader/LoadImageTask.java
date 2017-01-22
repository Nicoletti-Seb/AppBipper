package app.mbds.fr.unice.appbipper.service.image_loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by 53js on 29/12/2016.
 */

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    private final static String TAG = "LoadImageTask";

    private ImageView imageView;

    public LoadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];

        if(url == null){
            return null;
        }

        ImageMemoryCache cache = ImageMemoryCache.getInstance();
        synchronized (cache){
            Bitmap image = cache.get(url);

            if(image != null){
                Log.i(TAG, "Using cache ! " + url);
                return image;
            }

            Log.i(TAG, "no cache !" + url);

            try {
                InputStream in = new URL(url).openStream();
                image = BitmapFactory.decodeStream(in);
                cache.put(url, image);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

            return image;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }

}
