package hello.dto;

public class AccountMsg {
    private String account;
    private String context;
    private String source;
    private String msg;


    public AccountMsg() {
    }

    public AccountMsg(String account, String context, String source, String msg) {
        this.account = account;
        this.context = context;
        this.source = source;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "AccountMsg{" +
                "account='" + account + '\'' +
                ", context='" + context + '\'' +
                ", source='" + source + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
