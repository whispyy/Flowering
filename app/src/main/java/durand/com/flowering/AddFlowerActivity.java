package durand.com.flowering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * AddFlowerActivity : Permet d'ajouter une plante.
 */
public class AddFlowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameField = (EditText) findViewById(R.id.name);
                EditText frequencyField = (EditText) findViewById(R.id.frequency);

                if(nameField.getText().toString().equals(""))
                    Toast.makeText(getApplication().getBaseContext(), getString(R.string.name_empty),Toast.LENGTH_SHORT).show();
                else if (frequencyField.getText().toString().equals("") || frequencyField.getText().toString().equals("0") || frequencyField.getText().toString().equals("1"))
                    Toast.makeText(getApplication().getBaseContext(), getString(R.string.freq_empty),Toast.LENGTH_SHORT).show();
                else {
                    FlowerDB db = new FlowerDB(getApplicationContext());
                    Flower flower = new Flower(nameField.getText().toString(),Integer.parseInt(frequencyField.getText().toString()));
                    db.addFlower(flower);
                    Toast.makeText(getApplicationContext(), "New Flower created !", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK);
                    finish();

                }
            }
        });
    }


}
