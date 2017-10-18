package fr.wcs.galaxian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText textViewName, textViewPassword, textViewScore;
    TextView textViewResultBestPlayer, textViewResultScore;
    Button buttonCreateAccount, buttonBestPlayer;
    String nameBestPlayer, nameContent, passwordContent;
    int scoreBestPlayer, scoreContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = (EditText) findViewById(R.id.textViewName);
        textViewPassword = (EditText) findViewById(R.id.textViewPassword);
        textViewScore = (EditText) findViewById(R.id.textViewScore);
        textViewResultBestPlayer = (TextView) findViewById(R.id.textViewResultBestPlayer);
        textViewResultScore = (TextView) findViewById(R.id.textViewResultScore);
        buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        buttonBestPlayer = (Button) findViewById(R.id.buttonBestPlayer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameContent = textViewName.getText().toString();
                passwordContent = textViewPassword.getText().toString();
                scoreContent = Integer.parseInt(textViewScore.getText().toString());

                User user = new User(nameContent, passwordContent, scoreContent);

                myRef.push().setValue(user);

            }
        });

        buttonBestPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.orderByChild("user_score").limitToLast(1).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                            User user = dsp.getValue(User.class);

                            nameBestPlayer = user.getUser_name();
                            scoreBestPlayer = user.getUser_score();

                            textViewResultBestPlayer.setText(nameBestPlayer);
                            textViewResultScore.setText(String.valueOf(scoreBestPlayer));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }
        });
    }
}

