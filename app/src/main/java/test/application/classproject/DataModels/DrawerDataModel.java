package test.application.classproject.DataModels;

import android.graphics.drawable.Drawable;

/**
 * Created by Iman on 6/28/2018.
 */

public class DrawerDataModel {
    String title;
    Drawable img;

    public DrawerDataModel(String title, Drawable img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
