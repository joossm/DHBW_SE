package Main.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AdvancedEncryptionStandard {

    private final String secretStr;
    private SecretKeySpec secretKey;
    private byte[] key;

    public AdvancedEncryptionStandard() {
        this.secretStr = "mosbach@DHBW";
    }

    public String decrypt(String encryptedMessage) throws Exception {
        return decrypt(encryptedMessage, secretStr);
    }
    public String encrypt(String plainMessage) throws Exception {
        return encrypt(plainMessage, secretStr);
    }

    private String decrypt(String encryptedMessage, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }

    private void setKey(String inputKey) throws Exception {
        MessageDigest sha;

        key = inputKey.getBytes(StandardCharsets.UTF_8);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
    }

    private String encrypt(String plainMessage, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainMessage.getBytes(StandardCharsets.UTF_8)));
    }
}
