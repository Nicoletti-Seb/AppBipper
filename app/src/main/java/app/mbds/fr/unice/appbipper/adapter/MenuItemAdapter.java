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
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.image_loader.LoadImageTask;

/**
 * Created by 53js-Seb on 28/10/2016.
 */

public class MenuItemAdapter extends BaseAdapter{

    private static final String TAG = "MenuItemAdapter";

    private List<Menu> menus;
    private Context context;

    public MenuItemAdapter(Context context, List<Menu> menus){
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuViewHolder viewHolder;
        Menu menu = menus.get(position);
        if(convertView==null){
            convertView = View.inflate(context, R.layout.menu_item_list, null);
            viewHolder = new MenuViewHolder();
            viewHolder.number= (TextView)convertView.findViewById(R.id.menu_number);
            viewHolder.server = (TextView)convertView.findViewById(R.id.menu_server);
            viewHolder.cooker = (TextView)convertView.findViewById(R.id.menu_cooker);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (MenuViewHolder) convertView.getTag();
        }

        //Values Server or Cooker can be null...
        String nameServer = (menu.getServer() != null)? menu.getServer().getFullName(): " --";
        String nameCooker = (menu.getCooker() != null)? menu.getCooker().getFullName(): " --";

        viewHolder.number.setText(menu.getId());
        viewHolder.server.setText(nameServer);
        viewHolder.cooker.setText(nameCooker);
        return convertView;
    }

    public List<Menu> getMenus(){
        return menus;
    }

    private class MenuViewHolder{
        TextView number;
        TextView server;
        TextView cooker;

    }
}
