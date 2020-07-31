package fudi.freddy.mokitosample.store

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import android.util.Log
import fudi.freddy.mokitosample.model.Contact
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class DatabaseHelper(context: Context?, name: String, version: Int) : SQLiteOpenHelper(context, name + DATABASE_EXTENSION, null, version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACT")
        onCreate(sqLiteDatabase)
    }

    fun createContact(contact: Contact?): Long {
        val db = this.writableDatabase

        //    company, name, mail, celphone, app, requestExtension   ,requestEstimate, jobPosition;
        val values = ContentValues()
        values.put(CONTACT_COMPANY, contact?.company)
        values.put(CONTACT_NAME, contact?.name)
        values.put(CONTACT_PHONE, contact?.cell)
        values.put(CONTACT_MAIL, contact?.email)
        values.put(CONTACT_APP, contact?.application)
        values.put(CONTACT_REQUEST_EXTENSION, contact?.isFl_app)
        values.put(CONTACT_REQUEST_ESTIMATE, contact?.isFl_cot)
        values.put(CONTACT_COMMENTS, contact?.comments)
        values.put(CONTACT_JOB_POSITION, contact?.position)
        // insert row
        return db.insert(TABLE_CONTACT, null, values)
    }

    fun getContact(mail: String): Contact? {
        val db = this.writableDatabase
        val selectQuery = ("SELECT * FROM " + TABLE_CONTACT + " WHERE "
                + CONTACT_MAIL + " = '" + mail + "'")
        Log.e(LOG, selectQuery)
        val c = db.rawQuery(selectQuery, null)
        if (c != null) if (c.moveToFirst()) {
            return Contact(
                    c.getString(c.getColumnIndex(CONTACT_COMPANY)),
                    c.getString(c.getColumnIndex(CONTACT_NAME)),
                    c.getString(c.getColumnIndex(CONTACT_MAIL)),
                    c.getString(c.getColumnIndex(CONTACT_PHONE)),
                    c.getString(c.getColumnIndex(CONTACT_APP)),
                    c.getString(c.getColumnIndex(CONTACT_JOB_POSITION)),
                    c.getInt(c.getColumnIndex(CONTACT_REQUEST_EXTENSION)) == 1,
                    c.getInt(c.getColumnIndex(CONTACT_REQUEST_ESTIMATE)) == 1,
                    c.getString(c.getColumnIndex(CONTACT_COMMENTS)))
        }
        return null
    }

    val allContact: List<Contact>
        get() {
            val contacts: MutableList<Contact> = ArrayList()
            val selectQuery = "SELECT * FROM $TABLE_CONTACT"
            Log.e(LOG, selectQuery)
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    val contact = Contact(
                            c.getString(c.getColumnIndex(CONTACT_COMPANY)),
                            c.getString(c.getColumnIndex(CONTACT_NAME)),
                            c.getString(c.getColumnIndex(CONTACT_MAIL)),
                            c.getString(c.getColumnIndex(CONTACT_PHONE)),
                            c.getString(c.getColumnIndex(CONTACT_APP)),
                            c.getString(c.getColumnIndex(CONTACT_JOB_POSITION)),
                            c.getInt(c.getColumnIndex(CONTACT_REQUEST_EXTENSION)) == 1,
                            c.getInt(c.getColumnIndex(CONTACT_REQUEST_ESTIMATE)) == 1,
                            c.getString(c.getColumnIndex(CONTACT_COMMENTS)))
                    contacts.add(contact)
                } while (c.moveToNext())
            }
            return contacts
        }

    fun existContact(mail: String): Boolean {
        var b = false
        val selectQuery = "SELECT * FROM $TABLE_CONTACT WHERE $CONTACT_MAIL = '$mail'"
        Log.e(LOG, selectQuery)
        val db = this.readableDatabase
        val c = db.rawQuery(selectQuery, null)
        if (c.moveToFirst()) {
            b = true
        }
        return b
    }

    fun updateContact(contact: Contact): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(CONTACT_APP, contact.application)
        values.put(CONTACT_REQUEST_EXTENSION, contact.isFl_app)
        values.put(CONTACT_REQUEST_ESTIMATE, contact.isFl_cot)
        values.put(CONTACT_COMMENTS, contact.comments)
        return db.update(TABLE_CONTACT, values, "$CONTACT_MAIL =? "
                , arrayOf(contact.email))
    }

    companion object {
        private const val LOG = "DatabaseHelper"
        const val DATABASE_VERSION = 2
        var DATABASE_PATH = Environment
                .getExternalStorageDirectory().toString()
        const val DATABASE_NAME = "DB_CUSTOMERS"
        const val DATABASE_DIRECTORY = "FUDI_CHINA"
        private const val DATABASE_EXTENSION = ".sql"
        private const val TABLE_CONTACT = "CONTACT"
        private const val CONTACT_COMPANY = "COMPANY"
        private const val CONTACT_COMPANY_DIM = "50"
        private const val CONTACT_NAME = "NAME"
        private const val CONTACT_NAME_DIM = "20"
        private const val CONTACT_PHONE = "PHONE"
        private const val CONTACT_PHONE_DIM = "15"
        private const val CONTACT_MAIL = "MAIL"
        private const val CONTACT_MAIL_DIM = "50"
        private const val CONTACT_APP = "APP"
        private const val CONTACT_APP_DIM = "50"
        private const val CONTACT_REQUEST_EXTENSION = "REQUEST_EXTENSION"
        private const val CONTACT_REQUEST_EXTENSION_DIM = "1"
        private const val CONTACT_REQUEST_ESTIMATE = "REQUEST_ESTIMATE"
        private const val CONTACT_REQUEST_ESTIMATE_DIM = "1"
        private const val CONTACT_JOB_POSITION = "JOB_POSITION"
        private const val CONTACT_JOB_POSITION_DIM = "50"
        private const val CONTACT_COMMENTS = "COMMENTS"
        private const val CONTACT_COMMENTS_DIM = "150"
        private const val CREATE_TABLE_CONTACT = ("CREATE TABLE " + TABLE_CONTACT
                + "(" + CONTACT_COMPANY + " VARCHAR(" + CONTACT_COMPANY_DIM + "),"
                + CONTACT_NAME + " VARCHAR(" + CONTACT_NAME_DIM + "),"
                + CONTACT_PHONE + " VARCHAR(" + CONTACT_PHONE_DIM + "),"
                + CONTACT_MAIL + " VARCHAR(" + CONTACT_MAIL_DIM + "),"
                + CONTACT_APP + " VARCHAR(" + CONTACT_APP_DIM + "),"
                + CONTACT_REQUEST_EXTENSION + " VARCHAR(" + CONTACT_REQUEST_EXTENSION_DIM + "),"
                + CONTACT_REQUEST_ESTIMATE + " VARCHAR(" + CONTACT_REQUEST_ESTIMATE_DIM + "),"
                + CONTACT_COMMENTS + " VARCHAR(" + CONTACT_COMMENTS_DIM + "),"
                + CONTACT_JOB_POSITION + " VARCHAR(" + CONTACT_JOB_POSITION_DIM + "))")
        var instance: DatabaseHelper? = null
            private set

        fun open(context: Context?, name: String, version: Int): DatabaseHelper? {
            if (instance == null) instance = DatabaseHelper(context, name, version)
            return instance
        }

    }
}