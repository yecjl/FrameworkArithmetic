package com.study.arithmetic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by 48608 on 2018/1/8.
 */

public class ShowMapView extends View {
    public ShowMapView(Context context) {
        super(context);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        //每格地图大小为80*80,注意:数组和屏幕坐标X和Y相反
        int row = (int) y / 80;
        int col = (int) x / 80;
        if (MapUtils.map[row][col] == 0) {
            MapUtils.touchFlag++;
            if (MapUtils.touchFlag == 1) {
                MapUtils.startRow = row;
                MapUtils.startCol = col;
                MapUtils.map[row][col] = 2;
            } else if (MapUtils.touchFlag == 2) {
                MapUtils.endRow = row;
                MapUtils.endCol = col;
                MapUtils.map[row][col] = 2;
            }
        }
        this.invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < MapUtils.map.length; i++) {
            for (int j = 0; j < MapUtils.map[i].length; j++) {
                if (MapUtils.map[i][j] == 0) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.route);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                } else if (MapUtils.map[i][j] == 1) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.wall);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                } else if (MapUtils.map[i][j] == 2) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.path);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                }
            }
        }
        if (MapUtils.path != null) {
            canvas.drawPath(MapUtils.path, paint);
        }

    }
}
