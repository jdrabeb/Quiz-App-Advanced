package fr.epita.quiz.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

public class KeyGenerator {
	
	private static final Key signingKey = new SecretKeySpec(
			DatatypeConverter.parseBase64Binary("fwWxc#e1,s21Szx/?sz?ww1#"), SignatureAlgorithm.HS512.getJcaName()
		);

    static public Key generateKey() {
    	return signingKey;
    }
}
