package tpu.teamwork.tinder.exception;

import java.util.Date;

public class UnauthorizedUserException extends RuntimeException {
  private String status;
  private Date timestamp;
    public UnauthorizedUserException(String message) {
        super(message);
    }

    public UnauthorizedUserException(int status, String message) {
      super(message.concat(String.valueOf(status)));
    }
}
