package durand.com.flowering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JF on 26/11/2016.
 */

/**
 * FlowerDB : class permettant de créer et de communiquer avec la base de donnée.
 */
public class FlowerDB extends SQLiteOpenHelper {

    private SQLiteDatabase bd;
    private String TABLE_NAME = "flower";

    /**
     * Constructeur de la base de donnée
     * @param ctx : le contexte
     */
    public FlowerDB(Context ctx) {
        super(ctx, "flowers.bd", null, 1);
        bd = getWritableDatabase();
    }

    /**
     * Constructeur de la base de donnée
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public FlowerDB(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /* Création de la table : flower
     * idFlower : l'id principal
     * name : nom de la fleur/plante
     * frequency : frequence d'arrosage
     * lastWaterDay : Dernier jour d'arrosage
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + "idFlower INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL," + "frequency INTEGER NOT NULL," + "lastWaterDay LONG NOT NULL);");
    }

    /* Mise a jour
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int ancienneVersion, int nouvelleVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME+ ";");
        onCreate(db);
    }


    public void close() {
        bd.close();
    }

    /* Ajout des valeurs nom, frequence et date dernier arrosage dans la table
     */
    public long addFlower(Flower flower) {
        ContentValues valeurs = new ContentValues();

        valeurs.put("name", flower.getName());
        valeurs.put("frequency", flower.getFrequency());
        valeurs.put("lastWaterDay", flower.getLastWaterDay());
        return bd.insert(TABLE_NAME, null, valeurs);
    }

    /* Mise à jour des valeurs nom, frequence et date dernier arrosage dans la table
     */
    public int updateFlower(Flower flower) {
        ContentValues valeurs = new ContentValues();

        valeurs.put("name", flower.getName());
        valeurs.put("frequency", flower.getFrequency());
        valeurs.put("lastWaterDay", flower.getLastWaterDay());
        return bd.update(TABLE_NAME, valeurs, "idFlower = " + flower.getId(), null);
    }

    /* Suppresion d'une plante dans la table
     */
    public int deletePlant(int idFlower) {
        return bd.delete(TABLE_NAME, "idFlower = " + idFlower, null);
    }

    /* Récuperer une plante en fonction de son id
     */
    public Flower getFlower(int idFlower) {
        // Requête pour récuperer cette plante, où idPlant est égal au int passé en paramètre
        Cursor cursor = bd.query(TABLE_NAME, null, "idFlower = " + idFlower, null, null, null, null);

        if (cursor.getCount() == 0)
            return null;
        // Un seul enregistrement - id unique
        cursor.moveToFirst();
        return cursorToFlower(cursor);
    }

    /*
    Récupérer une plante en fonction de son nom
     */
    public Flower getFlowerByName(String name){
        Cursor cursor = bd.query(TABLE_NAME, new String[]{"idFlower", "name", "frequency", "lastWaterDay"}, "name LIKE \" " + name + "\"", null, null,null, null);
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();
        return cursorToFlower(cursor);
    }

    // Récuperer les plantes de la base dans une ArrayList
    public ArrayList<Flower> getFlowers() {

        ArrayList<Flower> liste = new ArrayList<Flower>();

        // Requête triée par nom
        Cursor cursor = bd.query("flower", null, null, null, null, null, "name");
        if (cursor.getCount() == 0)
            return liste;
        // Ajout des plantes à la liste dans l'ordre alphabétique
        cursor.moveToFirst();
        do {
            liste.add(cursorToFlower(cursor));
        } while (cursor.moveToNext());
        cursor.close();
        return liste;
    }

    /* Récupérations des infos des plantes
     * Modifications des attributs
     */
    private Flower cursorToFlower(Cursor cursor) {

        Flower flower = new Flower(
                (cursor.getInt(0)),
                (cursor.getString(1)),
                (cursor.getInt(2)),
                (cursor.getLong(3)));
        return flower;
    }
}
