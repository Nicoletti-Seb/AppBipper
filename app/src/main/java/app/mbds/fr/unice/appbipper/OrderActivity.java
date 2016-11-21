package app.mbds.fr.unice.appbipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class OrderActivity extends AppCompatActivity {

    private ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle(R.string.order_title);
        orderList = (ListView)findViewById(R.id.menu_order_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO: init views
    }
}
