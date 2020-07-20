package com.wentuo.weizixun.callback;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack callBack;

    public SimpleItemTouchHelperCallBack(TouchCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 获取方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swip = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag, swip);
    }

    /**
     * 移动
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        callBack.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 删除
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        callBack.onItemDelete(viewHolder.getAdapterPosition());
    }
}
