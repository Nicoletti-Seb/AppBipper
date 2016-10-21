package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listener button
        Button register = (Button)findViewById(R.id.register);
        Button login = (Button)findViewById(R.id.login);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;

            case R.id.login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
    }
}
