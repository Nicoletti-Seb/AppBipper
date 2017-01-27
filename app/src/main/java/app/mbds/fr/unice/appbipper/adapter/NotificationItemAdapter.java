package app.mbds.fr.unice.appbipper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.mbds.fr.unice.appbipper.BipperApplication;
import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Notification;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.BuzzTask;
import app.mbds.fr.unice.appbipper.service.DeleteUserTask;

/**
 * Created by MBDS on 26/10/2016.
 */

public class NotificationItemAdapter extends BaseAdapter {

    private Context context;
    private List<Notification> notifications;

    public NotificationItemAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        NotificationViewHolder viewHolder = null;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.notification_item_list, null);
            viewHolder = new NotificationViewHolder();
            viewHolder.sender= (TextView)convertView.findViewById(R.id.notification_sender);
            viewHolder.receiver = (TextView)convertView.findViewById(R.id.notification_receiver);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (NotificationViewHolder) convertView.getTag();
        }

        Notification notification = notifications.get(position);
        viewHolder.sender.setText(notification.getSender().getFullName());
        viewHolder.receiver.setText(notification.getReceiver().getFullName());

        return convertView;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    class NotificationViewHolder{
        TextView sender;
        TextView receiver;
    }
}

