package io.github.vindecoder.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * NHTSA Recall API response wrapper
 *
 * @author Wal33D
 */
public class RecallResponse {

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Message")
    private String message;

    @SerializedName(value = "results", alternate = {"Results"})
    private List<RecallRecord> results;

    // Getters
    public Integer getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }

    public List<RecallRecord> getResults() {
        return results;
    }

    // Setters
    public void setCount(Integer count) {
        this.count = count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResults(List<RecallRecord> results) {
        this.results = results;
    }

    /**
     * Check if response contains any recalls
     * @return true if results is not null and not empty
     */
    public boolean hasRecalls() {
        return results != null && !results.isEmpty();
    }

    @Override
    public String toString() {
        return "RecallResponse{" +
                "count=" + count +
                ", message='" + message + '\'' +
                ", results=" + (results != null ? results.size() + " recalls" : "null") +
                '}';
    }
}