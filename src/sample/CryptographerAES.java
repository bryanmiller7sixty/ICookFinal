package sample;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/*********************************************************************
 * This class encrypts and decrypts a String value
 *********************************************************************/
public class CryptographerAES {

    private static final String ALGO = "AES";//The encryption algorithm is called AES
    private byte[] keyValue;

    /**
     * Constructor is passed a 16 character String of character
     * @param key
     **/
    public  CryptographerAES(String key){
        keyValue = key.getBytes();
    }

    /**
     * Encrypts a String
     * @param data
     * @return the newly encrypted value
     * @throws Exception
     */
    public String encrypt(String data) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        //String encryptedValue = BASE64Encoder().encode(encVal);
        String base64Encoded = Base64.getEncoder().encodeToString(encVal);
        return base64Encoded;
    }

    /**
     * Decrypts a encrypted String
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        //byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}