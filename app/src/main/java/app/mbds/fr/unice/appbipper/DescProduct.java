package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.image_loader.LoadImageTask;

public class DescProduct extends Activity {

    public static final String PARAM_PRODUCT = "PARAM_PRODUCT";

    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_product);

        product = (Product)getIntent().getSerializableExtra(PARAM_PRODUCT);

        TextView title = (TextView) findViewById(R.id.txt_title_product);
        title.setText(product.getName());

        TextView desc = (TextView) findViewById(R.id.txt_desc_product);
        desc.setText(product.getDescription());

        TextView price = (TextView) findViewById(R.id.txt_price_value);
        price.setText("" + product.getPrice());

        TextView calories = (TextView) findViewById(R.id.txt_calories_value);
        calories.setText("" + product.getCalories());

        TextView type = (TextView) findViewById(R.id.txt_type_value);
        type.setText(product.getType());

        TextView discount = (TextView) findViewById(R.id.txt_discount_value);
        discount.setText("" + product.getDiscount());

        //Load image
        ImageView imageView = (ImageView) findViewById(R.id.img_image_product);
        new LoadImageTask(imageView).execute(product.getPicture());
    }
}
