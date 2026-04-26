package model.service;

import config.SpotifyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

@Service
public class SpotifyService {

    @Autowired
    private SpotifyConfig spotifyConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper       = new ObjectMapper();

    private String accessToken;
    private long   tokenExpiresAt = 0;

    // Obtiene token (Client Credentials)
    private String getAccessToken() throws Exception {
        if (accessToken != null && System.currentTimeMillis() < tokenExpiresAt) {
            return accessToken;
        }

        String credentials = spotifyConfig.getClientId() + ":" + spotifyConfig.getClientSecret();
        String encoded     = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encoded);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
            "https://accounts.spotify.com/api/token", request, String.class
        );

        JsonNode node  = mapper.readTree(response.getBody());
        accessToken    = node.get("access_token").asText();
        tokenExpiresAt = System.currentTimeMillis() + (node.get("expires_in").asLong() - 60) * 1000;
        return accessToken;
    }

    // ── Buscar álbumes
    public List<Map<String, Object>> searchAlbums(String query) throws Exception {
        String token = getAccessToken();
        String url   = "https://api.spotify.com/v1/search?q="
                       + java.net.URLEncoder.encode(query, "UTF-8")
                       + "&type=album&limit=10&market=ES";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> req = new HttpEntity<>(headers);

        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, req, String.class);
        JsonNode root   = mapper.readTree(res.getBody());
        JsonNode albums = root.path("albums").path("items");

        List<Map<String, Object>> result = new ArrayList<>();
        for (JsonNode album : albums) {
            Map<String, Object> a = new LinkedHashMap<>();
            a.put("spotifyAlbumId", album.path("id").asText());
            a.put("title",          album.path("name").asText());
            a.put("artist",         album.path("artists").get(0).path("name").asText());
            a.put("releaseYear",    album.path("release_date").asText().substring(0, 4));
            JsonNode images = album.path("images");
            a.put("coverUrl", images.size() > 0 ? images.get(0).path("url").asText() : null);
            a.put("spotifyUrl", album.path("external_urls").path("spotify").asText());
            result.add(a);
        }
        return result;
    }

    // ── Obtener álbum por ID
    public Map<String, Object> getAlbum(String spotifyId) throws Exception {
        String token = getAccessToken();
        String url   = "https://api.spotify.com/v1/albums/" + spotifyId + "?market=ES";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> req = new HttpEntity<>(headers);

        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, req, String.class);
        JsonNode album = mapper.readTree(res.getBody());

        Map<String, Object> a = new LinkedHashMap<>();
        a.put("spotifyAlbumId", album.path("id").asText());
        a.put("title",          album.path("name").asText());
        a.put("artist",         album.path("artists").get(0).path("name").asText());
        a.put("releaseYear",    album.path("release_date").asText().substring(0, 4));
        JsonNode images = album.path("images");
        a.put("coverUrl",   images.size() > 0 ? images.get(0).path("url").asText() : null);
        a.put("spotifyUrl", album.path("external_urls").path("spotify").asText());
        return a;
    }

    // ── Obtener canciones con preview_url
    public List<Map<String, Object>> getAlbumTracks(String spotifyId) throws Exception {
        String token = getAccessToken();
        String url   = "https://api.spotify.com/v1/albums/" + spotifyId + "/tracks?market=ES&limit=50";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> req = new HttpEntity<>(headers);

        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, req, String.class);
        JsonNode root   = mapper.readTree(res.getBody());
        JsonNode tracks = root.path("items");

        List<Map<String, Object>> result = new ArrayList<>();
        int trackNumber = 1;
        for (JsonNode track : tracks) {
            Map<String, Object> t = new LinkedHashMap<>();
            t.put("id",          track.path("id").asText());
            t.put("trackNumber", trackNumber++);
            t.put("name",        track.path("name").asText());
            t.put("durationMs",  track.path("duration_ms").asLong());
            t.put("previewUrl", track.path("preview_url").isNull() ? null : track.path("preview_url").asText());
            t.put("spotifyUrl",  track.path("external_urls").path("spotify").asText());
            result.add(t);
            
        }
        return result;
    }
}
