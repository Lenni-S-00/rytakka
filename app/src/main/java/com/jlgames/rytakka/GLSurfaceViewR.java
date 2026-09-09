package com.jlgames.rytakka;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.jlgames.rytakka.peli.toiminnot.Toiminnot;

public class GLSurfaceViewR extends GLSurfaceView {

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;

    private final GLRenderer renderer;

    public GLSurfaceViewR(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        renderer = new GLRenderer();
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();
        float leveys = getWidth();
        float korkeus = getHeight();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - previousX;
                float dy = y - previousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2f) {
                    dx = dx * -1;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2f) {
                    dy = dy * -1;
                }
                requestRender();
            break;
            case MotionEvent.ACTION_DOWN:
                Toiminnot.kosketusToiminto(x, y, leveys, korkeus);
            break;
        }

        previousX = x;
        previousY = y;
        return super.onTouchEvent(e);
    }
}
