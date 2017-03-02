package com.eworl.easybubble.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.eworl.easybubble.R;
import com.eworl.easybubble.RecyclerViewListeners.SelecteditemDragListener;
import com.eworl.easybubble.ViewHolder.RvHolder;
import com.eworl.easybubble.RecyclerViewListeners.Listener;
import com.eworl.easybubble.activities.MainActivity;
import com.eworl.easybubble.db.Program;

import java.util.List;

/**
 * Created by Dhankher on 2/27/2017.
 */

public class RvAdapterSelectedItems extends RecyclerView.Adapter<RvHolder> {


    private static final String TAG = "RvAdapterSelectedItems";
    private List<ItemObject> itemList;
    private Context context;
    private List<Program> log_list;
    private MainActivity mainActivity;
    private Listener mListener;


    public RvAdapterSelectedItems(Context context, List<ItemObject> itemList, List<Program> log_list, MainActivity mainActivity,Listener listener) {
        this.itemList = itemList;
        this.context = context;
        this.log_list = log_list;
        this.mainActivity = mainActivity;
        this.mListener = listener;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list_layout, null);
        RvHolder rcv = new RvHolder(layoutView, context, itemList, mainActivity, log_list,mListener);
        return rcv;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RvHolder holder, int position) {


        holder.appName.setText(log_list.get(position).getAppName());
        String img = log_list.get(position).getAppIcon();
        byte[] bitmapdata = Base64.decode(img, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        holder.appIcon.setImageBitmap(bitmap);

        holder.flRecycleViewItem.setTag(position);
        holder.flRecycleViewItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            view.startDragAndDrop(data, shadowBuilder, view, 0);
                        } else {
                            view.startDrag(data, shadowBuilder, view, 0);
                        }
                        return true;
                }
                return false;
            }
        });
//        holder.flRecycleViewItem.setOnDragListener(new AllitemDragListener1(mListener,context,mainActivity));
    }


    @Override
    public int getItemCount() {
        return this.log_list.size();
    }

   public SelecteditemDragListener getDragInstance() {
        if (mListener != null) {
            return new SelecteditemDragListener(mListener,context,mainActivity);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
    }

   public List<Program> getList() {
        return log_list;
    }

   public void updateList(List<Program> list) {
        log_list = list;
    }
}