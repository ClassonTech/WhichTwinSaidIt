package net.classon.www.whichtwinsaidit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteBox;
    private String[] quoteList;
    private int whichView;
    private int whichLayout;
    private int lastQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteList = getResources().getStringArray(R.array.raw_quote);
        quoteBox = (TextView) findViewById(R.id.quoteBox);
        Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/indieflower.ttf");
        quoteBox.setTypeface(myFont);
        newQ();
    }

    public void clickedMe(View view){
        int i = view.getId();
        boolean bool=false;
        if(i == whichView || whichView==0) {
            bool = true;
        }
            myDialog(bool); //custom message, makes new question
    }

    public void newQ(){
        Random rng = new Random();
        int i = rng.nextInt(quoteList.length);
        while(lastQ == i){
            i = rng.nextInt(quoteList.length);
        }
        lastQ = i;
        String kish = quoteList[lastQ].substring(0,1);
        String ore = quoteList[lastQ].substring(1);

        quoteBox.setText(ore);
        if(kish.equals("e")){
            whichView = R.id.ebutton;
            whichLayout = R.layout.alertlayoute;
        } else if(kish.equals("c")){
            whichView = R.id.cbutton;
            whichLayout = R.layout.alertlayoutc;
        } else {
            whichView = 0;
            whichLayout = R.layout.alertlayoutb;
        }
    }

    public void myDialog(boolean bool) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(whichLayout, null));
        if(bool){
            if(whichView==0){
                builder.setMessage(R.string.bothcorrect);
            } else {
                builder.setMessage(R.string.correct);
            }
        } else {
            builder.setMessage(R.string.incorrect);
        }
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                newQ();
            }
        });

        builder.show();

    }

}
