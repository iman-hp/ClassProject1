package test.application.classproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.application.classproject.DataModels.DrawerDataModel;
import test.application.classproject.R;

/**
 * Created by Iman on 6/28/2018.
 */

public class DrawerLiastAdapter extends BaseAdapter {
    Context mcontext;
    List<DrawerDataModel>  dataModels;

    public DrawerLiastAdapter(Context mcontext, List<DrawerDataModel> dataModels) {
        this.mcontext = mcontext;
        this.dataModels = dataModels;
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v= LayoutInflater.from(mcontext).inflate(R.layout.drawer_list_item,parent,false);
        TextView txt_title=v.findViewById(R.id.txt_title);
        ImageView img=v.findViewById(R.id.img);
        txt_title.setText(dataModels.get(position).getTitle());
        img.setImageDrawable(dataModels.get(position).getImg());
        return v;
    }
}
