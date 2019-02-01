package ftc.shift.sample.models;

public class TransferClient {
    private String userGiverId;
    private String userRecieverId;
    private Integer sum;

    public TransferClient(){
    }

    public TransferClient(String userGiverId, String userRecieverId, Integer sum) {
        this.userGiverId = userGiverId;
        this.userRecieverId = userRecieverId;
        this.sum = sum;
    }
        public void setUserGiverId(String userGiverId) {
            this.userGiverId = userGiverId;
        }

        public void setUserRecieverId(String userRecieverId) {
            this.userRecieverId = userRecieverId;
        }

        public void setSum(Integer sum) {
            this.sum = sum;
        }
    public String getUserGiverId() {
        return userGiverId;
    }

    public String getUserRecieverId() {
        return userRecieverId;
    }

    public Integer getSum() {
        return sum;
    }
}
