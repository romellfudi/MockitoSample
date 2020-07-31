package fudi.freddy.mokitosample.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fudi.freddy.mokitosample.model.Contact;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2/26/17
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 2;
    public static String DATABASE_PATH = Environment
            .getExternalStorageDirectory().toString();
    public static final String DATABASE_NAME = "DB_CUSTOMERS";
    public static final String DATABASE_DIRECTORY = "FUDI_CHINA";
    private static final String DATABASE_EXTENSION = ".sql";
    private static final String TABLE_CONTACT = "CONTACT";


    private static final String CONTACT_COMPANY = "COMPANY";
    private static final String CONTACT_COMPANY_DIM = "50";
    private static final String CONTACT_NAME = "NAME";
    private static final String CONTACT_NAME_DIM = "20";
    private static final String CONTACT_PHONE = "PHONE";
    private static final String CONTACT_PHONE_DIM = "15";
    private static final String CONTACT_MAIL = "MAIL";
    private static final String CONTACT_MAIL_DIM = "50";
    private static final String CONTACT_APP = "APP";
    private static final String CONTACT_APP_DIM = "50";
    private static final String CONTACT_REQUEST_EXTENSION = "REQUEST_EXTENSION";
    private static final String CONTACT_REQUEST_EXTENSION_DIM = "1";
    private static final String CONTACT_REQUEST_ESTIMATE = "REQUEST_ESTIMATE";
    private static final String CONTACT_REQUEST_ESTIMATE_DIM = "1";
    private static final String CONTACT_JOB_POSITION = "JOB_POSITION";
    private static final String CONTACT_JOB_POSITION_DIM = "50";
    private static final String CONTACT_COMMENTS = "COMMENTS";
    private static final String CONTACT_COMMENTS_DIM = "150";

    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_CONTACT
            + "(" + CONTACT_COMPANY + " VARCHAR(" + CONTACT_COMPANY_DIM + "),"
            + CONTACT_NAME + " VARCHAR(" + CONTACT_NAME_DIM + "),"
            + CONTACT_PHONE + " VARCHAR(" + CONTACT_PHONE_DIM + "),"
            + CONTACT_MAIL + " VARCHAR(" + CONTACT_MAIL_DIM + "),"
            + CONTACT_APP + " VARCHAR(" + CONTACT_APP_DIM + "),"
            + CONTACT_REQUEST_EXTENSION + " VARCHAR(" + CONTACT_REQUEST_EXTENSION_DIM + "),"
            + CONTACT_REQUEST_ESTIMATE + " VARCHAR(" + CONTACT_REQUEST_ESTIMATE_DIM + "),"
            + CONTACT_COMMENTS + " VARCHAR(" + CONTACT_COMMENTS_DIM + "),"
            + CONTACT_JOB_POSITION + " VARCHAR(" + CONTACT_JOB_POSITION_DIM + "))";


    private static DatabaseHelper instance;

    public static DatabaseHelper open(Context context, String name, int version) {
        if (instance == null)
            instance = new DatabaseHelper(context, name, version);
        return instance;
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    public DatabaseHelper(Context context, String name, int version) {
        super(context, name + DATABASE_EXTENSION, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(sqLiteDatabase);
    }

    public long createContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        //    company, name, mail, celphone, app, requestExtension
//    ,requestEstimate, jobPosition;
        ContentValues values = new ContentValues();
        values.put(CONTACT_COMPANY, contact.getCompany());
        values.put(CONTACT_NAME, contact.getName());
        values.put(CONTACT_PHONE, contact.getCell());
        values.put(CONTACT_MAIL, contact.getEmail());
        values.put(CONTACT_APP, contact.getApplication());
        values.put(CONTACT_REQUEST_EXTENSION, contact.isFl_app());
        values.put(CONTACT_REQUEST_ESTIMATE, contact.isFl_cot());
        values.put(CONTACT_COMMENTS, contact.getComments());
        values.put(CONTACT_JOB_POSITION, contact.getPosition());
        // insert row
        long id = db.insert(TABLE_CONTACT, null, values);
        return id;
    }

    public Contact getContact(String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE "
                + CONTACT_MAIL + " = '" + mail + "'";
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            if (c.moveToFirst()) {
                return new Contact(
                        c.getString(c.getColumnIndex(CONTACT_COMPANY)),
                        c.getString(c.getColumnIndex(CONTACT_NAME)),
                        c.getString(c.getColumnIndex(CONTACT_MAIL)),
                        c.getString(c.getColumnIndex(CONTACT_PHONE)),
                        c.getString(c.getColumnIndex(CONTACT_APP)),
                        c.getString(c.getColumnIndex(CONTACT_JOB_POSITION)),
                        (c.getInt(c.getColumnIndex(CONTACT_REQUEST_EXTENSION)) == 1),
                        (c.getInt(c.getColumnIndex(CONTACT_REQUEST_ESTIMATE)) == 1),
                        c.getString(c.getColumnIndex(CONTACT_COMMENTS)));
            }
        return null;
    }


    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Contact contact = new Contact(
                        c.getString(c.getColumnIndex(CONTACT_COMPANY)),
                        c.getString(c.getColumnIndex(CONTACT_NAME)),
                        c.getString(c.getColumnIndex(CONTACT_MAIL)),
                        c.getString(c.getColumnIndex(CONTACT_PHONE)),
                        c.getString(c.getColumnIndex(CONTACT_APP)),
                        c.getString(c.getColumnIndex(CONTACT_JOB_POSITION)),
                        (c.getInt(c.getColumnIndex(CONTACT_REQUEST_EXTENSION)) == 1),
                        (c.getInt(c.getColumnIndex(CONTACT_REQUEST_ESTIMATE)) == 1),
                        c.getString(c.getColumnIndex(CONTACT_COMMENTS)));

                contacts.add(contact);
            } while (c.moveToNext());
        }
        return contacts;
    }

    public boolean existContact(String mail) {
        boolean b = false;
        String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE " + CONTACT_MAIL + " = '" + mail + "'";
        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            b = true;
        }
        return b;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CONTACT_APP, contact.getApplication());
        values.put(CONTACT_REQUEST_EXTENSION, contact.isFl_app());
        values.put(CONTACT_REQUEST_ESTIMATE, contact.isFl_cot());
        values.put(CONTACT_COMMENTS, contact.getComments());

        return db.update(TABLE_CONTACT, values, CONTACT_MAIL + " =? "
                , new String[]{contact.getEmail()});

    }
}
