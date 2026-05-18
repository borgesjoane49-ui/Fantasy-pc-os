package com.fantasy.pc;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RAM ram = new RAM(0, 65535);
        Bus bus = new Bus();
        bus.attach(ram);
        VRAM vram = new VRAM();
        Kernel kernel = new Kernel(vram);
        CPU cpu = new CPU();
        cpu.setBus(bus);
        cpu.setKernel(kernel);
        Scheduler scheduler = new Scheduler();
        scheduler.add(new Process());

        // PROGRAMA: Desenha 3 pixels em escada
        ram.load(0, new byte[]{
					 0x41, 0x01,                   // regA = SYS_DRAW
					 0x44, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, // regD = BRANCO

					 0x42, 0x05, 0x43, 0x05, (byte)0xFF, // Ponto 1 em 5,5
					 0x42, 0x06, 0x43, 0x06, (byte)0xFF, // Ponto 2 em 6,6
					 0x42, 0x07, 0x43, 0x07, (byte)0xFF, // Ponto 3 em 7,7

					 0x30, 0x00                    // Loop infinito no endereço 0
				 });

        VMEngine engine = new VMEngine(cpu, bus, scheduler);
        new Thread(engine).start();

        setContentView(new DesktopView(this, vram));
    }
}

