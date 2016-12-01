package durand.com.flowering;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * FlowerDetail : Permet d'afficher le détail d'une plante par un clic depuis la liste de l'activité Home.
 * Il est également possible de modifier le nom d'une plante en cliquant sur "Modify" ou de supprimer
 * celle-ci en cliquant sur "Delete"
 */
public class FlowerDetail extends AppCompatActivity {

    private String name;
    private Flower flower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        name = getIntent().getExtras().getString("name");
        List<Flower> flowers = new FlowerDB(getApplicationContext()).getFlowers();
        for(int i = 0; i < flowers.size(); i++) {
            if ( flowers.get(i).getName().equals(name))
                flower = flowers.get(i);
        }

        //display plant name
        TextView detail_name = (TextView) findViewById(R.id.detail_name);
        detail_name.setText("Name "+flower.getName());
        //display plant freq
        TextView detail_freq = (TextView) findViewById(R.id.detail_freq);
        detail_freq.setText("Frequency "+ flower.getFrequency());
        //display plant lastWaterDay
        TextView detail_lastD = (TextView) findViewById(R.id.detail_lastD);
        detail_lastD.setText("Last Watering "+flower.getStringLastWaterDay());


        Button modify = (Button) findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyShow();
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShow();
            }
        });
    }

    /**
     *  Update detail_name textView
     */
    public void updateTextView(String toThis) {
        TextView textView = (TextView) findViewById(R.id.detail_name);
        textView.setText(toThis);
        return;
    }

    /**
     * Display a dialog box to update the plant's name
     */
    public void modifyShow(){
        final EditText newName = new EditText(this);

        newName.setText(flower.getName());

        new AlertDialog.Builder(this)
                .setTitle("Modify")
                .setMessage("Name :")
                .setView(newName)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name2 = newName.getText().toString();
                        flower.setName(name2);
                        new FlowerDB(getApplicationContext()).updateFlower(flower);
                        Toast.makeText(getApplicationContext(), "New name : "+flower.getName(), Toast.LENGTH_SHORT).show();
                        updateTextView("Name "+name2);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    /**
     * Display a dialog box to delete the plant
     */
    public void deleteShow() {

        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete this plant ?")
                //.setView(newName)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new FlowerDB(getApplicationContext()).deletePlant(flower.getId());
                        Toast.makeText(getApplicationContext(), flower.getName()+", removed!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }
}
