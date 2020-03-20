package pythia.za.data.models;

public class QueryResult {
    private Object result;
    private String message;
    private Object error;

    public QueryResult(Object result, String message, Object error) {
        this.result = result;
        this.message = message;
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Object getError() {
        return error;
    }

    @Override
    public String toString() {
        System.out.println(this.result);
        System.out.println();
        return "QueryResult{" +
                "result=" + result.toString() +
                ", message='" + message + '\'' +
                ", error=" + (error != null ? error.toString(): "null") +
                '}';
    }
}
