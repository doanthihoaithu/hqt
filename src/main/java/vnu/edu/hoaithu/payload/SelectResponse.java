package vnu.edu.hoaithu.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SelectResponse {
    private long number_of_row;
    private long executed_time;
    private ArrayList<Object> result;

    public long getNumber_of_row() {
        return number_of_row;
    }

    public void setNumber_of_row(long number_of_row) {
        this.number_of_row = number_of_row;
    }

    public long getExecuted_time() {
        return executed_time;
    }

    public void setExecuted_time(long executed_time) {
        this.executed_time = executed_time;
    }

    public ArrayList<Object> getResult() {
        return result;
    }

    public void setResult(ArrayList<Object> result) {
        this.result = result;
    }
}
