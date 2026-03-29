package cms.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {

    // Hash using SHA-256 and encode with Base64
    public static String hash(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("SHA-256 algorithm not available", ex);
        }
    }

    // Verify plain password against stored hash
    public static boolean verify(String plainPassword, String storedHash) {
        if (storedHash == null || storedHash.isEmpty()) return false;
        String computed = hash(plainPassword);
        return computed.equals(storedHash);
    }
}
