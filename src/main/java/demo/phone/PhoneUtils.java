package phone;

import java.util.Arrays;
import java.util.List;

/**
 * PhoneUtils
 * 电话使用
 *
 * @author juliapychen 2019-05-02
 */
public class PhoneUtils {

    private static List<String> LIMIT_8 = Arrays
            .asList(
                    "010",
                    "020", "021", "022", "023", "024", "025", "026", "027", "028", "029",
                    "0311",
                    "0371", "0377", "0379",
                    "0411", "0417",
                    "0431", "0432",
                    "0451",
                    "0510", "0511", "0512", "0513", "0514", "0515", "0516", "0517", "0518", "0519", "0523", "0527",
                    "0531", "0532",
                    "0551", "0558",
                    "0571", "0573", "0574", "0575", "0576", "0577", "0579",
                    "0591", "0595",
                    "0731",
                    "0754", "0755", "0757", "0760", "0769",
                    "0791",
                    "0851",
                    "0871",
                    "0898");

    public static Phone getPhone(String number) {
        return new PhoneParse(number).getPhone();
    }

    public static void main(String[] args) {
        Phone p = PhoneUtils.getPhone("00001234567");
        System.out.println(p);
    }

    /**
     * 将电话转成E164格式 简化版，例如将 10086 变为 +8610086
     */
    public static String convertToE164(String originalNumber) {
        if (originalNumber.startsWith("+86")) {
            return originalNumber;
        } else if (originalNumber.startsWith("86")) {
            return "+" + originalNumber;
        } else if (originalNumber.startsWith("0")) {
            return "+86" + originalNumber.substring(1);
        } else {
            return "+86" + originalNumber;
        }
    }

    public static class PhoneParse {

        private String countryCode;
        private String cityCode;
        private String telephone;
        private String extension;
        private String mobile;
        private String special;
        private String error;

        private String number;
        private int index;

        PhoneParse(String number) {
            this.number = number;
            this.index = 0;

            try {
                String firstNum = String.valueOf(this.peek());
                // +........
                if ("+".equals(firstNum)) {
                    this.skip();
                    this.parseFullTelephone();
                } else if ("(".equals(firstNum)) { // (..).....
                    char countryCodeChar = this.peek(1);
                    if (countryCodeChar == '0') {
                        this.skip(2);
                        this.cityCode = this.parseCityCode();
                    } else {
                        this.skip(1);
                        this.countryCode = this.parseCountryCode();
                        this.cityCode = this.parseCityCode();
                    }
                    String next = this.next(1);
                    if (")".equals(next)) {
                        this.telephone = this.parseTelephone();
                        this.extension = this.parseExtension();
                    } else {
                        this.error = "格式不正确";
                    }
                } else if ("0".equals(firstNum)) { // 0........
                    this.skip();
                    char next = this.peek();
                    // 00........
                    if (next == '0') {
                        // 玄镜线索LA/smb标签分层下发 特殊号码格式
                        if (number.startsWith("0000")) {
                            this.countryCode = "0000";
                            this.telephone = number.substring(4);
                            this.index = number.length();
                        } else {
                            this.skip();
                            this.parseFullTelephone();
                        }
                    } else { // 0xxx......
                        this.parseCityTelephone();
                    }
                } else if ("1".equals(firstNum)) { // 1.......
                    if (this.number.length() == 3) {
                        this.special = this.nextDigital(3);
                    } else if (this.number.length() > 4 && this.number.length() < 11) {
                        String four = this.number.substring(0, 4);
                        if ("1001".equals(four) || "1010".equals(four)) {
                            this.special = this.nextDigital(this.number.length());
                        }
                    } else {
                        this.mobile = this.parseMobile();
                    }
                } else { // x.......
                    String two = this.number.substring(0, 2);
                    String three = this.number.substring(0, 3);
                    if ("95".equals(two) && this.number.length() >= 5) {
                        this.special = this.nextDigital(this.number.length());
                    } else if (three.equals("400") || three.equals("800") && this.number.length() >= 10) {
                        String s1 = this.nextDigital(3);
                        this.skipDash();
                        String s2 = this.nextDigital(3);
                        this.skipDash();
                        String s3 = this.nextDigital(4);
                        this.special = s1.concat(s2).concat(s3);
                    } else {
                        this.error = "格式不正确";
                    }
                }
                // 判断是否过长
                if (this.error == null && this.index < this.number.length()) {
                    if (this.mobile != null || this.telephone != null || this.special != null) {
                        this.error = "输入过长";
                    } else {
                        this.error = "输入过短";
                    }
                }
            } catch (Exception e) {
                this.error = e.getMessage();
            }
        }

        /**
         * 解析完整电话号码
         */
        private void parseFullTelephone() throws Exception {
            this.countryCode = this.parseCountryCode();
            switch (this.countryCode) {
                case "86":
                    this.parseCityTelephone();
                    break;
                case "1":
                    this.telephone = this.nextDigital(10);
                    this.extension = this.parseExtension();
                    break;
                default:
                    // 其他国家/地区的电话号码
                    this.telephone = this.restDigital();
                    break;
            }
        }

        /**
         * 解析不含国家编码的电话号码
         */
        private void parseCityTelephone() throws Exception {
            this.skipDash();
            int index = this.peek() == '0' ? 1 : 0;
            if (this.peek(index) == '1' && this.peek(index + 1) != '0') {
                this.mobile = this.parseMobile();
            } else {
                this.cityCode = this.parseCityCode();
                this.telephone = this.parseTelephone();
                this.extension = this.parseExtension();
            }
        }

        /**
         * 国际电话区号列表
         * https://zh.wikipedia.org/wiki/%E5%9B%BD%E9%99%85%E7%94%B5%E8%AF%9D%E5%8C%BA%E5%8F%B7%E5%88%97%E8%A1%A8
         */
        private String parseCountryCode() throws Exception {
            char countryCode = this.peek();
            // 北美
            if (countryCode == '1') {
                return this.nextDigital(1);
            } else if (countryCode == '2') { // 非洲
                char second = this.peek(1);
                if (second == '0' || second == '7' || second == '8') {
                    return this.nextDigital(2);
                } else {
                    return this.nextDigital(3);
                }
            } else if (countryCode == '3') { // 欧洲
                char second = this.peek(1);
                if (second == '5' || second == '7' || second == '8') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else if (countryCode == '4') { // 欧洲
                char second = this.peek(1);
                if (second == '2') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else if (countryCode == '5') { // 墨西哥及中南美洲
                char second = this.peek(1);
                if (second == '0' || second == '9') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else if (countryCode == '6') { // 东南亚及大洋洲
                char second = this.peek(1);
                if (second == '7' || second == '8' || second == '9') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else if (countryCode == '7') { // 前苏联地区
                return this.nextDigital(2);
            } else if (countryCode == '8') { // 东亚及特殊服务
                char second = this.peek(1);
                if (second == '0' || second == '5' || second == '7' || second == '8' || second == '9') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else if (countryCode == '9') { // 西亚及南亚、中东
                char second = this.peek(1);
                if (second == '6' || second == '7' || second == '9') {
                    return this.nextDigital(3);
                } else {
                    return this.nextDigital(2);
                }
            } else {
                throw new Exception("格式不正确");
            }
        }

        /**
         * 城市区号
         * https://zh.wikipedia.org/wiki/%E4%B8%AD%E5%9B%BD%E5%A4%A7%E9%99%86%E7%94%B5%E8%AF%9D%E5%8C%BA%E5%8F%B7
         *
         * @return {string}
         */
        private String parseCityCode() throws Exception {
            this.skipDash();
            char cityCode = this.peek();
            // 01..
            if (cityCode == '1') {
                char second = this.peek(1);
                // 010
                if (second == '0') {
                    return "0" + this.nextDigital(2);
                } else { // 01x
                    throw new Exception("区号不存在");
                }
            } else if (cityCode == '2') { // 02..
                return "0" + this.nextDigital(2);
            } else {  // 0[3-9]..
                return "0" + this.nextDigital(3);
            }
        }

        /**
         * 固话总机
         *
         * @return {string}
         */
        private String parseTelephone() throws Exception {
            this.skipDash();
            int length = LIMIT_8.indexOf(this.cityCode) >= 0 ? 8 : 7;
            String result = this.nextDigital(length);
            if (result.charAt(0) == '0') {
                throw new Exception("总机号码非法");
            }
            return result;
        }

        /**
         * 固话分机
         *
         * @return {string}
         */
        private String parseExtension() throws Exception {
            this.skipDash();
            String result = this.restDigital();
            if (result != null) {
                if (result.length() < 3) {
                    throw new Exception("分机号码过短");
                } else if (result.length() > 5) {
                    throw new Exception("分机号码过长");
                }
            }
            return result;
        }

        /**
         * 手机
         *
         * @return {string}
         */
        private String parseMobile() throws Exception {
            this.skipDash();
            return this.nextDigital(11);
        }

        private char peek() {
            return this.peek(0);
        }

        /**
         * 提取指定位置的字符
         *
         * @param pos 字符位置
         * @return {*}
         */
        private char peek(int pos) {
            if (this.index + pos < this.number.length()) {
                return this.number.charAt(this.index + pos);
            } else {
                return (char) -1;
            }
        }

        private void skip() throws Exception {
            this.skip(1);
        }

        /**
         * 跳过指定长度
         *
         * @param length 跳过的长度
         */
        private void skip(int length) throws Exception {
            if (this.index + length > this.number.length()) {
                throw new Exception("输入过短");
            }
            this.index += length;
        }

        /**
         * 跳过分隔符
         */
        private void skipDash() throws Exception {
            char charInfo = this.peek();
            if (charInfo == '-') {
                this.skip();
            }
        }

        /**
         * 获取下一段
         *
         * @param length 下一段长度
         * @return {string}
         */
        private String next(int length) throws Exception {
            if (length > 0) {
                if (this.index + length > this.number.length()) {
                    throw new Exception("输入过短");
                }
                String token = this.number.substring(this.index, this.index + length);
                this.index += length;
                return token;
            } else {
                return null;
            }
        }

        /**
         * 获取下一批数字
         *
         * @param length 下一批数字长度
         * @return {string}
         */
        private String nextDigital(int length) throws Exception {
            String token = this.next(length);
            if (token != null && token.matches("^[0-9]+$")) {
                return token;
            } else {
                throw new Exception("输入格式不正确");
            }
        }

        /**
         * 获取剩余数字
         */
        private String restDigital() throws Exception {
            if (this.index >= this.number.length()) {
                return null;
            } else {
                return this.nextDigital(this.number.length() - this.index);
            }
        }

        public Phone getPhone() {
            return new Phone(this.countryCode, this.cityCode, this.telephone, this.extension, this.mobile,
                    this.special,
                    this.error, this.number);
        }

    }

    public static class Phone {

        private String countryCode;
        private String cityCode;
        private String telephone;
        private String extension;
        private String mobile;
        private String special;
        private String error;
        private String number;

        Phone(String countryCode, String cityCode, String telephone, String extension,
                String mobile, String special, String error, String number) {
            this.countryCode = countryCode;
            this.cityCode = cityCode;
            this.telephone = telephone;
            this.extension = extension;
            this.mobile = mobile;
            this.special = special;
            this.error = error;
            this.number = number;
        }

        public boolean isMobile() {
            return this.mobile != null;
        }

        public boolean isTelephone() {
            return this.telephone != null || isSpecial();
        }

        public boolean isSpecial() {
            return this.special != null;
        }

        public boolean isInternational() {
            return this.countryCode != null && !"86".equals(this.countryCode);
        }

        public boolean hasError() {
            return this.error != null;
        }

        /**
         * format
         *
         * @return String
         */
        public String format() {
            if (this.mobile != null) {
                return this.mobile;
            } else if (this.special != null) {
                return this.special;
            } else if (this.error != null) {
                return this.number;
            } else {
                if (this.isInternational()) {
                    return "+" + this.countryCode
                            + this.telephone
                            + (this.extension != null ? "-" + this.extension : "");
                } else {
                    return this.cityCode + "-"
                            + this.telephone
                            + (this.extension != null ? "-" + this.extension : "");
                }
            }
        }


        @Override
        public String toString() {
            return "Phone{" +
                    "countryCode='" + countryCode + '\'' +
                    ", cityCode='" + cityCode + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", extension='" + extension + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", special='" + special + '\'' +
                    ", error='" + error + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }






}
