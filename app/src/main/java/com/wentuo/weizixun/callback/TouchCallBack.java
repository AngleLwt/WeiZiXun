package com.wentuo.weizixun.callback;

public interface TouchCallBack {
    //数据交换
    void onItemMove(int fromPositon, int toPosition);

    //数据删除
    void onItemDelete(int position);
}
