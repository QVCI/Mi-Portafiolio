package com.example.mitransporteonline;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;

public class BlurView extends View
{
    private final Bitmap blurredBitmap;

    public BlurView(Context context, Bitmap originalBitmap)
    {
        super(context);
        blurredBitmap = blurBitmap(context, originalBitmap, 25); // Ajusta la intensidad del desenfoque aquí
    }

    private Bitmap blurBitmap(Context context, Bitmap bitmap, float radius)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            Bitmap blurredBitmap = Bitmap.createBitmap(bitmap);
            RenderScript renderScript = RenderScript.create(context);
            Allocation input = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(renderScript, input.getType());
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            blur.setInput(input);
            blur.setRadius(radius);
            blur.forEach(output);
            output.copyTo(blurredBitmap);
            renderScript.destroy();
            return blurredBitmap;
        }
        else
        {
            return bitmap;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(blurredBitmap, 0, 0, new Paint());
    }
}
