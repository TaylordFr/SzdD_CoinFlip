package com.example.coinflip;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button fejbtn;
    private Button irasbtn;
    private ImageView eredmenyimg;
    private TextView dobasoktxt;
    private TextView gyozelemtxt;
    private TextView veresegtxt;
    private Random random;
    private int dobasokdb;
    private int gyozelemdb;
    private int veresegdb;
    private int valasz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        fejbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dobasokdb < 5){
                    if(valasz == 0){
                        eredmenyimg.setImageResource(R.drawable.heads);
                        Toast.makeText(MainActivity.this, "Győzelem!", Toast.LENGTH_SHORT).show();
                        gyozelemdb++;
                        gyozelemtxt.setText("Győzelem: " + gyozelemdb);
                        dobasokdb++;
                        dobasoktxt.setText("Dobások: " + dobasokdb);
                        random = new Random();
                        valasz = random.nextInt(2);
                    } else if(valasz == 1){
                        eredmenyimg.setImageResource(R.drawable.tails);
                        Toast.makeText(MainActivity.this, "Vereség!", Toast.LENGTH_SHORT).show();
                        veresegdb++;
                        veresegtxt.setText("Vereség: " + veresegdb);
                        dobasokdb++;
                        dobasoktxt.setText("Dobások: " + dobasokdb);
                        random = new Random();
                        valasz = random.nextInt(2);
                    }
                } else {
                        showJatekVege();
                }

            }
        });

        irasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dobasokdb < 5){
                    if(valasz == 1){
                        Toast.makeText(MainActivity.this, "Győzelem!", Toast.LENGTH_SHORT).show();
                        gyozelemdb++;
                        gyozelemtxt.setText("Győzelem: " + gyozelemdb);
                        dobasokdb++;
                        dobasoktxt.setText("Dobások: " + dobasokdb);
                        random = new Random();
                        valasz = random.nextInt(2);
                    } else if(valasz == 0){
                        Toast.makeText(MainActivity.this, "Vereség!", Toast.LENGTH_SHORT).show();
                        veresegdb++;
                        veresegtxt.setText("Vereség: " + veresegdb);
                        dobasokdb++;
                        dobasoktxt.setText("Dobások: " + dobasokdb);
                        random = new Random();
                        valasz = random.nextInt(2);
                    }
                } else {
                    showJatekVege();
                }

            }
        });

    }

    public void showJatekVege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(gyozelemdb > veresegdb){
            builder.setTitle("Győzelem!");
        } else if(gyozelemdb < veresegdb){
            builder.setTitle("Vereség!");
        } else {
            builder.setTitle("Döntetlen!");
        }

        builder.setMessage("Szeretnéd újra játszani?");

        builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });

        builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void init(){
        eredmenyimg = findViewById(R.id.imageViewEredmenyimg);
        eredmenyimg.setImageResource(R.drawable.heads);
        fejbtn = findViewById(R.id.buttonFej);
        irasbtn = findViewById(R.id.buttonIras);
        dobasoktxt = findViewById(R.id.textViewDobasok);
        dobasoktxt.setText("Dobások: 0");
        gyozelemtxt = findViewById(R.id.textViewGyozelem);
        gyozelemtxt.setText("Győzelem: 0");
        veresegtxt = findViewById(R.id.textViewVereseg);
        veresegtxt.setText("Vereség: 0");
        random = new Random();
        valasz = random.nextInt(2);
        gyozelemdb = 0;
        veresegdb = 0;
        dobasokdb = 0;
    }
}