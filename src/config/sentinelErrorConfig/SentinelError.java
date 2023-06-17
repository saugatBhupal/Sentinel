package config.sentinelErrorConfig;

public class SentinelError extends RuntimeException{
    
    public SentinelError(String errMessage){
        super("\n"+errMessage);
    }
}
