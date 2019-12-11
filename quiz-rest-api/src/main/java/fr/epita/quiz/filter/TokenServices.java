package fr.epita.quiz.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import fr.epita.quiz.datamodel.Role;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.rest.UserResource;
import fr.epita.quiz.services.UserDAO;

public class TokenServices {

	private static final Key signingKey = new SecretKeySpec(
			DatatypeConverter.parseBase64Binary("ExampleKey"), SignatureAlgorithm.HS512.getJcaName()
		);

	public static String createToken(String username, String role) throws NoSuchAlgorithmException {
        
		String token = Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
        return token;
	}

	public static String getRoleFromToken(String token)throws UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
		String role = null;
		role = claims.get("role").toString();
		return role;
	}

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

}
