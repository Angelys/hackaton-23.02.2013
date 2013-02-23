package ua.org.gdg.cherkassy.hackaton.askme;

import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 2/23/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerAPI {

    public static String BASE_URL = "";
    public static String reg_url = BASE_URL + "/register/add";
    public static String upd_url = BASE_URL + "/register/update";
    public static String post_question_url = BASE_URL + "/q";
    public static String post_answer_url = BASE_URL + "/a";

    public static void sendRegData(Context context, String regId)
    {
        String lang = Locale.getDefault().getDisplayLanguage();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("id", regId));
        nameValuePairs.add(new BasicNameValuePair("lang", lang));

        sendPostRequest(reg_url, nameValuePairs);
    }

    public static void sendUpdateData(String old_id, String new_id)
    {
        List<NameValuePair> data = new ArrayList<NameValuePair>(2);
        data.add(new BasicNameValuePair("old_id", old_id));
        data.add(new BasicNameValuePair("new_id", new_id));

        sendPostRequest(upd_url, data);
    }

    public static HttpResponse sendGetRequest(String uri, List<NameValuePair> params)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri + "?" + URLEncodedUtils.format(params, "utf-8"));

        try {
            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httpGet);

            return response;

        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

    }

    public static HttpResponse sendPostRequest(String uri, List<NameValuePair> data)
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(uri);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(data));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            return response;
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

}
