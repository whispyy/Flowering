package durand.com.flowering;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        v.setBackgroundColor(Color.YELLOW);
        return v;
    }
}
