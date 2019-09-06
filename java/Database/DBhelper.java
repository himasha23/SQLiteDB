package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "userdata.db";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_create_table = "CREATE TABLE " + UserMaster.User.TABLE_NAME + " (" +
                UserMaster.User._ID + " INTEGER PRIMARY KEY," +
                UserMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.User.COLUMN_NAME_PASSWORD + " TEXT);";

        db.execSQL(sql_create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(String userName, String password) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_USERNAME, userName);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD, password);

        long result = db.insert(UserMaster.User.TABLE_NAME, null, values);


        if (result > 0) {
            //if data inserted succsessfully
            return true;
        } else {
            //if data does not insert
            return false;
        }
    }

    public List readAllInfor() {

        SQLiteDatabase DB = getReadableDatabase();

        String[] projection = {UserMaster.User._ID, UserMaster.User.COLUMN_NAME_USERNAME, UserMaster.User.COLUMN_NAME_PASSWORD};
        String sortOrder = UserMaster.User.COLUMN_NAME_PASSWORD + " DESC";

        Cursor values = DB.query(UserMaster.User.TABLE_NAME, projection,
                null, null, null, null, sortOrder);


        List userNameList = new ArrayList();
        List passwordList = new ArrayList();

        while (values.moveToNext()) {

            String userName = values.getString(values.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_USERNAME));
            String password = values.getString(values.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_PASSWORD));

            userNameList.add(userName);
            passwordList.add(password);
        }

        return userNameList;

    }

    public int deleteData(String userName) {

        SQLiteDatabase db = getReadableDatabase();

        String whereCondition = UserMaster.User.COLUMN_NAME_USERNAME + " = ?";

        String[] args = {userName};

        int result = db.delete(UserMaster.User.TABLE_NAME, whereCondition, args);

        if (result > 0) {

            return 1;


        } else

            return -1;

    }

    public boolean updatePassword(String uname, String pwd) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD, pwd);

        String whereCondition = UserMaster.User.COLUMN_NAME_USERNAME + "=? ";

        String[] args = {uname};

        int result = db.update(UserMaster.User.TABLE_NAME, values, whereCondition, args);

        if(result > 0){

            return true;


        }else {

            return false;
        }


    }

}
