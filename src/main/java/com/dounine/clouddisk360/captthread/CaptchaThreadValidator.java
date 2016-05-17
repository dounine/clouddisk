package com.dounine.clouddisk360.captthread;

import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CaptchaThreadValidator implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaThreadValidator.class);
    public static final int timeoutMin = 10 * 60;
    private static final String ACCOUNT_NOT_NULL = "account not empty";
    private static final String ERROR_MESSAGE_1="captchaValidator 属性(account/captchaPath)不能为空";
    private static final String ERROR_MESSAGE_2="captchaValidator 不能为空";
    private CaptchaThreadValidator() {
    }

    private static final CaptchaThreadValidator captchaThreadValidator;

    static {
        captchaThreadValidator = new CaptchaThreadValidator();
        new Thread(captchaThreadValidator).start();
    }

    public static CaptchaThreadValidator getInstance() {
        return captchaThreadValidator;
    }

    private static Map<String, CaptchaValidator> captchaValidators = new ConcurrentHashMap(100);

    public static boolean isTimeout(final String account) {
        return isTimeout(account, false);
    }

    public static boolean isTimeout(final String account,final boolean removeTime) {
        boolean timeout = false;
        if (StringUtils.isBlank(account)) {
            LOGGER.error(ACCOUNT_NOT_NULL);
        }else{
            synchronized (captchaValidators) {
                final CaptchaValidator captchaValidator = captchaValidators.get(account);
                if (removeTime && null != captchaValidator && captchaValidator.getAddTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
                    timeout = captchaValidators.remove(account).isSuccess();
                }
            }
        }
        return timeout;
    }

    public static void validCaptchaValidator(final String account,final CaptchaValidator captchaValidator) {
        if (StringUtils.isBlank(account)) {
            LOGGER.error(ERROR_MESSAGE_1);
        }
        if (null == captchaValidator) {
            LOGGER.error(ERROR_MESSAGE_2);
            return;
        }
        final CaptchaValidator cv = captchaValidators.get(account);
        if (null != cv) {
            synchronized (captchaValidators) {
                cv.setCaptchaValue(captchaValidator.getCaptchaValue());
                captchaValidators.put(account, cv);
            }
        }
    }

    public static void addCaptchaValidator(final String account,final CaptchaValidator captchaValidator) {
        if (null == captchaValidator) {
            LOGGER.error(ERROR_MESSAGE_2);
            return;
        }
        synchronized (captchaValidators) {
            if (StringUtils.isNotBlank(account)) {
                captchaValidators.put(account, captchaValidator);
            } else {
                LOGGER.error(ERROR_MESSAGE_1);
            }
        }
    }

    public static void removeCaptchaValidator(final String account) {
        if (null == account) {
            LOGGER.error("account 不能为空");
            return;
        }
        synchronized (captchaValidators) {
            if (StringUtils.isNotBlank(account)) {
                captchaValidators.remove(account);
            } else {
                LOGGER.error(ERROR_MESSAGE_1);
            }
        }
    }

    public static CaptchaValidator getCaptchaValidator(final String account) {
        return getCaptchaValidator(account, false);
    }

    public static CaptchaValidator getCaptchaValidator(final LoginUserToken loginUserToken) {
        return getCaptchaValidator(loginUserToken.getAccount(), false);
    }

    public static boolean hasCaptcha(final String account) {
        synchronized (captchaValidators) {
            return null != captchaValidators.get(account);
        }
    }

    public static CaptchaValidator getCaptchaValidator(final String account,final boolean removePass) {
        CaptchaValidator cv = null;
        if (null == account) {
            LOGGER.error("account 不能为空");
        }else if (StringUtils.isNotBlank(account)) {
            cv = captchaValidators.get(account);
        } else {
            LOGGER.error("account 不能为空");
        }
        return cv;
    }

    public static Map<String, CaptchaValidator> getCaptchaValidators() {
        return captchaValidators;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
                String removeTimeoutKey = null;
                for (final String key : captchaValidators.keySet()) {
                    final CaptchaValidator captchaValidator2 = captchaValidators.get(key);
                    if (captchaValidator2.getAddTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
                        removeTimeoutKey = key;
                        break;
                    } else if (null != captchaValidator2.getValidTime() && captchaValidator2.getValidTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
                        removeTimeoutKey = key;
                        break;
                    }
                }
                if (null != removeTimeoutKey) {
                    synchronized (captchaValidators) {
                        captchaValidators.remove(removeTimeoutKey);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新验证时间及验证信息
     *
     * @param account
     */
    public static void updateValidMsgAndTime(final String account,final String msg,final boolean validStatus) {
        synchronized (captchaValidators) {
            final CaptchaValidator captchaValidator = captchaValidators.get(account);
            if (null != captchaValidator) {
                captchaValidator.setValidMsg(msg);
                captchaValidator.setValidTime(LocalDateTime.now());
                captchaValidator.setSuccess(validStatus);
                captchaValidators.put(account, captchaValidator);
            } else {
                LOGGER.info("用户无需处理验证码");
            }
        }
    }

    public static void emptyCaptchaValue(final String account) {
        synchronized (captchaValidators) {
            final CaptchaValidator captchaValidator = captchaValidators.get(account);
            if (null != captchaValidator) {
                captchaValidator.setCaptchaValue(null);
                captchaValidators.put(account, captchaValidator);
            }
        }
    }
}
