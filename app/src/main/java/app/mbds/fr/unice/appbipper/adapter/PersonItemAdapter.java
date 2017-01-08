package app.mbds.fr.unice.appbipper.adapter;

import android.app.Application;
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
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.BuzzTask;
import app.mbds.fr.unice.appbipper.service.DeleteUserTask;

/**
 * Created by MBDS on 26/10/2016.
 */

public class PersonItemAdapter extends BaseAdapter {

    private Context context;
    private List<Person> persons;
    private int idLayoutItem;


    public PersonItemAdapter(Context context, List<Person> persons, int idLayoutItem) {
        this.context = context;
        this.persons = persons;
        this.idLayoutItem = idLayoutItem;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PersonViewHolder viewHolder = null;
        if(convertView==null){
            convertView = View.inflate(context, idLayoutItem, null);
            viewHolder = new PersonViewHolder();
            viewHolder.nom_prenom= (TextView)convertView.findViewById(R.id.txt_nom_prenom);
            viewHolder.connected = (ImageView)convertView.findViewById(R.id.isconnected);
            viewHolder.removeBtn = convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);

            initListenerBuzzBtn(viewHolder);
            initListenerRemoveBtn(viewHolder);
        }
        else{
            viewHolder = (PersonViewHolder) convertView.getTag();
        }

        updateView(viewHolder, position);

        return convertView;
    }

    private void updateView(PersonViewHolder viewHolder, int position){
        Person pers = persons.get(position);
        viewHolder.nom_prenom.setText(pers.getFullName());
        //Image button
        if(pers.isConnected()) {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_green);
        } else {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_grey);
        }

        //Update tag components
        viewHolder.connected.setTag(position);
        if( viewHolder.removeBtn != null){
            viewHolder.removeBtn.setTag(position);
        }
    }

    private void initListenerBuzzBtn(PersonViewHolder viewHolder){
        if( viewHolder.connected == null ){
            return;
        }

        viewHolder.connected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Receiver
                Integer pos = (Integer)v.getTag();
                Person receiver = persons.get(pos);
                if(!receiver.isConnected()){
                    Toast.makeText(context, R.string.buzz_user_not_connected, Toast.LENGTH_SHORT).show();
                    return;
                }

                //Sender
                Person sender = ((BipperApplication)context.getApplicationContext()).getUser();

                new BuzzTask(context).execute(receiver, sender);
            }
        });
    }

    private void initListenerRemoveBtn(PersonViewHolder viewHolder){
        if( viewHolder.removeBtn == null ){
            return;
        }

        final PersonItemAdapter p = this;
        viewHolder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer)v.getTag();
                Person pers = persons.get(pos);
                System.out.println("Click pers: " + pers);

                DeleteUserTask mAuthTask = new DeleteUserTask(p, context);
                mAuthTask.execute(pers);
            }
        });
    }

    public List<Person> getPerson() {
        return persons;
    }

    class PersonViewHolder{
        TextView nom_prenom;
        ImageView connected;
        View removeBtn;

    }
}

