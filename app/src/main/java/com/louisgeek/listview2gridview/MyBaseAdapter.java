package com.louisgeek.listview2gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by louisgeek on 2016/5/5.
 */
public class MyBaseAdapter extends BaseAdapter {

    private List<Map<String, Object>> mapList;
    private Context context;

    public boolean isGridLayout() {
        return isGridLayout;
    }

    public void setIsGridLayout(boolean isGridLayout) {
        this.isGridLayout = isGridLayout;
    }

    private boolean isGridLayout = false;

    public MyBaseAdapter(List<Map<String, Object>> mapList, Context context) {
        this.mapList = mapList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (!isGridLayout) {
            ListViewHolder listViewHolder = null;
            if (convertView != null && convertView.getTag() instanceof ListViewHolder) {
                listViewHolder = (ListViewHolder) convertView.getTag();
            } else {
                listViewHolder = new ListViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null, false);
                listViewHolder.id_iv_list = ButterKnife.findById(convertView, R.id.id_iv_list);
                listViewHolder.id_tv_list = ButterKnife.findById(convertView, R.id.id_tv_list);

              /* convertView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(context, "position:"+position, Toast.LENGTH_SHORT).show();
                   }
               });*/

                convertView.setTag(listViewHolder);
            }
            listViewHolder.id_tv_list.setText(mapList.get(position).get("name").toString());
        } else {
            GridViewHolder gridViewHolder = null;
            if (convertView != null && convertView.getTag() instanceof GridViewHolder) {
                gridViewHolder = (GridViewHolder) convertView.getTag();
            } else {


                gridViewHolder = new GridViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.gird_item, null, false);
                gridViewHolder.id_iv_grid = ButterKnife.findById(convertView, R.id.id_iv_grid);
                gridViewHolder.id_tv_grid = ButterKnife.findById(convertView, R.id.id_tv_grid);

               /*convertView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(context, "position:"+position, Toast.LENGTH_SHORT).show();
                   }
               });*/

                convertView.setTag(gridViewHolder);
            }
            gridViewHolder.id_tv_grid.setText(mapList.get(position).get("name").toString());
        }

        return convertView;
    }

    class ListViewHolder {
        ImageView id_iv_list;
        TextView id_tv_list;
    }

    class GridViewHolder {
        ImageView id_iv_grid;
        TextView id_tv_grid;
    }
}
