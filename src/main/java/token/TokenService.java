package token;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenService {

    private final static Logger LOG = LoggerFactory.getLogger(TokenService.class);

    /**
     * 生成token
     *
     * @param clientId
     * @param appId
     * @param appSecret
     * @return
     */
    public String encode(String clientId, String appId, String appSecret) {

        if (appId == null || appId.isEmpty()) {
            LOG.info("appId不能为空");
            throw new IllegalArgumentException("No appId");
        }

        if (appSecret == null || appSecret.isEmpty()) {
            LOG.info("secret不能为空");
            throw new IllegalArgumentException("Unable find the suitable appSecret.");
        }

        long timestamp = getCurrentTimestamp();
        String sign = sign(clientId, appId, appSecret, timestamp);
        String plain = compose(clientId, appId, timestamp, sign);

        return Base64.encodeBase64String(plain.getBytes());
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳（秒）
     */
    private long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 签名
     *
     * @param clientId
     * @param appId
     * @param appSecret
     * @param timestamp
     * @return 签名字符串
     */
    private String sign(String clientId, String appId, String appSecret, long timestamp) {
        return DigestUtils.sha1Hex(String.format("%s%s%d", appId, appSecret, timestamp));
    }

    /**
     * 组装数据
     *
     * @param clientId
     * @param appId
     * @param timestamp
     * @param sign
     * @return Token 文本
     */
    private String compose(String clientId, String appId, long timestamp, String sign) {
        String plain;
        if (clientId == null || clientId.isEmpty()) {
            plain = String.format("%s,%d,%s", appId, timestamp, sign);
        } else {
            plain = String.format("%s,%s,%d,%s", clientId, appId, timestamp, sign);
        }
        return plain;
    }

}
