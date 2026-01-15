package company.Amazon.LRU.exception;

class CacheFullException extends CacheException {
    public CacheFullException(String message) {
        super(message);
    }
}
