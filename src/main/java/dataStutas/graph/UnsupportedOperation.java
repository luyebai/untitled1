package dataStutas.graph;

/**
 * 此异常表示不支持的操作
 */
public class UnsupportedOperation extends  RuntimeException {
    public UnsupportedOperation(String message) {
        super(message);
    }
}
