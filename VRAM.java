package com.fantasy.pc;

public class VRAM {

    public static final int W = 160;
    public static final int H = 144;

    public int[] buffer = new int[W * H];

    public void clear() {
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = 0;
    }

    public void pixel(int x, int y, int c) {
        if (x < 0 || y < 0 || x >= W || y >= H) return;
        buffer[y * W + x] = c;
    }
}

