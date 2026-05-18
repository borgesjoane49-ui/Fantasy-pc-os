package com.fantasy.pc;

public class CPU {
    public int regA, regB, regC, regD;
    public int pc;
    public boolean zero;
    private Bus bus;
    private Kernel kernel;

    public void setBus(Bus b) { bus = b; }
    public void setKernel(Kernel k) { kernel = k; }

    public void step(int op) {
        if ((op & 0xFF) == 0xFF) {
            kernel.syscall(this);
            return;
        }

        switch (op & 0xFF) {
            case 0x01: regA++; break;
            case 0x02: regB++; break;
            case 0x10: regA = regB; break;
            case 0x20: regA += regB; zero = (regA == 0); break;
            case 0x30: pc = bus.read(pc) & 0xFF; break;
            case 0x31: {
					int addr = bus.read(pc++) & 0xFF;
					if (zero) pc = addr;
					break;
				}
            case 0x41: regA = bus.read(pc++) & 0xFF; break;
            case 0x42: regB = bus.read(pc++) & 0xFF; break;
            case 0x43: regC = bus.read(pc++) & 0xFF; break;
            case 0x44: // Lê 4 bytes para formar uma cor completa (ARGB)
                int b1 = bus.read(pc++) & 0xFF;
                int b2 = bus.read(pc++) & 0xFF;
                int b3 = bus.read(pc++) & 0xFF;
                int b4 = bus.read(pc++) & 0xFF;
                regD = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                break;
        }
    }
}

