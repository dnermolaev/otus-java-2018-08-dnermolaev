package home;

import java.lang.ref.SoftReference;

public interface CacheEngine<K, V> {

    void put(MyElement<K, V> element);

    MyElement<K, SoftReference<V>> get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
