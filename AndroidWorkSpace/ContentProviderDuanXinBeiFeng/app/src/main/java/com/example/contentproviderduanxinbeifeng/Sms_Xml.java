package com.example.contentproviderduanxinbeifeng;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Sms_Xml {
    //将短信保存在sd卡下的mes.xml文件下
    public static void beifen_sms(List<SmsInfo> list, Context context) {
        try {
            XmlSerializer serial = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "mes.xml");
            FileOutputStream fi_out = new FileOutputStream(file);
            //初始化序列号器，指定xml数据写入到哪个文件以及编码
            serial.setOutput(fi_out, "utf-8");
            serial.startDocument("utf-8", true);
            //根节点
            serial.startTag(null, "smss");
            for (SmsInfo info : list) {
                //构建父节点
                serial.startTag(null, "sms");
                serial.attribute(null, "id", info.getId() + "");
                //body部分
                serial.startTag(null, "body");
                serial.text(info.getBody());
                serial.endTag(null, "body");
                //address部分
                serial.startTag(null, "address");
                serial.text(info.getAddress());
                serial.endTag(null, "address");
                //type部分
                serial.startTag(null, "type");
                serial.text(info.getType() + "");
                serial.endTag(null, "type");
                //date部分
                serial.startTag(null, "date");
                serial.text(info.getDate() + "");
                serial.endTag(null, "date");
                //父节点结束
                serial.endTag(null, "sms");
            }
            serial.endTag(null, "smss");
            serial.endDocument();
            fi_out.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
