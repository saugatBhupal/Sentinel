package config.passwordEncoderConfig;

import org.mindrot.jbcrypt.BCrypt;

public class Encoder{

    private static final int logRounds = 12;

    public static String toHash (String plainText){
        String hashedData = BCrypt.hashpw(plainText, BCrypt.gensalt(logRounds));
        return (hashedData);
    }

    public static int verify (String source, String targetHash){
        if(BCrypt.checkpw(source, targetHash)){
            return(1);
        }
        return(0);
    }
}