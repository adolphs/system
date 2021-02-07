/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: HttpUtils
 * Author:   Administrator
 * Date:     2021/1/27
 * History:
 */
package com.wyait.manage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * 〈http 工具类〉
 *
 * @author Administrator
 * @create 2021/1/27 15:32
 * @since 1.0.0
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /** multipart/form-data 格式发送数据时各个部分分隔符的前缀,必须为 --  */
    private static final String BOUNDARY_PREFIX = "--";

    /** 回车换行,用于一行的结尾  */
    private static final String LINE_END = "\r\n";

   /* public static void main(String[] args) throws IOException {
        //请求 url
        String url = "http://10.194.252.56:8083/WebDiskServerDemo/upload";

        // keyValues 保存普通参数
        Map<String, Object> keyValues = new HashMap<>();
        keyValues.put("uid", "440113");
        keyValues.put("type", "doc");
        keyValues.put("folder_name", "11111");

        // filePathMap 保存文件类型的参数名和文件路径
        Map<String, String> filePathMap = new HashMap<>();
        String filePath = "c:/user_form/1353523557128056832/1353523557128056832_建设用地规划许可证和国有建设用地使用权不动产权证书合并办理.docx";
        if (!new File(filePath).exists()){
            System.out.println("找不到指定的路径----java.io.FileNotFoundException:  " + filePath);
        }
        filePathMap.put("file", filePath);

        //headers
        Map<String, Object> headers = new HashMap<>();
        //COOKIE: Name=Value;Name2=Value2
        headers.put("COOKIE", "token=" + System.currentTimeMillis());

        System.out.println("--====response======" + postFormData(url, filePathMap, keyValues, headers));
        //{"code":"0000","docid":11155599,"msg":"OK","uuid":"5099a28c-6112-11eb-aedc-d00d80981a7e"
    }*/

    /**
     * post 请求：以表单方式提交数据
     * 设置主体内容
     * 每一部分都是以--加分隔符开始的，然后是该部分内容的描述信息，然后一个回车换行，然后是描述信息的具体内容；
     * 如果传送的内容是一个文件的话，那么还会包含文件名信息以及文件内容类型。
     * 上面第二部分是一个文件体的结构，最后以--分隔符--结尾，表示请求体结束
     *
     * @param urlStr      请求的url
     * @param filePathMap key 参数名，value 文件的路径
     * @param keyValues   普通参数的键值对
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postFormData(String urlStr, Map<String, String> filePathMap, Map<String, Object> keyValues, Map<String, Object> headers) throws IOException {
        HttpURLConnection conn = getHttpURLConnection(urlStr, headers);
        //分隔符，可以自己任意规定（避免和正文重复）
        String boundary = "MyBoundary" + System.currentTimeMillis();
        //设置 Content-Type 为 multipart/form-data; boundary=${boundary}
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        //返回数据  responseContent
        StringBuilder responseContent = new StringBuilder();
        try {
            //发送参数数据
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            //发送普通参数
            if (keyValues != null && !keyValues.isEmpty()) {
                for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
                    //写分隔符--${boundary}，并回车换行
                    String boundaryStr = BOUNDARY_PREFIX + boundary + LINE_END;
                    out.write(boundaryStr.getBytes());
                    //写描述信息：Content-Disposition: form-data; name="参数名"，并两个回车换行
                    String contentDispositionStr = String.format("Content-Disposition: form-data; name=\"%s\"", entry.getKey()) + LINE_END + LINE_END;
                    out.write(contentDispositionStr.getBytes());
                    //写具体内容：参数值，并回车换行
                    String valueStr = entry.getValue().toString() + LINE_END;
                    out.write(valueStr.getBytes());
                }
            }

            //发送文件类型参数
            if (filePathMap != null && !filePathMap.isEmpty()) {
                for (Map.Entry<String, String> filePath : filePathMap.entrySet()) {
                    writeFile(filePath.getKey(), filePath.getValue(), boundary, out);
                }

            }
            //写结尾的分隔符--${boundary}--,然后回车换行
            String endStr = BOUNDARY_PREFIX + boundary + BOUNDARY_PREFIX + LINE_END;
            out.write(endStr.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

        } catch (Exception e) {
            LOGGER.error("HttpUtils.postFormData 请求异常！", e);
            e.printStackTrace();
        }
        return responseContent.toString();
    }

    /**
     * 获得连接对象
     *
     * @param urlStr
     * @param headers
     * @return
     * @throws IOException
     */
    private static HttpURLConnection getHttpURLConnection(String urlStr, Map<String, Object> headers) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时时间
        conn.setConnectTimeout(50000);
        conn.setReadTimeout(50000);
        //允许输入流
        conn.setDoInput(true);
        //允许输出流
        conn.setDoOutput(true);
        //不允许使用缓存
        conn.setUseCaches(false);
        //请求方式
        conn.setRequestMethod("POST");
        //设置编码 utf-8
        conn.setRequestProperty("Charset", "UTF-8");
        //设置为长连接
        conn.setRequestProperty("connection", "keep-alive");

        //设置其他自定义 headers
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, Object> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue().toString());
            }
        }

        return conn;
    }

    /**
     * 写文件类型的表单参数
     *
     * @param paramName 参数名
     * @param filePath  文件路径
     * @param boundary  分隔符
     * @param out
     * @throws IOException
     */
    private static void writeFile(String paramName, String filePath, String boundary,
                                  DataOutputStream out) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            // 写分隔符--${boundary}，并回车换行
            String boundaryStr = BOUNDARY_PREFIX + boundary + LINE_END;

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(fis));
            int size = 2*1024;
            char[] buf = new char[size];
            int len = 0;
            while ((len = fileReader.read(buf)) > 0){
//                out.write(boundaryStr.getBytes(), 0 , len);
                out.write(boundaryStr.getBytes());
            }


            /**
             * 写描述信息(文件名设置为上传文件的文件名)：
             * 写 Content-Disposition: form-data; name="参数名"; filename="文件名"，并回车换行
             * 写 Content-Type: application/octet-stream，并两个回车换行
             */
            String fileName = new File(filePath).getName();
            String contentDispositionStr = String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", paramName, fileName) + LINE_END;
            out.write(contentDispositionStr.getBytes());
            String contentType = "Content-Type: application/octet-stream" + LINE_END + LINE_END;
            out.write(contentType.getBytes());

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.write(line.getBytes());
            }
            //回车换行
            out.write(LINE_END.getBytes());
        } catch (Exception e) {
            LOGGER.error("写文件类型的表单参数异常", e);
            e.printStackTrace();
        }
    }

}
