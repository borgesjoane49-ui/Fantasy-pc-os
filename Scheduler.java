package com.fantasy.pc;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Process> list = new ArrayList<Process>();
    private int index = 0;

    public void add(Process p) {
        list.add(p);
    }

    public Process next() {
        if (list.size() == 0) return null;
        return list.get(index++ % list.size());
    }
}

