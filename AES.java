import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static SecretKeySpec secretkey;
    private static byte[] key;
    public static void setkey(String mykey){
        MessageDigest sha=null;
        try{
            key = mykey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretkey = new SecretKeySpec(key, "AES");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt,String secret)
    {
        try{
            setkey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }catch(Exception e){
            System.out.println("Error while Encrypting: "+e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt,String secret)
    {
        try{
            setkey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }catch(Exception e){
            System.out.println("Error while Encrypting: "+e.toString());
        }
        return null;
    }

    public static void main(String[] args){

        final String secretKey = "annaUniversity";
        String originalString = "www.annauniv.edu";
        String encryptedString=AES.encrypt(originalString,secretKey);
        String decryptedString  = AES.decrypt(encryptedString,secretKey);
        System.out.println("URL Encryption using AES Algorithm\n");
        System.out.println("Original URL :"+originalString);
        System.out.println("Encrypted URL :"+encryptedString);
        System.out.println("Decrypted URL :"+decryptedString);

    }
}
