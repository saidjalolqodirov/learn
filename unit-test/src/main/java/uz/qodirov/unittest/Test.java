package uz.qodirov.unittest;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        Integer value = map.get(s.charAt(0));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
             if (!entry.getValue().equals(value)) {
                 return false;
             }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
