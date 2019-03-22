package hello.dto;

public class AccountMsgOpportunity {

    private String account;
    private String context;

    public AccountMsgOpportunity() {
    }

    public AccountMsgOpportunity(String account, String context) {
        this.account = account;
        this.context = context;
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

    @Override
    public String toString() {
        return "AccountMsgOpportunity{" +
                "account='" + account + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
