package config.sentinelErrorConfig.Exceptions.ExceptionsImpl;

import config.sentinelErrorConfig.SentinelError;
import config.sentinelErrorConfig.Exceptions.SentinelExceptions;

public class SentinelExceptionsImpl implements SentinelExceptions {

    @Override
    public SentinelError DaoUserNotFound(Long id) {
        return new SentinelError("Dao Exception: User of id: " + id + " not found.");
    }

    @Override
    public SentinelError CitizenNotFound(Long id) {
        return new SentinelError("Dao Exception: Citizen of id: " + id + " not found.");
    }

    @Override
    public SentinelError PoliceNotFound(Long id) {
        return new SentinelError("Dao Exception: Police of id: " + id + " not found.");
    }

    @Override
    public SentinelError FirNotFound(Long id) {
        return new SentinelError("Dao Exception: Police of id: " + id + " not found.");
    }

    @Override
    public SentinelError OICNotFound(Long id) {
        return new SentinelError("Dao Exception: OIC of police: " + id + " not found.");
    }

    @Override
    public SentinelError CaseNotFound(Long id) {
        return new SentinelError("Dao Exception: Case of id: " + id + " not found.");
    }

    @Override
    public SentinelError VerdictNotFound(Long id) {
        return new SentinelError("Dao Exception: Verdict of id: " + id + " not found.");
    }

}
