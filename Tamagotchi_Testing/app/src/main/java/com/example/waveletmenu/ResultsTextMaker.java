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
    private String menu;
    private FileOutputStream writer;
    public ResultsTextMaker(String menu, String name, File path) {
        this.filename = name;
        this.menu = menu;
        this.filename += this.menu + ".txt";

        builder = new StringBuilder();
        try {
            writer = new FileOutputStream(new File(path, this.filename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        builder.append(String.format("Subject: %s; Menu Style: %s\n", name, menu));
    }

    public void WriteToFile(String test, String finishTime) {
        builder.append(String.format("Test#: %s; Completion Time: %sms\n", test, finishTime));
    }
    public void PublishFile(Context context) throws IOException {
        writer.write(builder.toString().getBytes());
        writer.close();
        Toast.makeText(context, "Wrote to file " + this.filename, Toast.LENGTH_SHORT).show();
    }
}
