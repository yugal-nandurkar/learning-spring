package microteam.account.exception;

public class BalanceNotEnoughException extends RuntimeException {

    public BalanceNotEnoughException(String message) {
        super(message);
    }

}
