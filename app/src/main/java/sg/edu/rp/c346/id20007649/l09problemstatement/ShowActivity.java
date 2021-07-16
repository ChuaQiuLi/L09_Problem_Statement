package sg.edu.rp.c346.id20007649.l09problemstatement;

import
        androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFiveStars;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        btnFiveStars = findViewById(R.id.btnFiveStars);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al = dbh.getAllSongs();
        dbh.close();

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);



        btnFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(ShowActivity.this);

                al.clear();
                al.addAll(dbh.getAllSong());
                aa.notifyDataSetChanged();
            }
        });





    }


}