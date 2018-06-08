package com.example.shinji_win.listviewviewholder;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ListView mMyListView;
    // ユーザーデータ配列
    ArrayList<User> mUsers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyListView = (ListView) findViewById(R.id.myListView);

        // データを作成
        createUser();

        // アダプターにユーザー情報をセット
        UserAdapter adapter = new UserAdapter(this, 0, mUsers);

        // ListViewに表示
        mMyListView.setAdapter(adapter);

        // リストビューのクリックイベントを取得
        mMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DEBUG_DATA", "onItemClick");

                String msg = position + "番目のアイテムがクリックされました";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createUser(){
        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] names = {
                "azunobu",
                "azuki",
                "kanon"
        };

        String[] comments = {
                "僕の夏休みは9月から始まる予定です。",
                "…。",
                "ｷｬﾝｷｬﾝ!!!ﾜﾝﾜﾝ!!!"
        };

        for (int i = 0; i < icons.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setName(names[i]);
            user.setComment(comments[i]);
            mUsers.add(user);
        }
    }

    public class UserAdapter extends ArrayAdapter<User> {

        private LayoutInflater layoutInflater;
        public UserAdapter(Context c, int id, ArrayList<User> users) {
            super(c, id, users);
            this.layoutInflater = (LayoutInflater) c.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(
                        R.layout.list_item,
                        parent,
                        false
                );

                // ビューホルダーを作成して、データを取得
                ViewHolder vh = new ViewHolder();
                vh.iconImage = convertView.findViewById(R.id.icon);
                vh.nameText = convertView.findViewById(R.id.name);
                vh.commentText = convertView.findViewById(R.id.comment);

                convertView.setTag(vh);
            }

            User user = getItem(position);
            ViewHolder vh = (ViewHolder) convertView.getTag();

            vh.iconImage.setImageBitmap(user.getIcon());
            vh.nameText.setText(user.getName());
            vh.commentText.setText(user.getComment());

            return convertView;
        }
    }

    private static class ViewHolder {
        private ImageView iconImage;
        private TextView nameText;
        private TextView commentText;
    }
}
