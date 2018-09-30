package mobile.database.dbtest02;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertContactActivity extends Activity {

	ContactDBHelper helper;
	EditText etName;
	EditText etPhone;
	EditText etCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_contact);
		
		helper = new ContactDBHelper(this);
		
		etName = (EditText)findViewById(R.id.editText1);
		etPhone = (EditText)findViewById(R.id.editText2);
		etCategory = (EditText)findViewById(R.id.editText3);
	}
	
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnAddNewContact:
			SQLiteDatabase db = helper.getWritableDatabase();
			
			String name = etName.getText().toString();
			String phone = etPhone.getText().toString();
			String category = etCategory.getText().toString();
			
//			DB 메소드를 사용할 경우
			ContentValues row = new ContentValues();
			row.put(ContactDBHelper.COL_NAME, name);
			row.put(ContactDBHelper.COL_PHONE, phone);
			row.put(ContactDBHelper.COL_CATEGORY, category);
			
			db.insert(ContactDBHelper.TABLE_NAME, null, row);
			
//			SQL query를 직접 사용할 경우
//			db.execSql("insert into " + ContactDBHelper.TABLE_NAME
//									  + " values(null, '" + name + "', '" + phone +"', '" + category "');", null);
			
			helper.close();
			
			break;
		case R.id.btnAddNewContactClose:
			finish();
			break;
		}
	}
	
	
	
	
	
}
