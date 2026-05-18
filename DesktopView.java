package com.fantasy.pc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DesktopView extends View {
    private VRAM vram;
    private Paint paint = new Paint();
    private int scale = 10; // Pixel bem grande para teste

    public DesktopView(Context context, VRAM vram) {
        super(context);
        this.vram = vram;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY); // Fundo cinza do monitor

        for (int y = 0; y < VRAM.H; y++) {
            for (int x = 0; x < VRAM.W; x++) {
                int color = vram.buffer[y * VRAM.W + x];
                if (color != 0) {
                    paint.setColor(color);
                    canvas.drawRect(x * scale, y * scale, (x + 1) * scale, (y + 1) * scale, paint);
                }
            }
        }
        invalidate(); // Atualiza a tela
    }
}

