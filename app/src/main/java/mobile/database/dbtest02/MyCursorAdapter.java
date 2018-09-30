package mobile.database.dbtest02;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyCursorAdapter extends CursorAdapter {

    LayoutInflater inflater;
    Cursor cursor;

    public MyCursorAdapter(Context context, int layout, Cursor c) {
        super(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = R.layout.listview_layout;
        cursor = c;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //ListView 내부에 표시할 뷰에 cursor의 데이터 연결
        TextView tvContactName = (TextView)view.findViewById(R.id.tvContactName);
        TextView tvContactPhone = (TextView)view.findViewById(R.id.tvContactPhone);
        tvContactName.setText(cursor.getString(1));
        tvContactPhone.setText(cursor.getString(2));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // inflator를 사용하여 ListView 내부에 표시할 view를 inflation
        View listItemLayout = inflater.inflate(R.layout.listview_layout, parent, false);
        return listItemLayout;
    }
}
