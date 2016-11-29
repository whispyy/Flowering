package durand.com.flowering;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    private static Long today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (today == null)
            setToday();

        //affichage de la date d'aujourd'hui
        TextView currentDate = (TextView) findViewById(R.id.currentDate);
        DateFormat stringToday = new SimpleDateFormat("dd/MM/yyyy");
        Date netDate = (new Date(today));
        currentDate.setText(stringToday.format(netDate));

        //modifier la date d'aujourd'hui
        Button modifyDate = (Button) findViewById(R.id.modifyDate);
        modifyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyDateShow();
            }
        });

    }

    public void modifyDateShow(){
        final DatePicker newDate = new DatePicker(this);



        new AlertDialog.Builder(this)
                .setTitle("Modify")
                .setMessage("Date :")
                .setView(newDate)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int y = newDate.getYear();
                        int m = newDate.getMonth();
                        int d = newDate.getDayOfMonth();
                        Calendar c = Calendar.getInstance();
                        c.set(y,m,d);
                        Long newDateTimeStamp = c.getTimeInMillis();
                        setToday(newDateTimeStamp);
                        //update affichage
                        TextView currentDate = (TextView) findViewById(R.id.currentDate);
                        DateFormat stringToday = new SimpleDateFormat("dd/MM/yyyy");
                        Date netDate = (new Date(today));
                        currentDate.setText(stringToday.format(netDate));
                        Toast.makeText(SettingsActivity.this, "Date changed !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    public static void setToday(){
        today = (System.currentTimeMillis());
    }

    public void setToday(Long date){
        today = date;
    }

    public static Long getToday(){
        if (today == null)
            setToday();
        return today;
    }

    /*public void importFixtures(){
        List<Flower> flowerList;
        for (int i = 0; i < 10; i++)
            flowerList.add(new Flower("Plant"+i,2));
    }*/
}
