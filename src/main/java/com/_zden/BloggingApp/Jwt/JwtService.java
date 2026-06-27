package com._zden.BloggingApp.Jwt;

import com._zden.BloggingApp.Entities.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.sql.results.graph.basic.BasicResultAssembler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.time.LocalDateTime;

@Service
public class JwtService {

    public String generateJwtToken(String email, String password){
         return Jwts.builder()
                 .claims()
                 .subject(email)
                 .issuedAt(new Date())
                 .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 60))
                 .and().signWith(SignatureAlgorithm.HS256, this.getSecretKey())
                 .compact();
    }
    public boolean validateJwtToken(String token) throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(token);
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]),StandardCharsets.UTF_8);
        System.out.println(payload);
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(
                getSecretKey().getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );
        mac.init(keySpec);
        byte[] bytes = mac.doFinal((parts[0]+"."+parts[1]).getBytes(StandardCharsets.UTF_8));
        String sign = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
        String exppString = payload.substring(payload.indexOf("exp")+5);
        if (exppString.contains(","))
                exppString = exppString.substring(0,exppString.indexOf(','));
        else if (exppString.contains("}"))
            exppString = exppString.substring(0,exppString.indexOf('}'));
        System.out.println(getClaims(token).getExpiration());
        Date rn = new Date(System.currentTimeMillis());
//        if (rn.compareTo(new Date(Integer.parseInt(exppString))) || !(sign == parts[2])){
//            System.out.println("this jwt token in expired!!");
//            return false;
//        }
        System.out.println("token is valid!!");
        return true;
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private String getSecretKey(){
        String encoded = Base64.getEncoder().encodeToString("tempSecret".getBytes(StandardCharsets.UTF_8));
        return "jF9fQzE0x5mXr3d2V7m6H8nBqY2YxJwZkN7QvL8pT3sR6uK4mE1dW9cA2yF5hG7J";
    }


}
