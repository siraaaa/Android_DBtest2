package mobile.database.dbtest02;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AllContactsActivity extends Activity {
	
	ListView lvContacts = null;
	ContactDBHelper helper;
	Cursor cursor;
	//SimpleCursorAdapter adapter;
	MyCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_contacts);
		lvContacts = (ListView)findViewById(R.id.lvContacts);

		helper = new ContactDBHelper(this);

//		  SimpleCursorAdapter 객체 생성
//        adapter = new SimpleCursorAdapter (     );

		/* 추가한 코드 */
		/* simple_list_item2는 2개의 칼럼을 가지고 올 수 있음 */
		//onCreate 처음 생성될 때 한번만 실행, activity가 실행될 때는 아직 디비를 가지고 오지 않기 때문에 이곳에 cursor를 쓰지 않음
		//onResume(화면에 보여지기 직전에 실행됨)에 써주는게 좋다. simpleCursor는 다행히 처음 생성될 때 null을 써주는 것이 가능하다.
		/*adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
				new String[] {"name", "phone"}, new int[] {android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);*/

		//custom cursorAdapter 어떻게 정의하지???
		adapter = new MyCursorAdapter(this, R.layout.listview_layout, cursor);

		lvContacts.setAdapter(adapter);

//        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//           return true;
//            }
//        });
	}

	@Override
	protected void onResume() {
		super.onResume();
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME, null);

        adapter.changeCursor(cursor); //cursor가 바뀌었으니 새로운 정보가 보여지게 된다. notify를 시켜주지 않아도 바뀐 부분만 바꿔치기(?)
		//cursorAdapter에 들어가 있는 cursor는 close 시키면 안된다. close 시키면 커서가 날라감-정보가 날라감
		//나중에 관리할 때 cursor close 시키는데 destroy 시점에 close 시킨다.
		//swapCursor() : 커서를 바꿔치기하고 close하지 않고 보존한다. 다시 swap하면 이전의 내용 다시 swap, 바뀐 내용으로 화면을 보여주다가 다시 전체 내용, 이전의 내용을 다시 보여준다.
		//ChangCursor() : 현재 어댑터에 설정되어 있는 cursor를 close한 후 매개변수 cursor로 adapter 재설정
        helper.close();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//        cursor 사용 종료
		if (cursor != null) cursor.close();
	}
	
}




