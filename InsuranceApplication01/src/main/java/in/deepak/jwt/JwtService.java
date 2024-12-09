package in.deepak.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.deepak.entities.User;
import in.deepak.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    private UserRepository userRepository;
    
    
    
    public void validateToken(final String token){
    	
         Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
         
    }
    public String generateToken(String userName){
    	
    	
        Map<String,Object> claims = new HashMap();
        User user = userRepository.findByEmail(userName).orElseThrow();
        claims.put("role",user.getRole());
        claims.put("userId",user.getUserId());
        return createToken(claims,userName);
        
    }
    private String createToken(Map<String,Object> claims,String userName) {
    	
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        
    }
    private Key getSignKey(){
    	
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

