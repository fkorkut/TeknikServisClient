package egitim.uniyaz.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import egitim.uniyaz.dto.KullaniciDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ResetClientKullanici {

    public KullaniciDto kullaniciListe(KullaniciDto kullaniciDto){
        Gson gson = new GsonBuilder().create();
        String kullaniciDtoJson = gson.toJson(kullaniciDto);

        HttpPost post = new HttpPost("http://localhost:8080/rest/kullanici/findKullanici");
        HttpEntity httpEntity = new StringEntity(kullaniciDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            KullaniciDto kullanici = gson.fromJson(dataAsJsonStr, KullaniciDto.class);
            return  kullanici;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}