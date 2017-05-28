import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599. Minimum Index Sum of Two Lists
 * @author LiPeng
 * @since 2017/5/2820:39
 */
public class MinimumIndexSumofTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            map.put(list2[i], i);
        }

        List<String> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            if (map.containsKey(list1[i])) {
                if ((map.get(list1[i]) + i) <= min) {
                    ans.add(list1[i]);
                    min = map.get(list1[i]) + i;
                }
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

    public static void main(String []args) {
        String []list1 = {"k","KFC"};
        String []list2 = {"k","KFC"};
        MinimumIndexSumofTwoLists s = new MinimumIndexSumofTwoLists();
        String [] ans = s.findRestaurant(list1, list2);
        for (int i = 0; i < ans.length; i++)
            System.out.println(ans[i]);
    }
}
