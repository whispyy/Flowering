package durand.com.flowering;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by JF on 27/11/2016.
 */

/**
 * Classe modifiant l'affichage des listes notamment utilisé
 * ici pour modifier et mettre a jour les couleurs du background des items de la liste
 */
public class HomeAdapter extends ArrayAdapter {

    public HomeAdapter(Context context, int simple_list_item_1, int text1, String[] flowers){
        super(context, simple_list_item_1, text1, flowers);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        List<Flower> flowers = new FlowerDB(getContext()).getFlowers();

        Flower flower = new Flower("fake",5);;
        for(int i= 0; i < flowers.size(); i++)
            if (flowers.get(i).getName().equals(getItem(position).toString()))
                flower = flowers.get(i);
        // /Toast.makeText(getContext(), flower.getName(), Toast.LENGTH_SHORT).show();

        //ajouter le comparateur de date (86400000 = 24*60*60*1000) et définir la couleur
        Long today = SettingsActivity.getToday();
        Long nextWatering = flower.getLastWaterDay()+(flower.getFrequency()*86400000);
        if ((nextWatering == today) || (nextWatering == today+86400000))
            v.setBackgroundColor(Color.YELLOW);
        else if (nextWatering < today)
            v.setBackgroundColor(Color.RED);
        else
            v.setBackgroundColor(Color.GREEN);
        return v;
    }
}
