package com.example.qthjen.mymusics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomList extends BaseAdapter {

    List<Song> mList;
    int mSong;
    Context mContext;

    public CustomList(Context context, int song, List<Song> list) {

        mContext = context;
        mSong = song;
        mList = list;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {

        TextView tvTitleList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();

        if ( view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate( mSong, null);
            viewHolder.tvTitleList = (TextView) view.findViewById(R.id.tvTitleList);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvTitleList.setText(mList.get(i).getmTitle());

        return view;
    }
}
