package app.mbds.fr.unice.appbipper.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.mbds.fr.unice.appbipper.LoginActivity;
import app.mbds.fr.unice.appbipper.MenuActivity;
import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.DeleteUserTask;

/**
 * Created by MBDS on 26/10/2016.
 */

public class PersonItemAdapter extends BaseAdapter {

    private Context context;
    public List<Person> person;

    public PersonItemAdapter(Context context, List<Person> person) {
        this.context = context;
        this.person = person;
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int position) {
        return person.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PersonViewHolder viewHolder = null;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.element_layout, null);
            viewHolder = new PersonViewHolder();
            viewHolder.nom_prenom= (TextView)convertView.findViewById(R.id.txt_nom_prenom);
            viewHolder.connected = (ImageView)convertView.findViewById(R.id.isconnected);
            viewHolder.removeBtn = (ImageButton)convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);

            //Listener delete
            final PersonItemAdapter p = this;
            viewHolder.removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer)v.getTag();
                    Person pers = person.get(pos);
                    System.out.println("Click pers: " + pers);

                    DeleteUserTask mAuthTask = new DeleteUserTask(pers, p, context);
                    mAuthTask.execute((Void) null);
                }
            });
        }
        else{
            viewHolder = (PersonViewHolder) convertView.getTag();
        }

        Person pers = person.get(position);
        viewHolder.removeBtn.setTag(position);
        viewHolder.nom_prenom.setText(pers.getFullName());
        if(pers.isConnected()) {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_green);
        } else {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_grey);
        }
        return convertView;
    }

    class PersonViewHolder{
        TextView nom_prenom;
        ImageView connected;
        ImageButton removeBtn;

    }
}

