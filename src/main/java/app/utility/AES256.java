package app.utility;

import app.data.environment.AES256_Environment;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AES256 {

    private final AES256_Environment environment;

    public AES256(AES256_Environment environment) {
        this.environment = environment;
    }

    public String encrypt(String plain){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(environment.getPrivate_key()),"AES"), new IvParameterSpec(Base64.getDecoder().decode(environment.getIv())));
            return new String(Base64.getEncoder().encode(cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8))));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String cypher){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(environment.getPrivate_key()), "AES"), new IvParameterSpec(Base64.getDecoder().decode(environment.getIv())));
            return new String(cipher.doFinal(Base64.getDecoder().decode(cypher)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
