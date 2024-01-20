package command;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class ElectronicKey {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue =
            new byte[] {'Z', 'o', 'o', 'x', 'S', 'D', 'C', '7', '3', 'Z', 'o', 'o', 'x', 'S', 'D', 'C'};

    public String encrypt(String password) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(password.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encValue);
        return encryptedValue;
    }

    public String decrypt(String encryptedPassword) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedPassword);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
}
