package com.fantasy.pc;

import java.util.ArrayList;

public class Bus {

    private ArrayList<Device> devices = new ArrayList<Device>();

    public void attach(Device d) {
        devices.add(d);
    }

    public byte read(int addr) {
        for (int i = 0; i < devices.size(); i++) {
            Device d = devices.get(i);
            if (addr >= d.getStartAddress() && addr <= d.getEndAddress())
                return d.read(addr);
        }
        return 0;
    }

    public void write(int addr, byte v) {
        for (int i = 0; i < devices.size(); i++) {
            Device d = devices.get(i);
            if (addr >= d.getStartAddress() && addr <= d.getEndAddress())
                d.write(addr, v);
        }
    }

    public void tick() {
        for (int i = 0; i < devices.size(); i++)
            devices.get(i).tick();
    }
}

