package config.sentinelErrorConfig.Exceptions.ExceptionsImpl;

import config.sentinelErrorConfig.SentinelError;
import config.sentinelErrorConfig.Exceptions.SentinelExceptions;

public class SentinelExceptionsImpl implements SentinelExceptions{

    @Override
    public SentinelError DaoUserNotFound(long id) {
        return new SentinelError("Dao Exception: User of id: " + id  + " not found.");
    }

    @Override
    public SentinelError CitizenNotFound(long id) {
        return new SentinelError("Dao Exception: Citizen of id: " + id  + " not found.");
    }

    @Override
    public SentinelError PoliceNotFound(long id) {
        return new SentinelError("Dao Exception: Police of id: " + id  + " not found.");
    }

    @Override
    public SentinelError FirNotFound(long id) {
       return new SentinelError("Dao Exception: Police of id: " + id  + " not found.");
    }
    
}
