package com.example.user.devtimetrack;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String displayPreview;
    String strdate;
    String strnoa;
    String strnoh;
    String strtsm;
    String strqa;
    EditText Edate;
    EditText Enoa;
    EditText Enoh;
    EditText Etsm;
    EditText Eqa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Edate = findViewById(R.id.date);
        Enoa = findViewById(R.id.noa);
        Enoh = findViewById(R.id.noh);
        Etsm = findViewById(R.id.tsm);
        Eqa = findViewById(R.id.qa);
    }

    private void displayMessage(String a) {
        TextView displayPreviewA = findViewById(R.id.preview);
        displayPreviewA.setText(a);

    }

    private void openWhatsApp() {
        String url = "  "; // contains spaces.
        url = url.replace("+", "").replace(" ", "");
        /*Uri uri=Uri.parse("https://chat.whatsapp.com/DnKIBZfQ4G1AOMt7WdzCdU");*/
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra("jid", url + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, displayPreview);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void calc() {
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        strdate = Edate.getText().toString();
        strnoa = Enoa.getText().toString();
        strnoh = Enoh.getText().toString();
        strtsm = Etsm.getText().toString();
        strqa = Eqa.getText().toString();
        displayPreview = "Date:" + date + "\n1.Number of Aiveos worked on=" + strnoa +
                "\n2.Number of hours logged into Aiveo=" + strnoh +
                "\n3.Time spent in Meeting/Discussions/Helping=" + strtsm +
                "\n4.Number of Hours spent in Development/QA=" + strqa;
    }

    public void whatsapp(View view) {
        calc();
        displayMessage(displayPreview);
        openWhatsApp();
    }

    public void email(View view) {
        calc();
        Intent mailClient = new Intent(Intent.ACTION_SENDTO);
        mailClient.setData(Uri.parse("mailto:"));
        mailClient.putExtra(Intent.EXTRA_EMAIL, "aditya.maheswari@vawsum.com \"trakkerzdev@googlegroups.com\" <trakkerzdev@googlegroups.com>");
        mailClient.putExtra(Intent.EXTRA_SUBJECT, "Today's Time Track");
        mailClient.putExtra(Intent.EXTRA_TEXT, displayPreview);
        if (mailClient.resolveActivity(getPackageManager()) != null) {
            startActivity(mailClient);
            displayMessage(displayPreview);
        }
    }
}



