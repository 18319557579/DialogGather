package com.example.floatlayer.storage;

import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.PriorityQueue;

public class FloatLayerStorage {


    private HashMap<FrameLayout, HashMap<String, LabelSave>> hostMap = new HashMap<>();  //根据宿主类型，找到标识map

/*    public HashMap<String, PriorityQueue<InlineItem>> labelMap = new HashMap<>();  //根据标识，找到元素队列

    PriorityQueue<InlineItem> priorityQueue = new PriorityQueue<>();*/

    public static class InlineItem {
        public int priority;  //优先级。默认值为0，为最低。数字越大，优先级越高
        public long timestamp;  //时间戳。相同优先级的情况下，时间越早，越先出队列
        public FloatLayerItem floatLayerItem;  //FloatLayer + Config

        public InlineItem(int priority, FloatLayerItem floatLayerItem) {
            this.priority = priority;
            this.timestamp = System.currentTimeMillis();
            this.floatLayerItem = floatLayerItem;
        }
    }

    public static class LabelSave {
        public InlineItem currentItem;
        public PriorityQueue<InlineItem> priorityQueue;

        public LabelSave(PriorityQueue<InlineItem> priorityQueue) {
            this.priorityQueue = priorityQueue;
        }
    }



    /*public void putFloatLayerItem(FrameLayout frameLayout, FloatLayerItem floatLayerItem) {
        putFloatLayerItem(frameLayout, "DEFAULT_LABEL", 0, floatLayerItem);
    }

    public void putFloatLayerItem(FrameLayout frameLayout, int priority, FloatLayerItem floatLayerItem) {
        putFloatLayerItem(frameLayout, "DEFAULT_LABEL", priority, floatLayerItem);
    }

    public void putFloatLayerItem(FrameLayout frameLayout, String label, FloatLayerItem floatLayerItem) {
        putFloatLayerItem(frameLayout, label, 0, floatLayerItem);
    }*/

    /**
     * 将元素放置到队列中
     */
    public void putFloatLayerItem(FrameLayout frameLayout, String label, int priority, FloatLayerItem floatLayerItem) {
        HashMap<String, LabelSave> labelMap = getHostUnderling(frameLayout, hostMap);
        PriorityQueue<InlineItem> inlineItemQueue = getLabelUnderling(label, labelMap).priorityQueue;
        InlineItem inlineItem = new InlineItem(priority, floatLayerItem);
        inlineItemQueue.offer(inlineItem);
    }

    /**
     * 从队列中获取第一个元素，就是此时优先级最高的FloatLayer
     */
    public FloatLayerItem getFloatLayerItem(FrameLayout frameLayout, String label) {
        HashMap<String, LabelSave> labelMap = getHostUnderling(frameLayout, hostMap);
        LabelSave labelSave = getLabelUnderling(label, labelMap);
        PriorityQueue<InlineItem> inlineItemQueue = labelSave.priorityQueue;
        InlineItem inlineItem = inlineItemQueue.poll();  //从队列中获取优先级最高的item，并移除
        if (inlineItem == null) return null;  //如果取不到元素，那么代表此时的队列为空

        labelSave.currentItem = inlineItem;  //设置当前显示的item
        return inlineItem.floatLayerItem;
    }

    /**
     * 是否有FloatLayer正在展示
     */
    public boolean isShowing(FrameLayout frameLayout, String label) {
        HashMap<String, LabelSave> labelMap = getHostUnderling(frameLayout, hostMap);
        LabelSave labelSave = getLabelUnderling(label, labelMap);
        return labelSave.currentItem != null;
    }

    /**
     * 设置当前没有在展示
     */
    public void setNoShow(FrameLayout frameLayout, String label) {
        HashMap<String, LabelSave> labelMap = getHostUnderling(frameLayout, hostMap);
        LabelSave labelSave = getLabelUnderling(label, labelMap);
        labelSave.currentItem = null;
    }
/*    *//**
     * 设置当前在显示的FloatLayer
     *//*
    public void setCurrentShow(FrameLayout frameLayout, String label) {
        HashMap<String, LabelSave> labelMap = getHostUnderling(frameLayout, hostMap);
        LabelSave labelSave = getLabelUnderling(label, labelMap);
        labelSave.currentItem =

    }*/
    
    private HashMap<String, LabelSave> getHostUnderling(FrameLayout frameLayout, HashMap<FrameLayout, HashMap<String, LabelSave>> hostMap) {
        HashMap<String, LabelSave> labelMap = hostMap.get(frameLayout);
        if (labelMap != null)
            return labelMap;

        HashMap<String, LabelSave> hashMap = new HashMap<>();
        hostMap.put(frameLayout, hashMap);
        return hashMap;
    }
    
    private LabelSave getLabelUnderling(String label, HashMap<String, LabelSave> labelMap) {
        LabelSave inlineItemQueue = labelMap.get(label);
        if (inlineItemQueue != null)
            return inlineItemQueue;

        LabelSave labelSave = new LabelSave(new PriorityQueue<>());
        labelMap.put(label, labelSave);
        return labelSave;
    }
}
