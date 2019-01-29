package ftc.shift.sample.models;

public class Offer {
    private String id;
    private String userid;
    private String username;
    private Integer sum;
    public Offer() {
    }

    public Offer(String id, String userid, String username, Integer sum) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.sum = sum;
    }

    public String getId() { return this.id;}

    public void setId(String id) { this.id = id;}

    public String getUsername() { return this.username;}

    public void setUsername(String username) { this.username = username;}

    public Integer getSum() { return this.sum;}

    public void setSum(Integer sum) { this.sum = sum;}

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }
}

