package fudi.freddy.mokitosample.camera;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class MyFileContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse
            ("content://com.example.camerademo/");
    private static final HashMap<String, String> MIME_TYPES =
            new HashMap<String, String>();

    static {
        MIME_TYPES.put(".jpg", "image/jpeg");
        MIME_TYPES.put(".jpeg", "image/jpeg");
    }

    @Override
    public boolean onCreate() {

        try {
            File mFile = new File(getContext().getFilesDir(), "newImage.jpg");
            if(!mFile.exists()) {
                mFile.createNewFile();
            }
            getContext().getContentResolver().notifyChange(CONTENT_URI, null);
            return (true);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        String path = uri.toString();

        for (String extension : MIME_TYPES.keySet()) {
            if (path.endsWith(extension)) {
                return (MIME_TYPES.get(extension));
            }
        }
        return (null);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode)
            throws FileNotFoundException {

        File f = new File(getContext().getFilesDir(), "newImage.jpg");
        if (f.exists()) {
            return (ParcelFileDescriptor.open(f,
                    ParcelFileDescriptor.MODE_READ_WRITE));
        }
        throw new FileNotFoundException(uri.getPath());
    }
}