package com.fantasy.pc;

public class Kernel {
    public static final int SYS_DRAW = 1;
    private VRAM vram;

    public Kernel(VRAM v) { vram = v; }

    public void syscall(CPU cpu) {
        if (cpu.regA == SYS_DRAW) {
            // Se a cor for 0, usamos branco para teste
            int color = (cpu.regD == 0) ? 0xFFFFFFFF : cpu.regD;
            vram.pixel(cpu.regB, cpu.regC, color);
        }
    }
}

