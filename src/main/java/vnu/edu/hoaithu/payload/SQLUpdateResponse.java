package vnu.edu.hoaithu.payload;

public class SQLUpdateResponse {
    private int number_of_effected_row;
    private long executed_time;

    public SQLUpdateResponse(int number_of_effected_row, long executed_time) {
        this.number_of_effected_row = number_of_effected_row;
        this.executed_time = executed_time;
    }

    public SQLUpdateResponse() {
    }

    public int getNumber_of_effected_row() {
        return number_of_effected_row;
    }

    public void setNumber_of_effected_row(int number_of_effected_row) {
        this.number_of_effected_row = number_of_effected_row;
    }

    public long getExecuted_time() {
        return executed_time;
    }

    public void setExecuted_time(long executed_time) {
        this.executed_time = executed_time;
    }
}
