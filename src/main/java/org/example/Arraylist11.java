package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Key {
    int id;
    Key(int id) { this.id = id; }
}

public class Arraylist11 {
    public static void main(String[] args) {
        HashMap<Key, String> map = new HashMap<>();
        map.put(new Key(1), "One");
        map.put(new Key(1), "Duplicate One");
        System.out.println("the op"+map.size());
    }
}

