package hk.edu.cuhk.ie.iems5722.a2_1155162650;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);


        new HttpTask() {

            @Override
            public void success() {
                JSONObject json = super.getResponse();
                System.out.println(json.toString());
                try {
                    JSONArray jsonArray = json.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        list.add(name);
                    }
                    listView = findViewById(R.id.chatrooms);
                    listView.setAdapter(new ArrayAdapter<String>(context, R.layout.activity_list_view, R.id.testView, list));

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Object item = listView.getAdapter().getItem(i);
                            Intent it = new Intent(context, ChatActivity.class);

                            startActivity(it);

                            //Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed() {

            }
        }.execute("http://18.217.125.61/api/a3/get_chatrooms");
//        listView = findViewById(R.id.chatrooms);
//        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.testView, list));

    }


}

