package typedmap;

/**
 * TypedMap
 *
 * @author by gatesma.
 */

import java.util.HashMap;
import java.util.Optional;

/**
 * 带类型的Map，在Map的基础上
 *
 * @author leiwen
 */
public class TypedMap {

    private final HashMap<String, Object> map = new HashMap<>();

    public <T> Optional<T> getOptional(TypedKey<T> key) {
        Object value = map.get(key.getKey());
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of((T) value);
    }

    public <V> void put(TypedKey<V> key, V value) {
        map.put(key.getKey(), value);
    }


    public static void main(String[] args) {

        String KEY_AGENCY_ID = "agencyId";
        String KEY_AGENCY = "agency";
        String KEY_CALL_CENTER = "callCenter";
        String KEY_SKIP_VERIFY_CODE = "skipVerifyCode";
        String KEY_EMPLOYEE = "employee";
        String KEY_AGENCY_NAME = "agencyName";
        String KEY_ENTRY_JS = "callCenterEntryJs";
        String KEY_BM_ID = "bmId";
        String KEY_MDM_ID = "mdmId";
        String KEY_COMPANY_ID = "companyId";
        String KEY_FREE_LOGIN_UID = "freeLoginUid";
        String KEY_CMS_SESSION = "AuthSession";
        String KEY_MANAGED_AGENCY_IDS = "managedAgencyIds";

        TypedKey<String> AGENCY_ID = TypedKey.create(KEY_AGENCY_ID);
        TypedKey<Long> BM_ID = TypedKey.create(KEY_BM_ID);
        TypedKey<Long> MDM_ID = TypedKey.create(KEY_MDM_ID);
        TypedKey<Long> COMPANY_ID = TypedKey.create(KEY_COMPANY_ID);
        TypedKey<String> CALL_CENTER = TypedKey.create(KEY_CALL_CENTER);
        TypedKey<Boolean> SKIP_VERIFY_CODE = TypedKey.create(KEY_SKIP_VERIFY_CODE);
        TypedKey<String> ENTRY_JS = TypedKey.create(KEY_ENTRY_JS);
        TypedKey<Long> FREE_LOGIN_UID = TypedKey.create(KEY_FREE_LOGIN_UID);

        TypedMap map = new TypedMap();
        map.put(AGENCY_ID, "AgencyId");
        Optional<String> optional = map.getOptional(AGENCY_ID);
        Optional<Long> tesyt = map.getOptional(FREE_LOGIN_UID);

    }

}

