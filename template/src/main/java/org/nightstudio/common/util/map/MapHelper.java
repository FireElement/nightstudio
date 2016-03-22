package org.nightstudio.common.util.map;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xuezhucao on 16/3/22.
 */
public abstract class MapHelper<K, V> {
    public abstract K getKey(V o);

    public Map<K, V> getMap(Map<K, V> map, List<V> list) {
        if (map == null) {
            return null;
        }
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        for (V o : list) {
            map.put(getKey(o), o);
        }
        return map;
    }
}
