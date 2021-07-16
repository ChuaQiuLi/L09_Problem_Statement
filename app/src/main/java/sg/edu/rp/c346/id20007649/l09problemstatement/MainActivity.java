package sg.edu.rp.c346.id20007649.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle;
    EditText etSingers;
    EditText etYears;
    RadioGroup rbStars;
    Button btnInsert;
    Button btnShowList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etSongTitle = findViewById(R.id.etSingers);
        etSingers = findViewById(R.id.etSingers);
        etYears = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        rbStars = findViewById(R.id.rbStars);





        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String songTitle = etSongTitle.getText().toString();
                String singer = etSingers.getText().toString();
                String year = etYears.getText().toString().trim();
                int years = Integer.valueOf(year);
                int stars = getStars();


                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertSong(songTitle,singer,years,stars);
                dbh.close();
                Toast.makeText(MainActivity.this,"Song Inserted",Toast.LENGTH_LONG);

                etSongTitle.setText("");
                etSingers.setText("");
                etYears.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ShowActivity.class);

                startActivity(intent);

            }
        });

    }

    private int getStars(){
        int stars = 1;

        if (rbStars.getCheckedRadioButtonId() == R.id.rb1){
            stars=1;
        }

        else if(rbStars.getCheckedRadioButtonId() == R.id.rb2){
            stars=2;

        }

        else if(rbStars.getCheckedRadioButtonId() == R.id.rb3){
            stars=3;

        }

        else if(rbStars.getCheckedRadioButtonId() == R.id.rb4){
            stars=4;

        }

        else if(rbStars.getCheckedRadioButtonId() == R.id.rb5){
            stars=5;

        }

        return stars;
    }


}