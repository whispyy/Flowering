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

import java.util.List;

public class FlowerDetail extends AppCompatActivity {

    private String name;
    private Flower flower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        name = getIntent().getExtras().getString("name");
        /* Crap function to find why it does not match the list item with the string */
        List<Flower> flowers = new FlowerDB(getApplicationContext()).getFlowers();
        for(int i = 0; i < flowers.size(); i++) {
            if ( flowers.get(i).getName().equals(name))
                flower = flowers.get(i);
        }

        /* end crap*/

        //display plant name
        TextView detail_name = (TextView) findViewById(R.id.detail_name);
        detail_name.setText("Name "+flower.getName());
        //display plant freq
        TextView detail_freq = (TextView) findViewById(R.id.detail_freq);
        detail_freq.setText("Frequency "+ flower.getFrequency());
        //display plant lastWaterDay



        Button modify = (Button) findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyShow();
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
     * Display a dialog box to update data information
     */
    public void modifyShow(){
        final EditText newName = new EditText(this);
        final EditText newFreq = new EditText(this);

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
}
