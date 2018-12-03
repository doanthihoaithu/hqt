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

}
