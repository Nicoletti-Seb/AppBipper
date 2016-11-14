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
    public Object getItem(int arg0) {
        return person.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        PersonViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.element_layout, null);
            viewHolder = new PersonViewHolder();
            viewHolder.nom_prenom= (TextView)v.findViewById(R.id.txt_nom_prenom);
            viewHolder.connected = (ImageView)v.findViewById(R.id.isconnected);
            v.setTag(viewHolder);

            ImageButton ib = (ImageButton)v.findViewById(R.id.delete_btn);

            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person pers = person.get(position);
                    System.out.println("Click pers: " + pers);

                    DeleteUserTask mAuthTask = new DeleteUserTask(pers);
                    mAuthTask.execute((Void) null);
                }
            });
        }
        else{
            viewHolder = (PersonViewHolder) v.getTag();
        }

        Person pers = person.get(position);
        viewHolder.nom_prenom.setText(pers.getFullName());
        if(pers.isConnected()) {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_green);
        } else {
            viewHolder.connected.setImageResource(R.drawable.circle_icon_grey);
        }
        return v;
    }

    class PersonViewHolder{
        TextView nom_prenom;
        ImageView connected;
    }

    public class DeleteUserTask extends AsyncTask<Object, Object, String> {

        private Person personne;

        DeleteUserTask(Person personne) {
            this.personne = personne;
        }
        @Override
        protected String doInBackground(Object... params) {

            //Delete request
            try {
                URL url = new URL(context.getString(R.string.url_server) + context.getString(R.string.url_service_person) + personne.getId());
                System.out.println("url delete: " + url);
                
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.connect();

                String result = "" + connection.getResponseCode();
                System.out.println("Response :"+result);

                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }            
        }

        @Override
        protected void onPostExecute(final String success) {
            System.out.println("success :"+success);

            if(success.equals("200")) {
                person.remove(personne);
                PersonItemAdapter.this.notifyDataSetChanged();
            }

        }
    }
}

