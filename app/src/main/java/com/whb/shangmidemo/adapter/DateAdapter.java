//package com.whb.shangmidemo.adapter;
//
//public class DateAdapter extends BaseQuickAdapter<DateItem, BaseViewHolder> {
//
//    private static final int VIEW_TYPE_DATE = 0;
//    private static final int VIEW_TYPE_EMPTY = 1;
//
//    public DateAdapter() {
//        super(R.layout.item_date); // 设置日期项的布局资源
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, DateItem item) {
//        if (item.getViewType() == VIEW_TYPE_DATE) {
//            // 处理日期项
//            Date date = item.getDate();
//            // 设置日期项的视图数据
//        } else {
//            // 处理空白项
//        }
//    }
//
//    @Override
//    protected int getDefItemViewType(int position) {
//        if (position % 7 == 0) {
//            // 每周的第一天，返回空白项的视图类型
//            return VIEW_TYPE_EMPTY;
//        }
//        return VIEW_TYPE_DATE; // 其他情况返回日期项的视图类型
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return getDefItemViewType(position);
//    }
//
//    @Override
//    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_EMPTY) {
//            return createBaseViewHolder(parent, R.layout.item_empty); // 设置空白项的布局资源
//        }
//        return super.onCreateDefViewHolder(parent, viewType);
//    }
//}
