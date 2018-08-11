package util;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class HttpHelper {

    public static String getRequestPostData(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int len = 0;
            try {
                len = request.getInputStream().read(buffer, i, contentLength - i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (len == -1) {
                break;
            }
            i += len;
        }
        try {
            return new String(buffer, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String finalResp(ActionContext ac,String msg, Object data, int code){
        ac.put("data", getResp(msg,data,code));
        return "success";
    }

    public static String finalResp(ActionContext ac,String msg){
        ac.put("data",getResp(msg,null,0));
        return "success";
    }
    public static String finalResp(ActionContext ac,String msg, int code){
        ac.put("data",getResp(msg,null,code));
        return "success";
    }

    public static String finalResp(ActionContext ac,String msg, Object data){
        ac.put("data",getResp(msg,data,0));
        return "success";
    }

    public static HashMap<String,Object> getResp(String msg, Object data, int code){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("code",code);
        map.put("data",data);
        map.put("msg",msg);
        return map;
    }

    public static JSONObject getReqJson(HttpServletRequest req){
        try {
            String postData= HttpHelper.getRequestPostData(req);
            if(postData==null||postData.isEmpty())return null;
            JSONObject jsonObject = JSONObject.parseObject(postData);
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
