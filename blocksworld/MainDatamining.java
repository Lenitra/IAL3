package blocksworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDatamining {
    public static void main(String[] args) {
        // create a var List<List<Integer>> data = new ArrayList<>();
        List<List<Integer>> data = new ArrayList<>();
        // add rows
        data.add(Arrays.asList(1, 2, 3));
        data.add(Arrays.asList(4, 5, 6));
        data.add(Arrays.asList(7, 8, 9));
        // print the data
        System.out.println(data);
    }
}
