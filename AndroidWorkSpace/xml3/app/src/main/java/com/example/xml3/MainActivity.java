package com.example.xml3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;
    private Button button;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermisionUtils.verifyStoragePermissions(this); //动态获取文件读取写入权限

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    XmlSerializer serializer = Xml.newSerializer();
                    File file = new File(Environment.getExternalStorageDirectory(),"person_mes.xml");
                    FileOutputStream fi_out=new FileOutputStream(file);
                    serializer.setOutput(fi_out,"UTF-8");
                    serializer.startDocument("UTF-8",true);
                    serializer.startTag(null,"persons");
                    serializer.startTag(null,"person");
                    //将Person对象的用户名属性写入
                    serializer.startTag(null,"name");
                    serializer.text(editText.getText().toString());
                    serializer.endTag(null,"name");
                    //将person对象的密码写入
                    serializer.startTag(null,"password");
                    serializer.text(editText2.getText().toString());
                    serializer.endTag(null,"password");
                    //结束标签
                    serializer.endTag(null,"person");
                    serializer.endTag(null,"persons");
                    serializer.endDocument();
                    serializer.flush();
                    fi_out.close();
                    Toast.makeText(MainActivity.this,"asd",Toast.LENGTH_LONG).show();
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XmlPullParser pullParser = Xml.newPullParser();//创建一个XmlPullParser实例
                File sd_path = new File(Environment.getExternalStorageDirectory() ,"person_mes.xml");//获取路径
                FileInputStream xml;
                ArrayList<persons>persons = null;
                persons person = null;
                try {
                    //为pull解析器设置要解析的XML数据
                    xml = new FileInputStream(sd_path);
                    pullParser.setInput(xml,"UTF-8"); //设置输入流
                    int event = pullParser.getEventType();
                    while(event!=XmlPullParser.END_DOCUMENT){
                        switch (event){
                            case XmlPullParser.START_DOCUMENT:
                                persons = new ArrayList<>();
                                break;
                            case XmlPullParser.START_TAG:
                                if("person".equals(pullParser.getName())){
                              //      int id = new Integer(pullParser.getAttributeValue(0));
                                    person = new persons(); }
                                if("name".equals(pullParser.getName())){
                                    String name = pullParser.nextText();
                                    person.setName(name);         }
                                if("age".equals(pullParser.getName())){
                                    String pwd = pullParser.nextText();
                                    person.setPwd(pwd);                } break;
                            case XmlPullParser.END_TAG:
                                if("person".equals(pullParser.getName())){
                                    persons.add(person);
                                    person = null;    }
                                break;
                        }
                        event = pullParser.next();
                    }
                    for (persons pperson:persons) {
                        Log.v("asd",pperson.getPwd().toString()+pperson.getName().toString());
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}
