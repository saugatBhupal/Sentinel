package config.sentinelErrorConfig.Exceptions;

import config.sentinelErrorConfig.SentinelError;

public interface SentinelExceptions{
    SentinelError DaoUserNotFound(long id);
    SentinelError CitizenNotFound(long id);
    SentinelError PoliceNotFound(long id);
    SentinelError FirNotFound(long id);
}
