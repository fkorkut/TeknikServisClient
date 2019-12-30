package egitim.uniyaz.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import egitim.uniyaz.dto.ArizaDto;
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
import java.util.Arrays;
import java.util.List;

public class RestClientAriza {

    public List<ArizaDto> arizaListe(){
        ArizaDto arizaDto = new ArizaDto();
        Gson gson = new GsonBuilder().create();
        String arizaDtoJson = gson.toJson(arizaDto);

        HttpPost post = new HttpPost("http://localhost:8080/rest/ariza/findAllAriza");
        HttpEntity httpEntity = new StringEntity(arizaDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            ArizaDto[] mcArray = gson.fromJson(dataAsJsonStr, ArizaDto[].class);
            List<ArizaDto> mcList = Arrays.asList(mcArray);
            return  mcList;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



    public List<ArizaDto> searchAriza(String arizaDtoJson){
        Gson gson = new GsonBuilder().create();

        HttpPost post = new HttpPost("http://localhost:8080/rest/ariza/findArizaByName");
        HttpEntity httpEntity = new StringEntity(arizaDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            ArizaDto[] mcArray = gson.fromJson(dataAsJsonStr, ArizaDto[].class);
            List<ArizaDto> mcList = Arrays.asList(mcArray);;
            return  mcList;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void saveAriza(ArizaDto arizaDto){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        String arizaDtoJson = gson.toJson(arizaDto);

        HttpPost post = new HttpPost("http://localhost:8080/rest/ariza/saveAriza");
        HttpEntity httpEntity = new StringEntity(arizaDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            ArizaDto Ariza = gson.fromJson(dataAsJsonStr, ArizaDto.class);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
