package durand.com.flowering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
}
