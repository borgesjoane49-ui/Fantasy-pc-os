package com.fantasy.pc;

public class VMEngine implements Runnable {

    private CPU cpu;
    private Bus bus;
    private Scheduler scheduler;
    private Process current;

    public VMEngine(CPU c, Bus b, Scheduler s) {
        cpu = c;
        bus = b;
        scheduler = s;
    }

    public void run() {
        while (true) {
            if (current == null)
                current = scheduler.next();

            cpu.pc = current.pc;
            cpu.regA = current.regA;
            cpu.regB = current.regB;
            cpu.regC = current.regC;
            cpu.regD = current.regD;
            cpu.zero = current.zero;

            int op = bus.read(cpu.pc++) & 0xFF;
            cpu.step(op);

            current.pc = cpu.pc;
            current.regA = cpu.regA;
            current.regB = cpu.regB;
            current.regC = cpu.regC;
            current.regD = cpu.regD;
            current.zero = cpu.zero;

            try { Thread.sleep(1); } catch (Exception e) {}
        }
    }
}

