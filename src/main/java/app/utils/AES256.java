package app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AES256 {

    private static final byte[] KEY = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private final byte[] IV = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    public String encrypt(String plain){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY, "AES"), new IvParameterSpec(IV));
            return new String(Base64.getEncoder().encode(cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8))));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String cypher){
        System.out.println(cypher);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY, "AES"), new IvParameterSpec(IV));
            return new String(cipher.doFinal(Base64.getDecoder().decode(cypher)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
