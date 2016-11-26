package durand.com.flowering;

import android.content.Context;

/**
 * Created by JF on 26/11/2016.
 */

public class Flower {
    private int id;
    private String name;
    private int frequency;
    private int lastWaterDay;

    /*
    Constructeur par défaut
     */
    public Flower(String name, int frequency){
        this.name = name;
        this.frequency = frequency;
        this.lastWaterDay = 0;
    }

    /*
    Constructeur ou l'utilisateur défini tous les attributs
     */
    public Flower(int id, String name, int frequency, int lastWaterDay){
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

    public int getLastWaterDay() {
        return lastWaterDay;
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

    public void setLastWaterDay(int lastWaterDay) {
        this.lastWaterDay = lastWaterDay;
    }

    @Override
    public String toString() {
        return this.name+" : "+this.frequency+"d remaining";
    }
}
