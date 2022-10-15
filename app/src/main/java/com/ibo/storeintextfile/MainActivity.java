package com.ibo.storeintextfile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writertotext(editText.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            textView.setText(readData());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writertotext (String s) throws IOException {
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("myFile.txt", Context.MODE_PRIVATE));
        outputStreamWriter.write(s);
        outputStreamWriter.close();

    }

    private String readData() throws IOException {
        String myStoredtext="";
        InputStream inputStream=openFileInput("myFile.txt");
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String tempData="";
            StringBuilder stringBuilder=new StringBuilder();
            while ((tempData=bufferedReader.readLine())!=null){
                stringBuilder.append(tempData);

            }
            inputStream.close();
            myStoredtext=stringBuilder.toString();
        }
        return myStoredtext;
    }
}