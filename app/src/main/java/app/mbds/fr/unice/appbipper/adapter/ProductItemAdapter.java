package app.mbds.fr.unice.appbipper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.mbds.fr.unice.appbipper.DescProduct;
import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.image_loader.LoadImageTask;

/**
 * Created by 53js-Seb on 28/10/2016.
 */

public class ProductItemAdapter extends BaseAdapter{

    private static final String TAG = "ProductItemAdapter";

    private List<Product> products;
    private Context context;

    public ProductItemAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductViewHolder viewHolder;
        Product product = products.get(position);
        if(convertView==null){
            convertView = View.inflate(context, R.layout.product_item_list, null);
            viewHolder = new ProductViewHolder();
            viewHolder.title= (TextView)convertView.findViewById(R.id.product_el_title);
            viewHolder.descriptionBtn = (ImageButton)convertView.findViewById(R.id.product_el_button_see_product);
            viewHolder.image = (ImageView)convertView.findViewById(R.id.product_el_picture);
            convertView.setTag(viewHolder);

            new LoadImageTask(viewHolder.image).execute(product.getPicture());
            initListener(viewHolder);
        }
        else{
            viewHolder = (ProductViewHolder) convertView.getTag();
        }

        viewHolder.descriptionBtn.setTag(position);
        viewHolder.title.setText(product.getName());
        return convertView;
    }


    private void initListener(ProductViewHolder viewHolder){
        viewHolder.descriptionBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Product product = products.get((int)v.getTag());
                Intent i = new Intent(context, DescProduct.class);
                i.putExtra(DescProduct.PARAM_PRODUCT, product);
                context.startActivity(i);
            }
        });
    }

    public List<Product> getProducts(){
        return products;
    }

    private class ProductViewHolder{
        TextView title;
        ImageView image;
        ImageButton descriptionBtn;

    }
}
