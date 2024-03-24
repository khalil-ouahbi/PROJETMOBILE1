package com.example.myapplication6;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    // Définition des constantes pour la base de données
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_ANNONCE = "annonce";
    private static final String COLUMN_ANNONCE_ID = "annonce_id";
    private static final String COLUMN_TITRE = "titre";
    private static final String COLUMN_CATEGORIE = "categorie";
    private static final String COLUMN_SECTEUR = "secteur";
    private static final String COLUMN_CONTRAT = "contrat";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_VILLE = "ville";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crée les tables de la base de données lors de la première utilisation
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_ANNONCE_TABLE = "CREATE TABLE " + TABLE_ANNONCE + "("
                + COLUMN_ANNONCE_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TITRE + " TEXT,"
                + COLUMN_CATEGORIE + " TEXT,"
                + COLUMN_SECTEUR + " TEXT,"
                + COLUMN_CONTRAT + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_VILLE + " TEXT" + ")";
        db.execSQL(CREATE_ANNONCE_TABLE);
    }

    // Met à jour la base de données si nécessaire lors d'un changement de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANNONCE);
        onCreate(db);
    }

    // Méthode pour ajouter un utilisateur à la base de données
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Méthode pour vérifier l'existence d'un utilisateur dans la base de données
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{email, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Méthode pour ajouter une annonce à la base de données
    public long addAnnonce(String titre, String categorie, String secteur, String contrat,
                           String description, String ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITRE, titre);
        values.put(COLUMN_CATEGORIE, categorie);
        values.put(COLUMN_SECTEUR, secteur);
        values.put(COLUMN_CONTRAT, contrat);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_VILLE, ville);
        long result = db.insert(TABLE_ANNONCE, null, values);
        db.close();
        return result;
    }

    // Méthode pour obtenir le nombre d'annonces pour une ville spécifique
    public int getNombreAnnoncesParVille(String ville) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_ANNONCE + " WHERE " +
                COLUMN_VILLE + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{ville});
        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
    }
}
