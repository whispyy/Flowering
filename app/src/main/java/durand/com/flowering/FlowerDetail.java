package durand.com.flowering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FlowerDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        String name = getIntent().getExtras().getString("name");

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(name);
    }
}
