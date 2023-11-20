package assignment05;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private Stack<URL> backStack;
    private Stack<URL> forwardStack;
    //can be any object that implement Stack<E>
    public URL currentURL;
    public WebBrowser(){
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        currentURL = null;
    }
    public int backStackSize(){
        return backStack.size();
    }
    public WebBrowser(SinglyLinkedList<URL> history){
        this(); // 调用无参构造函数以初始化 backStack 和 forwardStack
        if(!history.isEmpty()){
            // 将历史记录中的第一个URL设置为当前URL，并将其余URL添加到后退栈
            currentURL = history.deleteFirst();
        } //set the first URL as current url
        //rest of history add to backstack
        while(!history.isEmpty()){
            backStack.push(history.deleteFirst());
        }
    }
    public void visit(URL webpage) {
        if (webpage == null) {
            throw new IllegalArgumentException("Cannot visit a null webpage");
        }
        if(currentURL != null){
            backStack.push(currentURL);
        }
        currentURL = webpage; // 更新当前页面
        forwardStack.clear(); // 清空前进栈
    }
    public URL back() throws NoSuchElementException {
        if (backStack.isEmpty()) {
            throw new NoSuchElementException("No previous page");
        }
        forwardStack.push(currentURL); // push current page to forward stack
        // 当前页面移动到前进栈
        currentURL = backStack.pop(); // set current page as first page on the backStack
        // 后退到上一个页面
        return currentURL;
    }

    public URL forward() throws NoSuchElementException {
        if (forwardStack.isEmpty()) {
            throw new NoSuchElementException("No next page");
        }
        backStack.push(currentURL); // 将当前页面移动到后退栈
        currentURL = forwardStack.pop(); // 前进到下一个页面
        return currentURL;
    }
    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
        LinkedListStack<URL> tempStack = new LinkedListStack<>();
        // 将后退栈中的元素弹出并临时存储，以保持原栈不变
        while (!backStack.isEmpty()) {
            URL url = backStack.pop();
            tempStack.push(url); // 将URL存储在临时栈中
        }
        // 将临时栈中的元素重新压入后退栈
        while (!tempStack.isEmpty()) {
            URL url = tempStack.pop();
            historyList.insertFirst(url); // 将URL添加到历史列表
            backStack.push(url);
        }
        // 如果有当前页面，添加到历史记录列表的头部
        if (currentURL != null) {
            historyList.insertFirst(currentURL);
        }
        return historyList;
    }

}
