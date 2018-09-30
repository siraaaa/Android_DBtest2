package mobile.database.dbtest02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper {
	
	private final static String DB_NAME = "contact_db"; 
	public final static String TABLE_NAME = "contact_table";
    public final static String COL_NAME = "name";
    public final static String COL_PHONE = "phone";
    public final static String COL_CATEGORY = "category";

	public ContactDBHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " ( _id integer primary key autoincrement,"
				+ COL_NAME + " TEXT, " + COL_PHONE + " TEXT, " + COL_CATEGORY + " TEXT);");
	
//		샘플 데이터
		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '뭐여', '1299879734', '정보과학대학');");
		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '잉', '29797345', '정보과학대학');");
		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '응', '345697979', '자연과학대학');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
	}

}
