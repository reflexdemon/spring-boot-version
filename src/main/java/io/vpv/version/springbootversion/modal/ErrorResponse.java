package io.vpv.version.springbootversion.modal;

/**
 * Created by vprasanna on 6/12/18.
 * The type Error response.
 */
public class ErrorResponse {

    private String code;
    private String message;

    /**
     * Instantiates a new Error response.
     *
     * @param code    the code
     * @param message the message
     */
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Instantiates a new Error response.
     */
    public ErrorResponse() {
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
