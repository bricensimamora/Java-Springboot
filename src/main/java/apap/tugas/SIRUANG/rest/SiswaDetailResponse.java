package apap.tugas.SIRUANG.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SiswaDetailResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private SiswaDetail result;

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param result the result to set
     */
    public void setResult(SiswaDetail result) {
        this.result = result;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the result
     */
    public SiswaDetail getResult() {
        return result;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
}
