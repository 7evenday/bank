package ftc.shift.sample.models;

import ftc.shift.sample.models.TransferClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransferServer {
    private final String id;
    private final String userGiverId;
    private final String userRecieverId;
    private final Integer sum;
    private final String timeAccepted;

    public TransferServer(String id, TransferClient transferClient){
        this.id = id;
        this.userGiverId = transferClient.getUserGiverId();
        this.userRecieverId = transferClient.getUserRecieverId();
        this.sum = transferClient.getSum();
        this.timeAccepted = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public TransferServer(String id, String userGiverId, String userRecieverId, Integer sum){
        this.id = id;
        this.userGiverId = userGiverId;
        this.userRecieverId = userGiverId;
        this.sum = sum;
        this.timeAccepted = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
/*
    public void setUserGiverId(String userGiverId) {
        this.userGiverId = userGiverId;
    }

    public void setUserRecieverId(String userRecieverId) {
        this.userRecieverId = userRecieverId;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
*/

    public String getId() {
        return id;
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
