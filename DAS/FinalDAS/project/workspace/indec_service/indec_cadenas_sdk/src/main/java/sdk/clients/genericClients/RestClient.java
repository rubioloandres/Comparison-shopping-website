package sdk.clients.genericClients;
import sdk.clients.exceptions.ClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sdk.clients.constants.Constants.*;
public class RestClient  {
    private final String url;
    private final HttpClient client;


    protected RestClient(final String url) {
        this.url = url;
        this.client = HttpClientBuilder
                .create()
                .disableAutomaticRetries()
                .build();
    }

    protected String getQuery(final String base, final String... params) {
        final String target = "/" + base + "?";
        final String args = Stream.of(params)
                .map(p -> p + "=%s")
                .collect(Collectors.joining("&"));
        final String arg = (params.length > 1 ? args : params[0] + "=%s");
        return target + arg;
    }

    private HttpUriRequest constructURI (final String method,final String callTo) throws ClientException {

        URI uri = URI.create(url + callTo);
        switch (method) {
            case POST:
                HttpPost postReq = new HttpPost();
                postReq.setURI(uri);
                return postReq;
            case GET:
                HttpGet getReq = new HttpGet();
                getReq.setURI(uri);
                return getReq;
            case PUT:
                HttpPut putReq = new HttpPut();
                putReq.setURI(uri);
                return putReq;
            default:
                throw new ClientException("Invalid method: " + method);
        }
    }

    private HttpEntity extractHttpEntity (HttpResponse httpResponse) throws ClientException {
        return httpResponse.getEntity();
    }

    private HttpResponse execute (HttpUriRequest uri) throws ClientException {
      try{
          return client.execute(uri);
      } catch (ClientException ex) {
          throw new ClientException( "ENDPOINT " + url + " IS DOWN : " + ex.getMessage() );
      } catch (IOException ex) {
          throw new ClientException( "ENDPOINT " + url + " IS DOWN : " + ex.getMessage() );
      }
    }

    private String httpEntityToString (HttpEntity entity){
        try {
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    private HttpResponse filterBadResponse(HttpResponse response) throws ClientException {
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 500) throw new ClientException("Server Error -> " + response.toString());
        if (statusCode >= 400) throw new ClientException("Client Error -> " + response.toString());
        return response;

    }

    protected String call(final String method, final String callTo) throws ClientException  {

        HttpUriRequest request = constructURI(method, callTo);
        HttpResponse response = execute(request);
        filterBadResponse(response);
        HttpEntity entity = extractHttpEntity(response);
        return httpEntityToString(entity);
    }


}


