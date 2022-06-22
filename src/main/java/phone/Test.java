package phone;

/**
 * Test
 *
 * @author by gatesma.
 */
public class Test {


    public static void main(String[] args) {


        PhoneUtils.Phone phone = PhoneUtils.getPhone("+861021356793");
        System.out.println(phone);
        PhoneUtils.Phone phone1 = PhoneUtils.getPhone("+8613480755775");
        System.out.println(phone1);
        PhoneUtils.Phone phone2 = PhoneUtils.getPhone("053189602586");
        System.out.println(phone2);
        PhoneUtils.Phone phone3 = PhoneUtils.getPhone("0755-23772535");
        System.out.println(phone3);

    }


}
