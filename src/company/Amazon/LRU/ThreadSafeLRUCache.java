package company.Amazon.LRU;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe LRU Cache implementation
 */
public class ThreadSafeLRUCache<K, V> implements LRUCache<K, V> {
    private final LRUCacheImpl<K, V> cache;
    private final ReentrantReadWriteLock lock;
    private final ReadWriteLock readWriteLock;
    
    public ThreadSafeLRUCache(int capacity) {
        this.cache = new LRUCacheImpl<>(capacity);
        this.lock = new ReentrantReadWriteLock();
        this.readWriteLock = new ReentrantReadWriteLock();
    }
    
    @Override
    public V get(K key) {
        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public void delete(K key) {
        lock.writeLock().lock();
        try {
            cache.delete(key);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            cache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }
    
    @Override
    public int capacity() {
        return cache.capacity();
    }
    
    @Override
    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            return cache.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    @Override
    public void printCache() {
        lock.readLock().lock();
        try {
            cache.printCache();
        } finally {
            lock.readLock().unlock();
        }
    }
}
