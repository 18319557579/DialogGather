package com.example.floatlayer.storage;

import android.widget.FrameLayout;

import java.util.Comparator;
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

    /**
     * 移除某宿主下的所有
     */
    public void removeHost(FrameLayout frameLayout) {
        hostMap.remove(frameLayout);
    }
    
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

        LabelSave labelSave = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            labelSave = new LabelSave(new PriorityQueue<>(new Comparator<InlineItem>() {
                @Override
                public int compare(InlineItem o1, InlineItem o2) {
                    if (o2.priority - o1.priority != 0) {  //意思是顺序能确定
                        return o2.priority - o1.priority;  //优先级降序（因为正数为交换顺序，那么就会将后面的元素排到前面去。而priority越大，代表优先级越高）
                    }

                    if (o1.timestamp - o2.timestamp > 0) {  //时间升序，先进入队列的排在前面
                        return 1;
                    } else if (o1.timestamp - o2.timestamp < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                    //todo 这里不直接使用long转int进行返回，是因为担心long转int会改变符号，不确定是否真的会
                }
            }));
        }
        labelMap.put(label, labelSave);
        return labelSave;
    }
}
