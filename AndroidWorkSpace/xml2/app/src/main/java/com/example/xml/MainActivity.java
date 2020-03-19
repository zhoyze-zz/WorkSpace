package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XmlSerializer serializer = Xml.newSerializer();
        File file = new File(Environment.getExternalStorageDirectory(),"person_mes.xml");
        FileOutputStream fi_out = new FileOutputStream(file);
            serializer.setOutput(fi_out,"UTF-8");
            serializer.startDocument("UTF-8",true);
            serializer.startTag(null,"persons");
            serializer.startTag(null,"person");



    }
}
