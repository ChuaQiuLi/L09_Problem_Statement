package sg.edu.rp.c346.id20007649.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etNewSongTitle;
    EditText etNewSingers;
    EditText etNewYear;
    RadioGroup rbNewStars;
    Button btnUpdate;
    Button btnCancel;
    Button btnDelete;
    Song data;
    EditText etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNewSingers = findViewById(R.id.etNewSingers);
        etNewSongTitle = findViewById(R.id.etNewSongTitle);
        etNewYear = findViewById(R.id.etNewYear);
        rbNewStars = findViewById(R.id.rbNewStars);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(data.getId() + "");
        etID.setEnabled(false);
        etNewSongTitle.setText(data.getTitle());
        etNewSingers.setText(data.getSingers());
        etNewYear.setText(data.getYear() + "");
        int strRadio = data.getStars();

        if (strRadio == 1) {
            rbNewStars.check(R.id.rb1);
        }

        else if (strRadio == 2 ) {
            rbNewStars.check(R.id.rb2);

        }

        else if (strRadio ==3 ) {
            rbNewStars.check(R.id.rb3);

        }

        else if (strRadio ==  4 ) {
            rbNewStars.check(R.id.rb4 );

        }

        else {
            rbNewStars.check(R.id.rb5);

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitleContent(etNewSongTitle.getText().toString());
                data.setSingersContent(etNewSingers.getText().toString());

                String year = etNewYear.getText().toString().trim();
                int years = Integer.parseInt(year);
                data.setYearContent(years);

                int stars = 1;

                if (rbNewStars.getCheckedRadioButtonId() == R.id.rb1){
                    stars=1;
                }

                else if(rbNewStars.getCheckedRadioButtonId() == R.id.rb2){
                    stars=2;

                }

                else if(rbNewStars.getCheckedRadioButtonId() == R.id.rb3){
                    stars=3;

                }

                else if(rbNewStars.getCheckedRadioButtonId() == R.id.rb4){
                    stars=4;

                }

                else if(rbNewStars.getCheckedRadioButtonId() == R.id.rb5){
                    stars=5;

                }


              data.setStarsContent(stars);

                dbh.updateNote(data);
                dbh.close();
                finish();

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.getId());
                finish();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }
}