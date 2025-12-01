package com.ecommerce.productservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiResponse(String message, boolean success, HttpStatus httpStatus) {
        this.message = message;
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(Builder builder) {
        this.message = builder.message;
        this.success = builder.success;
        this.httpStatus = builder.httpStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static class Builder {
        private String message;
        private boolean success;
        private HttpStatus httpStatus;

        public Builder() {
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public ApiResponse build(){
            return new ApiResponse(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "message='" + message + '\'' +
                    ", success=" + success +
                    ", httpStatus=" + httpStatus +
                    '}';
        }
    }
}
