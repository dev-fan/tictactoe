package org.itstep.falaleev.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class FinishLine extends View {

    Paint p;
    private int pos;

    public FinishLine(Context context, int pos) {
        super(context);
        p = new Paint();
        this.pos = pos;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int padding = 20;
        int startX = canvas.getWidth() / 6 - 10;
        int startY = canvas.getHeight() / 6 - 10;
        int middleX = canvas.getWidth() / 2;
        int middleY = canvas.getHeight() / 2;
        int endX = canvas.getWidth() - startX;
        int endY = canvas.getHeight() - startY;
        int[][] coordMap = {
                {padding, startY, canvas.getHeight() - padding, startY},
                {padding, middleY, canvas.getHeight() - padding, middleY},
                {padding, endY, canvas.getHeight() - padding, endY},
                {startX, padding, startX, canvas.getHeight() - padding},
                {middleX, padding, middleX, canvas.getHeight() - padding},
                {endX, padding, endX, canvas.getHeight() - padding},
                {padding, padding, canvas.getWidth() - padding, canvas.getHeight() - padding},
                {padding, canvas.getHeight() - padding, canvas.getWidth() - padding, padding},
        };

        // заливка канвы цветом
//        canvas.drawARGB(40, 204, 204, 204);

        // настройка кисти
        p.setColor(Color.YELLOW); // красный цвет
        p.setStrokeWidth(canvas.getWidth() / 20); // толщина линии = 10

        // рисуем линию от (100,100) до (500,50)
        canvas.drawLine(coordMap[pos][0], coordMap[pos][1], coordMap[pos][2], coordMap[pos][3], p);
    }

}
