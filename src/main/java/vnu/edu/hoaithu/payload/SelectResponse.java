package vnu.edu.hoaithu.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SelectResponse {
    private long numberOfRow;
    private long time;
    private ArrayList<Object> result;

    public long getNumberOfRow() {
        return numberOfRow;
    }

    public void setNumberOfRow(long numberOfRow) {
        this.numberOfRow = numberOfRow;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<Object> getResult() {
        return result;
    }

    public void setResult(ArrayList<Object> result) {
        this.result = result;
    }
}
