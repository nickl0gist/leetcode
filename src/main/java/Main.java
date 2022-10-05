import lombok.extern.slf4j.Slf4j;
import two_sum.TwoSum;

import java.util.Arrays;

/**
 * Created on 05.10.2022
 * @author Mykola Horkov
 * <br> mykola.horkov@gmail.com
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        int [] arr = {2, 5, 6, 6, 33, 66, 35, 25};

        log.info(Arrays.toString(TwoSum.twoSumOptimized(arr ,99)));
    }
}
