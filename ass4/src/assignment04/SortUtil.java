package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ass4.SortUtil class provide implementation of mergesort and quicksort function
 * INSERTION_SORT_THRESHOLD defines the time use insertion
 */
public class SortUtil {
//    public static int INSERTION_SORT_THRESHOLD = 5;

    /**
     *This method is to generate array with ascending order
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * This method is to generate random array
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> list = generateBestCase(size);
        Collections.shuffle(list);
        return list;
    }

    /**
     * Generate array with totally reverse order
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = size; i > 0; i--) {
            list.add(i);
        }
        return list;
    }


    /**
     * @param <T>type of element in the list
     * @param list the ArrayList to be sorted
     * @param cmp the rules of order elements
     */
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> cmp,int INSERTION_SORT_THRESHOLD){
        if(list.size()<=1){
            return; //terminates the current recursive call
        }
        if (list.size() <= INSERTION_SORT_THRESHOLD) {
            insertionSort(list, cmp);
            return;
        }
        int middle = list.size()/2;
        ArrayList<T> right = new ArrayList<>(list.subList(0,middle));
        ArrayList<T> left = new ArrayList<>(list.subList(middle, list.size()));
        mergesort(left,cmp,INSERTION_SORT_THRESHOLD);
        mergesort(right,cmp,INSERTION_SORT_THRESHOLD);
        merge(list,right,left,cmp);
    }
    private static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> cmp) {
        // 实现插入排序
        for(int i = 1;i< list.size();i++){
            T current = list.get(i);
            int j = i-1;
            // 将当前元素与已排序部分的元素比较，并移动已排序元素
            while((j >= 0) && (cmp.compare(current,list.get(j))<0)){
                    list.set(j+1,list.get(j));
                    j--;
            }
            list.set(j+1,current);
        }
    }
    private static <T> void merge(ArrayList<T>list,ArrayList<T>left,ArrayList<T>right, Comparator<? super T> cmp){
        int l = 0;
        int r = 0;
        int res = 0;
        while(l < left.size() && r< right.size()){
            if(cmp.compare(left.get(l), right.get(r))<0){
                list.set(res,left.get(l));
                res++;
                l++;
            }else {
                list.set(res,right.get(r));
                res++;
                r++;
            }
        }
        //in case size of left and right is not even
        // 复制左子列表剩余的元素
        while (l < left.size()) {
            list.set(res++, left.get(l++));
        }
        // 复制右子列表剩余的元素
        while (r < right.size()) {
            list.set(res++, right.get(r++));
        }
    }
    /**
     * Quick Sort
     * Three strategy of picking pivot
     */
    public enum PivotStrategy{
        FIRST_ELEMENT,
        LAST_ELEMENT,
        RANDOM_ELEMENT,
    }

    /**
     *
     * @param list array need to be sorted
     * @param cmp  the rules of order elements
     * @param <T> type of element in the list
     */
    public static <T> void quicksort(ArrayList<T>list, Comparator<? super T>cmp,PivotStrategy strategy){

         if(list == null || list.size()<=1){
             return;
         }
        quicksortInternal(list,0, list.size()-1, cmp,strategy);

    }
    public static <T> void quicksortInternal(ArrayList<T> list, int low, int high, Comparator<? super T> cmp,PivotStrategy strategy){
        if(low <high){
            int partitionIndex = partition(list,low,high,cmp,strategy);
            quicksortInternal(list,low,partitionIndex -1,cmp,strategy);//recursive left side of pivot
            quicksortInternal(list,partitionIndex +1,high,cmp,strategy);//recursive right side of pivot
        }
    }


    /**
     * @param list
     * @param low
     * @param high
     * @param cmp
     * @return
     * @param <T>
     */
    private static <T> int partition(ArrayList<T> list, int low, int high, Comparator<? super T> cmp,PivotStrategy strategy){
        switch (strategy){
            case LAST_ELEMENT:
                break;
            case FIRST_ELEMENT:
                swap(list,low,high);
                break;
            case RANDOM_ELEMENT:
                int randomIndex = low + (int) (Math.random() * (high - low));
                swap(list,randomIndex,high);
                break;
        }

        T pivot =list.get(high);
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(cmp.compare(list.get(j),pivot) <= 0){
                i++;//i index point at the last one <= pivot,because we add new one so i++
                swap(list,i,j);//let element before i not bigger than pivot
            }
        }
        swap(list,i+1,high);//element before pivot less than it, after pivot bigger than it
        return i+1;//index of pivot
    }

    private static <T> void swap(ArrayList<T> list, int i, int j){
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j,temp);
    }
}
