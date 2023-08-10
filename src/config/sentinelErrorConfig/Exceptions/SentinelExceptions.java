package config.sentinelErrorConfig.Exceptions;

import config.sentinelErrorConfig.SentinelError;

public interface SentinelExceptions{
    SentinelError DaoUserNotFound(Long id);
    SentinelError CitizenNotFound(Long id);
    SentinelError PoliceNotFound(Long id);
    SentinelError FirNotFound(Long id);
    SentinelError OICNotFound(Long id);
    SentinelError CaseNotFound(Long id);
    SentinelError VerdictNotFound(Long id);
}
