package com.example.waveletmenu;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultsTextMaker extends AppCompatActivity {
    StringBuilder builder;
    File path;
    FileOutputStream writer;
    public void InitalizeFile(String fileName) throws IOException{
        path = getApplicationContext().getFilesDir();
        writer = new FileOutputStream(new File(path, fileName));
    }
    public void WriteToFile(String menu, String test, String data, String finishTime){
        builder.append(String.format("Menu Style: $s Test#: $s ; Time to Complete: $s", menu, test, finishTime));
        builder.append("\n");
    }
    public void PublishFile(String fileName) throws IOException {
        writer.write(builder.toString().getBytes());
        writer.close();
        Toast.makeText(getApplicationContext(), "Wrote to file" + fileName, Toast.LENGTH_SHORT).show();
    }
}
