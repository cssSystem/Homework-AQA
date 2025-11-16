package sys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DirectoryOfTelephoneNumbers {
    private HashMap<String, HashSet<String>> map = new HashMap<>();

    public void add(String name, String number) {
        if (!map.containsKey(name)) {
            map.put(name, new HashSet<>());
        }
        map.get(name).add(number);
    }

    public Set<String> get(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
