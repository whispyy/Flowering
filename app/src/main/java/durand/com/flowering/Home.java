package durand.com.flowering;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ListView flv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ListView
        flv = (ListView) findViewById(R.id.flowersList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, AddFlowerActivity.class));
            }
        });
        refreshList();
    }

    @Override
    protected void onPostResume() {
        refreshList();
        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * En fonction du résultat précédemment retourné met à jour la liste des plantes
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
            refreshList();
    }

    public void refreshList() {
        FlowerDB db = new FlowerDB(getApplicationContext());
        List<Flower> flowers = db.getFlowers();
        String[] flowersName = new String[flowers.size()];
        for (int i = 0; i < flowers.size();i++)
            flowersName[i] = flowers.get(i).getName();
        //flowers.toArray(flowersName);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, flowersName);
        flv.setAdapter(adapter);

        //Le clic simple renvoie vers le détail de la plante
        flv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.GRAY);
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText() , Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("name",((TextView) view).getText().toString());
                Intent mIntent = new Intent(Home.this, FlowerDetail.class).putExtras(bundle);
                startActivity(mIntent);
            }
        });

        //Le clic long reset le temps avant le prochain arrosage
        flv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.GREEN);
                //update lastWaterDay in DB
                return true;
            }
        });

    }
}
