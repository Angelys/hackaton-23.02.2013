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
import org.json.JSONArray;
import org.json.JSONException;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Answer;
import ua.org.gdg.cherkassy.hackaton.askme.objects.AnswersCollection;
import ua.org.gdg.cherkassy.hackaton.askme.objects.Question;
import ua.org.gdg.cherkassy.hackaton.askme.objects.QuestionsCollection;

import java.io.IOException;
import java.net.ResponseCache;
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

    public static String BASE_URL = "http://ua-gdg-hackathon-askme.appstore.com";
    public static String reg_url = BASE_URL + "/register";
    public static String post_question_url = BASE_URL + "/q";
    public static String post_answer_url = BASE_URL + "/a";
    public static String get_answers = BASE_URL + "/a";
    public static String get_questions = BASE_URL + "/a";

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
        String lang = Locale.getDefault().getDisplayLanguage();

        List<NameValuePair> data = new ArrayList<NameValuePair>(2);
        data.add(new BasicNameValuePair("old_id", old_id));
        data.add(new BasicNameValuePair("id", new_id));
        data.add(new BasicNameValuePair("lang", lang));

        sendPostRequest(reg_url, data);
    }

    public static boolean postQuestion(Question q)
    {
        List<NameValuePair> data = new ArrayList<NameValuePair>(2);
        data.add(new BasicNameValuePair("title", q.getTitle()));

        HttpResponse r = sendPostRequest(post_question_url, data);

        return r != null && r.getStatusLine().getStatusCode() == 200;
    }

    public static boolean postAnswer(Answer a)
    {
        List<NameValuePair> data = new ArrayList<NameValuePair>(2);
        data.add(new BasicNameValuePair("question_id", Integer.toString(a.getQuestion_id())));

        HttpResponse r = sendPostRequest(post_answer_url, data);

        return r != null && r.getStatusLine().getStatusCode() == 200;
    }

    public static QuestionsCollection getQuestions()
    {
        HttpResponse response = sendGetRequest(get_questions, null);

        if(response == null)
        {
            return new QuestionsCollection();
        }

        JSONArray array = new JSONArray();

        try
        {
            array = new JSONArray(EntityUtils.toString(response.getEntity()));
        } catch (JSONException je){

        } catch (IOException ioe){

        }

        return new QuestionsCollection(array);
    }

    public static AnswersCollection getAnswers(Question q)
    {
        List<NameValuePair> data = new ArrayList<NameValuePair>(2);
        data.add(new BasicNameValuePair("question_id", Integer.toString(q.getId())));

        HttpResponse response = sendGetRequest(get_answers, null);

        if(response == null)
        {
            return new AnswersCollection();
        }

        JSONArray array = new JSONArray();

        try
        {
            array = new JSONArray(EntityUtils.toString(response.getEntity()));
        } catch (JSONException je){

        } catch (IOException ioe){

        }

        return new AnswersCollection(array);
    }


    public static HttpResponse sendGetRequest(String uri, List<NameValuePair> params)
    {
        if(params == null)
        {
            params = new ArrayList<NameValuePair>(2);
        }

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
