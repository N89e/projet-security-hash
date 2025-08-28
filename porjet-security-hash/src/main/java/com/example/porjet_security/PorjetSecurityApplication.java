package com.example.porjet_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;

@SpringBootApplication
public class PorjetSecurityApplication {

	public static String getHash(String input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		String hex = HexFormat.of().formatHex(hash);
		return hex;
	}

	public static String getBCrypt(String input) throws NoSuchAlgorithmException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(input);
	}

	public static int findNonce(String prenom) throws NoSuchAlgorithmException {
		int nonce = 1;
		while (true) {
			String neoPrenom = prenom + nonce;
			String hash = getHash(neoPrenom);
			if (hash.startsWith("0000")) {
				return nonce;
			}
			nonce++;
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		//TP1
		List<String> prenoms = List.of("Sarah", "Mohamed", "Clara", "Hugo", "Léa");

		for (String prenom : prenoms) {
			int nonce = findNonce(prenom);
			String neoprenom = prenom + nonce;
			//TP2_a1
			String hash = getHash(prenom);
			System.out.println(prenom + " Hash-256 : " + hash);
			//TP2_a2
			String hash2 = getHash(neoprenom);
			System.out.println(prenom + " → nonce : " + nonce + " → " + " neoprenom : " + neoprenom + " hash-256 : "+ hash2);
			//TP2_a3
			String hash3 = getBCrypt(prenom);
			System.out.println(prenom + " BCrypt : " + hash3);

		}

		//TP2 C
		String secretKey = "maSuperCleSecrete123maSuperCleSecrete123";
		String message = "Voici une chaîne à signer";
		//Génération du JWT avec payload
		String jwt = Jwts.builder()
						.setSubject("TITI")
						.claim("message : ", message)
						.setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
						.compact();
		System.out.printf(" JWT : " + jwt);
		//Décodage vérification JWT
		Claims claims = Jwts.parser()
						.setSigningKey(secretKey.getBytes())
						.parseClaimsJws(jwt)
						.getBody();
		System.out.printf("\n Message extrait du JWT : " + claims.get("message"));

		SpringApplication.run(PorjetSecurityApplication.class, args);
	}

}
