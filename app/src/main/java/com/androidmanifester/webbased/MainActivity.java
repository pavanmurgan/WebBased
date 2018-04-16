package com.androidmanifester.webbased;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView1,textView2;
    Button btnget;
    String name1,name2;
    String jsonstr;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        btnget=findViewById(R.id.btnget);

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///textView1.setText("a");
                //textView2.setText("b");

                Sapient sapient = new Sapient();
                sapient.execute();

            }
        });
    }


    public class Sapient extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar=new ProgressDialog(MainActivity.this);
            progressBar.setMessage("Loading.. please wait..");
            progressBar.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            HttpHandler httpHandler = new HttpHandler();
            jsonstr = httpHandler.makeServiceCall("https://api.androidhive.info/contacts/");

            try {
                JSONObject jsonObject = new JSONObject(jsonstr);

                JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                JSONObject jsonObject1 = jsonArray.getJSONObject(2);

                name1=jsonObject1.getString("name");

                JSONObject jsonObject2 = jsonArray.getJSONObject(11);

                name2=jsonObject2.getString("name");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressBar.dismiss();
            textView1.setText(name1);
            textView2.setText(name2);
        }
    }
}










