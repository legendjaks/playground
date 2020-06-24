package com.jay.tree;

import java.util.TreeMap;

class MyCalendar {

    private TreeMap<Integer, Integer> bookings;

    public MyCalendar() {
        bookings = new TreeMap<>();
    }

    public boolean book(int start, int end) {

        Integer prev = bookings.floorKey(start);
        Integer next = bookings.ceilingKey(start);

        if((prev == null || bookings.get(prev) <= start) && (next == null || end <= next)){
            bookings.put(start, end);
            return true;
        }
        return false;
    }
}
