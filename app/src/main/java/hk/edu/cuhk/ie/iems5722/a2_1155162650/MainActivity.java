package hk.edu.cuhk.ie.iems5722.a2_1155162650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

