package cos;

import java.net.URLEncoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * Test
 *
 * @author by gatesma.
 */
public class Test {

    private static String hmacSha1(String value, String key) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, key).hmacHex(value);
    }

    public static void main(String[] args) throws Exception {

        String timeRange = String.valueOf(1655199360) + ";" + String.valueOf(1655199960);
        String signKey = hmacSha1(timeRange, "ght91KU1UkbU2NXyDFwnAS/ziTj/jIIzZ9");

        String formatMethod = "post";
        String formatUri = "/c5fddc1d-e92b-447f-8448-84453ade377c4540270814141206618.txt";

        String formatString = "post/template-16010401-2022-06-14-14%3a25%3a05.xlsxuploads=host=downloadlead-40094.sh.gfp.tencent-cloud.com";
        String stringToSign = String.format("%s\n%s\n%s\n", "sha1", timeRange, DigestUtils.sha1Hex(formatString));

        System.out.println(hmacSha1(stringToSign, signKey));
    }

}
