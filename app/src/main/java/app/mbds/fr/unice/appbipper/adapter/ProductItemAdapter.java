package app.mbds.fr.unice.appbipper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import app.mbds.fr.unice.appbipper.entity.Product;

/**
 * Created by 53js-Seb on 28/10/2016.
 */

public class ProductItemAdapter extends BaseAdapter{

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
        return null;
    }
}
