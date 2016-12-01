package durand.com.flowering;

import android.content.Context;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe modélisant une plante. Le constructeur par défaut 
 * permet d'ajouter une plante en lui passant en paramêtre un nom et une fréquence d'arrosage.
 */
public class Flower {
    private int id;
    private String name;
    private int frequency;
    private Long lastWaterDay;

    /*
    Constructeur par défaut
     */
    public Flower(String name, int frequency){
        this.name = name;
        this.frequency = frequency;
        this.lastWaterDay = SettingsActivity.getToday();
    }

    /*
    Constructeur ou l'utilisateur défini tous les attributs
     */
    public Flower(int id, String name, int frequency, Long lastWaterDay){
        this.id = id;
        this.name = name;
        this.frequency = frequency;
        this.lastWaterDay = lastWaterDay;
    }



    /* Définitions des getters
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public Long getLastWaterDay() {
        return lastWaterDay;
    }

    //convertir le timestamp en string de type dd/mm/yyyy
    public String getStringLastWaterDay() {
        DateFormat stringDay = new SimpleDateFormat("dd/MM/yyyy");
        Date netDate = (new Date(lastWaterDay));
        return stringDay.format(netDate);
    }
    /* Définitions des setters
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setLastWaterDay(Long lastWaterDay) {
        this.lastWaterDay = lastWaterDay;
    }

    @Override
    public String toString() {
        return this.name+" : "+this.frequency+"d remaining";
    }
}
