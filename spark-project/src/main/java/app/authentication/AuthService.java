package app.authentication;

import app.User;
import app.user.UserRepository;
import app.util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    static Key key = MacProvider.generateKey();

    public static String generateJWT(User user){
       
        Map<String, Object> claims = new HashMap();
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());
        claims.put("type", user.getType());
        claims.put("user", user);

        return Jwts.builder().setSubject(user.getUsername())
        		.setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + 1000*60*60L))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static boolean isAuthorized(String token){
        if(!Util.isEmpty(token) && token.contains("Bearer ")){
            String jwt = token.substring(token.indexOf("Bearer ") + 7);
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            if(UserRepository.findUserByUsername(claims.getBody().getSubject()) != null){
            	
                return true;
            }
        }
        return false;
    }
    public static boolean isAdmin(String token) {
    	 if(!Util.isEmpty(token) && token.contains("Bearer ")){
    	String jwt = token.substring(token.indexOf("Bearer ") + 7);
    	Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
    	
    	if(claims.getBody().get("type").equals("admin")) {
    		return true;
    	}else {
    		return false;
    	}
    	 }
		return false;
		
    	
    }
}
