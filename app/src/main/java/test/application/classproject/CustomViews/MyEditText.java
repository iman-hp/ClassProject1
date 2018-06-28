package test.application.classproject.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Iman on 6/26/2018.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public String text(){
        if(getText()!=null)
            return getText().toString();
        return "";
    }

}
