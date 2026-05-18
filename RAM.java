package com.fantasy.pc;

public class RAM implements Device {

    private int start, end;
    private byte[] mem;

    public RAM(int s, int e) {
        start = s;
        end = e;
        mem = new byte[e - s + 1];
    }

    public int getStartAddress() { return start; }
    public int getEndAddress() { return end; }

    public byte read(int a) { return mem[a - start]; }
    public void write(int a, byte v) { mem[a - start] = v; }

    public void tick() {}
    public void reset() {}

    public void load(int addr, byte[] data) {
        for (int i = 0; i < data.length; i++)
            write(addr + i, data[i]);
    }
}

