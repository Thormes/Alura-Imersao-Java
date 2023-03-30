import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class MarvelAPIUrlCreator {

    public String getUrl() {
        try{
        EnvParser envparser = new EnvParser();
        String privateKey = envparser.properties.get("MARVEL_PRIVATE_KEY");
        String publicKey = envparser.properties.get("MARVEL_PUBLIC_KEY");
        String ts = Long.toString(System.currentTimeMillis());
        String hashString = ts + privateKey + publicKey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(hashString.getBytes(StandardCharsets.UTF_8)));
        StringBuilder hashText = new StringBuilder(hash.toString(16));
        while (hashText.length() < 32){
            hashText.insert(0, "0");
        }
        return "https://gateway.marvel.com:443/v1/public/characters?limit=25&ts=" + ts + "&apikey=" + publicKey + "&hash=" + hashText;
        }
        catch (IOException | NoSuchAlgorithmException ex){
            return "";
        }
    }
}
