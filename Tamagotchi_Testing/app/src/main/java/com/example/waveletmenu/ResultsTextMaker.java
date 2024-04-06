package com.example.waveletmenu;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultsTextMaker extends AppCompatActivity {
    private StringBuilder builder;
    private File path;
    private String filename;
    private FileOutputStream writer;
    public ResultsTextMaker(String name, File path) {
        this.filename = name;
        InitalizeFile(name, path);
    }

    public void InitalizeFile(String fileName, File path){
        this.filename += ".txt";
        builder = new StringBuilder();
        try {
            writer = new FileOutputStream(new File(path, this.filename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void WriteToFile(String menu, String test, String data, String finishTime){
        builder.append(String.format("Menu Style: $s Test#: $s ; Time to Complete: $s", menu, test, finishTime));
        builder.append("\n");
    }
    public void PublishFile(Context context) throws IOException {
        writer.write(builder.toString().getBytes());
        writer.close();
        Toast.makeText(context, "Wrote to file " + this.filename, Toast.LENGTH_SHORT).show();
    }
}
