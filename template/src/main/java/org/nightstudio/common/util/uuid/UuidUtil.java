package org.nightstudio.common.util.uuid;

import java.util.UUID;

/**
 * Created by xuezhucao on 15/7/21.
 */
public class UuidUtil {
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String result = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return result;
    }
}
