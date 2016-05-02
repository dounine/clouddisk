package com.dounine.clouddisk360.captthread;

import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CaptchaThreadValidator implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaThreadValidator.class);
	public static final int timeoutMin = 10*60;

	private CaptchaThreadValidator() {
	}

	private static CaptchaThreadValidator captchaThreadValidator;

	static {
		captchaThreadValidator = new CaptchaThreadValidator();
		new Thread(captchaThreadValidator).start();
	}

	public static CaptchaThreadValidator getInstance() {
		return captchaThreadValidator;
	}

	private static Map<String, CaptchaValidator> captchaValidators = new HashMap<>(100);

	public static boolean isTimeout(String account) {
		return isTimeout(account, false);
	}

	public static boolean isTimeout(String account, boolean removeTime) {
		synchronized (captchaValidators) {
			if (null == account) {
				LOGGER.error("account 不能为空");
				return false;
			}
			if (StringUtils.isNotBlank(account)) {
				CaptchaValidator captchaValidator = captchaValidators.get(account);
				if (null != captchaValidator) {
					if (captchaValidator.getAddTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
						if (removeTime) {
							captchaValidators.remove(account);
						}
					}
				}
				return true;
			} else {
				LOGGER.error("account 不能为空");
			}
			return false;
		}
	}

	public static void validCaptchaValidator(String account, CaptchaValidator captchaValidator) {
		synchronized (captchaValidators) {
			if (null == captchaValidator) {
				LOGGER.error("captchaValidator 不能为空");
				return;
			}
			if (StringUtils.isNotBlank(account)) {
				CaptchaValidator cv = captchaValidators.get(account);
				if(null!=cv){
					cv.setCaptchaValue(captchaValidator.getCaptchaValue());
					captchaValidators.put(account,cv);
				}
			} else {
				LOGGER.error("captchaValidator 属性(account/captchaPath)不能为空");
			}
		}
	}

	public static void addCaptchaValidator(String account, CaptchaValidator captchaValidator) {
		synchronized (captchaValidators) {
			if (null == captchaValidator) {
				LOGGER.error("captchaValidator 不能为空");
				return;
			}
			if (StringUtils.isNotBlank(account)) {
				captchaValidators.put(account, captchaValidator);
			} else {
				LOGGER.error("captchaValidator 属性(account/captchaPath)不能为空");
			}
		}
	}

	public static void removeCaptchaValidator(String account) {
		synchronized (captchaValidators) {
			if (null == account) {
				LOGGER.error("account 不能为空");
				return;
			}
			if (StringUtils.isNotBlank(account)) {
				captchaValidators.remove(account);
			} else {
				LOGGER.error("captchaValidator 属性(account/captchaPath)不能为空");
			}
		}
	}

	public static CaptchaValidator getCaptchaValidator(String account) {
		return getCaptchaValidator(account, false);
	}

	public static CaptchaValidator getCaptchaValidator(LoginUserToken loginUserToken) {
		return getCaptchaValidator(loginUserToken.getAccount(), false);
	}

	public static boolean hasCaptcha(String account){
		synchronized (captchaValidators){
			return null!=captchaValidators.get(account);
		}
	}
	public static CaptchaValidator getCaptchaValidator(String account, boolean removePass) {
		synchronized (captchaValidators) {
			if (null == account) {
				LOGGER.error("account 不能为空");
				return null;
			}
			if (StringUtils.isNotBlank(account)) {
				return captchaValidators.get(account);
			} else {
				LOGGER.error("account 不能为空");
			}
			return null;
		}
	}

	public static Map<String, CaptchaValidator> getCaptchaValidators() {
		return captchaValidators;
	}

	@Override
	public void run() {
		for(;;){
			try {
				Thread.sleep(1000);
				synchronized (captchaValidators) {
					String removeTimeoutKey = null;
					for (String key : captchaValidators.keySet()) {
						CaptchaValidator captchaValidator2 = captchaValidators.get(key);
						if (captchaValidator2.getAddTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
							removeTimeoutKey = key;
							break;
						}else if(null!=captchaValidator2.getValidTime()){
							if (captchaValidator2.getValidTime().plusMinutes(timeoutMin).isBefore(LocalDateTime.now())) {
								removeTimeoutKey = key;
								break;
							}
						}
					}
					if (null != removeTimeoutKey) {
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
	 * @param account
	 */
	public static void updateValidMsgAndTime(String account,String msg,boolean validStatus) {
		synchronized (captchaValidators){
			CaptchaValidator captchaValidator = captchaValidators.get(account);
			if(null!=captchaValidator){
				captchaValidator.setValidMsg(msg);
				captchaValidator.setValidTime(LocalDateTime.now());
				captchaValidator.setSuccess(validStatus);
				captchaValidators.put(account,captchaValidator);
			}else{
				LOGGER.info("用户无需处理验证码");
			}
		}
	}
	public static void emptyCaptchaValue(String account) {
		synchronized (captchaValidators){
			CaptchaValidator captchaValidator = captchaValidators.get(account);
			if(null!=captchaValidator){
				captchaValidator.setCaptchaValue(null);
				captchaValidators.put(account,captchaValidator);
			}
		}
	}
}
