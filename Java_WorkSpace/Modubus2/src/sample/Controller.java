package sample;

import gnu.io.SerialPortEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static java.lang.Thread.sleep;
import static sample.CommUtil.receivedata;

public class Controller {

    private String sbaddr;
    private String jcqaddr;
    private String gnm;
    private String senddata;

    @FXML private TextField tfsbaddr;
    @FXML private TextField tfgnm;
    @FXML private TextField tfjcqaddr;
    @FXML private TextArea tarecdata;
    @FXML private ChoiceBox datatype;
    @FXML private Label lasenddata;

    CommUtil commUtil = CommUtil.getInstance();  //创建一个控制发送接收的类

    public void Send()  {
        sbaddr = tfsbaddr.getText();
        gnm = tfgnm.getText();
        jcqaddr = tfjcqaddr.getText();
        commUtil.send(sbaddr+gnm+jcqaddr);
        senddata =  sbaddr+gnm+jcqaddr + commUtil.getCRC(sbaddr+gnm+jcqaddr);
        lasenddata.setText(senddata);
    }

    public void setreceivedata(){
        String type = datatype.getSelectionModel().getSelectedItem().toString(); //获取choicebox中当前选中的选项
        if (type.equals("十六进制数")){
            tarecdata.clear();
            for(int i=6;i<receivedata.length()-4;i=i+4){
                tarecdata.appendText("0x"+receivedata.charAt(i)+receivedata.charAt(i+1)+ receivedata.charAt(i+2)+receivedata.charAt(i+3)+"\n");
            }
        }
        if (type.equals("整数")){
            tarecdata.clear();
            for(int i=6;i<receivedata.length()-4;i=i+4){
                String hex_num = ""+receivedata.charAt(i)+receivedata.charAt(i+1)+ receivedata.charAt(i+2)+receivedata.charAt(i+3);
                System.out.println(hex_num);
                long dec_num = Integer.valueOf(hex_num, 16);
                tarecdata.appendText(dec_num+"\n");
            }
        }
        if (type.equals("浮点数")){
            tarecdata.clear();
            for(int i=6;i<receivedata.length()-4;i=i+8){
                String hex_num = ""+receivedata.charAt(i)+receivedata.charAt(i+1)+ receivedata.charAt(i+2)+receivedata.charAt(i+3)+
                        receivedata.charAt(i+4)+receivedata.charAt(i+5)+ receivedata.charAt(i+6)+receivedata.charAt(i+7);
                Float value = Float.intBitsToFloat(Integer.valueOf(hex_num.trim(), 16));
                tarecdata.appendText(" "+value+"\n");
            }
        }
    }
}
