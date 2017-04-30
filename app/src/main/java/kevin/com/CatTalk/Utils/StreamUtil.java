package kevin.com.CatTalk.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Created by Kevin Wong on 2017/3/25 0025.
 */

public class StreamUtil {
    /**
     * 将流信息转化成字符串
     * @return
     * @throws IOException
     */
    public static String parserStreamUtil(InputStream in) throws IOException {
        //字符流,读取流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        //写入流
        StringWriter sw  = new StringWriter();
        //读写操作
        //数据缓冲区
        String str = null;
        while((str = br.readLine()) !=null){
            //写入操作
            sw.write(str);
        }
        //关流
        sw.close();
        br.close();
        return sw.toString();
    }
}
