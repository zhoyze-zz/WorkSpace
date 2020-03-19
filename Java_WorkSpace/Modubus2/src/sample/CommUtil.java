package sample;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author:ms.y
 * @create 2019/1/24-8:48
 */
public class CommUtil implements SerialPortEventListener {

    public static String receivedata = null;

    private static final String PORT_NAME = "COM1";         //设置端口名称
    private static final int BIT_RATE = 9600;               //设置比特率
    public static final int DATA_BITS = SerialPort.DATABITS_8;  //数据位
    public static final int STOP_BIT = SerialPort.STOPBITS_1;   //停止位
    public static final int PARITY_BIT = SerialPort.PARITY_NONE;  //校验位

    private static SerialPort serialPort;
    private static InputStream in;  //输入流
    private static OutputStream out; //输出流
    private static CommUtil commUtil;

    private int flag = 0;

    private CommUtil(){}

    public static CommUtil getInstance(){
        if(commUtil==null){  //判断commUtil是否存在
            commUtil = new CommUtil(); //如果不存在就新建一个
            commUtil.init(); //初始化
        }
        return commUtil;
    }

    public void init(){
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(PORT_NAME);  //根据端口名称获取端口对象
            if (portIdentifier.isCurrentlyOwned()){         //判断对象是否被占用
                System.out.println("Error: 端口正在被使用");
            }else if(portIdentifier.getPortType()==1){
                serialPort = (SerialPort) portIdentifier.open(PORT_NAME,1000);
                serialPort.setSerialPortParams(BIT_RATE,DATA_BITS,STOP_BIT,PARITY_BIT);

                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();

                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
            }else{
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message){  //将要发送的16进制字符串消息转换成byte数组发送，并且让线程休眠1秒
        try {
            message = message + commUtil.getCRC(message);
            byte[] bytes = hexStrToByteArray(message);   //16进制转byte数组
            out.write(bytes);
//            Controller controller = new Controller();
//            controller.setreceivedata();
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serialEvent(SerialPortEvent event) {       //用来监听时间
        switch (event.getEventType()){       //判断是否有是可用数据，如果存在可用数据就接收。
            case SerialPortEvent.DATA_AVAILABLE:
                flag = 1;
                receive();
                break;
        }
    }

    public int getFlag(){
        return flag;
    }

    public String receive(){  //用来接收收到的数据，这里比较好理解，通过上面初始化的输入流，将获取到的数据存入byte数组，并且将其转成字符串返回。
        byte[] buffer = new byte[512];
        int data;
        String result = null;
        try{
            int len = 0;
            while ( ( data = in.read()) > -1 ){ //in 为输入流
                buffer[len++] = (byte) data;
            }
            byte[] copyValue = new byte[len];
            System.arraycopy(buffer,0,copyValue,0,len);
            result = ByteArrayToString(copyValue);
        }catch ( IOException e ){
            e.printStackTrace();
        }
        System.out.println(result);
        receivedata = result;
//        Controller controller = new Controller();
//        controller.setreceivedata();
        return result;
    }

    public void close(){
        try {
            in.close();
            out.close();
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
            serialPort.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //16进制转byte数组
    public byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    public String ByteArrayToString(byte[] by) {
        String str = "";
        for (int i = 0; i < by.length; i++) {
            String hex = Integer.toHexString(by[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            str += hex.toUpperCase();
        }
        return str;
    }

    public String getCRC(String s) {
        byte[] bytes = hexStrToByteArray(s);
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        String CRC2 = Integer.toHexString(CRC);
        CRC2 = ""+CRC2.charAt(2)+CRC2.charAt(3)+CRC2.charAt(0)+CRC2.charAt(1);//调换位置
        return CRC2;
    }


    public static void main ( String[] args ) throws Exception {
        CommUtil commUtil = CommUtil.getInstance();
        String crc = commUtil.getCRC("020300000002");
        System.out.println(crc);
        commUtil.send("020300000002");
        Main main = new Main();
    }

}


