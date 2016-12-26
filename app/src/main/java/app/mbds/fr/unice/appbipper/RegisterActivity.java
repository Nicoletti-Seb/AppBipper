package app.mbds.fr.unice.appbipper;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.RegisterTask;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText lastname;
    private EditText firstname;
    private RadioGroup radioGroup;
    private EditText phone;
    private EditText email;
    private EditText pass;
    private EditText passVerify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init listener button
        Button buttonRegister = (Button)findViewById(R.id.register_send);
        buttonRegister.setOnClickListener(this);

        //Components
        lastname = (EditText) findViewById(R.id.register_lastname);
        firstname = (EditText) findViewById(R.id.register_firstname);
        radioGroup = (RadioGroup) findViewById(R.id.register_radio_gender);
        phone = (EditText) findViewById(R.id.register_phone);
        email = (EditText) findViewById(R.id.register_email);
        pass = (EditText) findViewById(R.id.register_pass);
        passVerify = (EditText) findViewById(R.id.register_pass_verify);
    }

    @Override
    public void onClick(View v) {

        String passString = pass.getText().toString();
        String passVerifyString = pass.getText().toString();

        if( !passString.equals(passVerifyString) ){
            return;
        }

        Person person = new Person();
        person.setNom(lastname.getText().toString());
        person.setPrenom(firstname.getText().toString());
        person.setTelephone(phone.getText().toString());
        person.setEmail(email.getText().toString());
        person.setPassword(passString);
        person.setCreatedBy(person.getPrenom());

        int idRadio = radioGroup.getCheckedRadioButtonId();
        RadioButton radioGender = (RadioButton) findViewById(idRadio);
        person.setSexe(radioGender.getText().toString());

        new RegisterTask(this).execute(person);
    }

/*
    private class RegisterTask extends AsyncTask<Person, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Person... params) {
            //Verify params
            if( params.length <= 0 ){
                return null;
            }
            Person person  = params[0];

            //Post request
            Gson g = new Gson();
            String stringJson = g.toJson(person);
            boolean result = false;
            try {
                URL url = new URL(getString(R.string.url_server) + getString(R.string.url_service_person));
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);
                connection.connect();

                //Write data
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                osw.write(stringJson);
                osw.flush();
                osw.close();

                if( connection.getResponseCode() ==  HttpURLConnection.HTTP_CREATED ){
                    result = true;
                }

                //Close
                connection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return result;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            showProgressDialog(false);
            RegisterActivity.this.finish();
            Toast.makeText(RegisterActivity.this, R.string.inscription_ok, Toast.LENGTH_LONG).show();
        }


        private ProgressDialog progressDialog;
        public void showProgressDialog(boolean isVisible) {
            if (isVisible) {
                if(progressDialog==null) {
                    progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage(getResources().getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            progressDialog = null;
                        }
                    });
                    progressDialog.show();
                }
            }
            else {
                if(progressDialog!=null) {
                    progressDialog.dismiss();
                }
            }
        }


    }*/
}
