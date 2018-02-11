package cn.com.dubbo.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import cn.com.dubbo.util.HttpClientUtils;


/**
 * 格式
 * 订单号|69码|amount|备注
 */
public class OrderMainTxt {
	
	/**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),"gbk");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String[] str = null;
                int line=0;
                StringBuffer sbf = null;
                while((lineTxt = bufferedReader.readLine()) != null){
//                    System.out.println(lineTxt);
                	sbf = new StringBuffer();
                    //处理业务
                    str = lineTxt.split("\\|");
                	//保存总纪录
            		sbf.append("{\"orderNo\":\""+str[0]);
            		sbf.append("\",");
            		sbf.append("\"notes\":\""+str[3]);
            		sbf.append("\",");
            		sbf.append("\"items\":[{\"goods69\":\""+str[1]);
            		sbf.append("\",");
            		sbf.append("\"amount\":\""+str[2]);
            		sbf.append("\"}]");
            		sbf.append("}");
                    String tt = HttpClientUtils.do_post("http://localhost:8080/oms-provider/reissued/add.do", sbf.toString());
                    System.out.println("result:" + tt);
                    line++;
                }
                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "D:\\bufa\\order5.txt";
        readTxtFile(filePath);
    	
    	/*String tt = "PAJK201607200001_20160719.txt";
    	System.out.println(tt.substring(17, 25));*/
    	
    }



}
