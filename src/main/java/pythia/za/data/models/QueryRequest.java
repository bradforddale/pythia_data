package pythia.za.data.models;

public class QueryRequest {
    private String operation;
    private Object requestObj;

    public QueryRequest() {
    }

    public QueryRequest(String operation, Object requestObj) {
        this.operation = operation;
        this.requestObj = requestObj;
    }

    public String getOperation() {
        return operation;
    }


    public Object getRequestObj() {
        return requestObj;
    }
}
