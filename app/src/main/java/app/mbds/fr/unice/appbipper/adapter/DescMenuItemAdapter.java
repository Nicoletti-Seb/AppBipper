package app.mbds.fr.unice.appbipper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.image_loader.LoadImageTask;

/**
 * Created by 53js-Seb on 28/10/2016.
 */

public class DescMenuItemAdapter extends BaseAdapter{

    private static final String TAG = "DescMenuItemAdapter";

    private List<Product> products;
    private Context context;

    public DescMenuItemAdapter(Context context, List<Product> products){
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
        MenuViewHolder viewHolder;
        Product product = products.get(position);
        if(convertView==null){
            convertView = View.inflate(context, R.layout.desc_menu_item_list, null);
            viewHolder = new MenuViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.product_el_picture);
            viewHolder.title = (TextView)convertView.findViewById(R.id.product_el_title);
            viewHolder.price = (TextView)convertView.findViewById(R.id.product_price);
            convertView.setTag(viewHolder);

            new LoadImageTask(viewHolder.image).execute(product.getPicture());
        }
        else{
            viewHolder = (MenuViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(product.getName());
        viewHolder.price.setText(String.valueOf(product.getPrice()));
        return convertView;
    }

    public List<Product> getProducts(){
        return products;
    }

    private class MenuViewHolder{
        ImageView image;
        TextView title;
        TextView price;

    }
}
