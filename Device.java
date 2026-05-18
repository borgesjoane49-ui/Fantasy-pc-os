package com.fantasy.pc;

public interface Device {
    int getStartAddress();
    int getEndAddress();
    byte read(int address);
    void write(int address, byte value);
    void tick();
    void reset();
}

