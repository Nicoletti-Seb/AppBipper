package app.mbds.fr.unice.appbipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button buttonRegister = (Button)findViewById(R.id.register_send);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //TODO: Save Person
        finish();
    }
}
