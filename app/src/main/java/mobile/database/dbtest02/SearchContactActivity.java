package mobile.database.dbtest02;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchContactActivity extends Activity {

	ContactDBHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);
		
		helper = new ContactDBHelper(this);
	}
	
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnSearchContactSave:
			SQLiteDatabase db = helper.getReadableDatabase();
			EditText etSearchName = (EditText)findViewById(R.id.etSearchName);
			String searchName = etSearchName.getText().toString();
			
			String selection = ContactDBHelper.COL_NAME + "=?";
			String[] selectionArgs = new String[]{searchName}; 
			
			Cursor cursor = db.query(ContactDBHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
			
//			String result = "";
			
			ContactDto item = new ContactDto();
			
			while(cursor.moveToNext()) {
				item.setId(cursor.getInt(0));
				item.setName(cursor.getString(1)); 
				item.setPhone(cursor.getString(2));
				item.setCategory(cursor.getString(3));
			}
			
			TextView tvSearchResult = (TextView)findViewById(R.id.tvSearchResult);
			
			tvSearchResult.setText(item.toString() + "\n");
			
			cursor.close();
			helper.close();
			
			break;
		case R.id.btnClose :
			finish();
			break;
		}
	}
	
	
	
}
