package assignment05;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;


public class WebBrowserTest {

    private WebBrowser webBrowser;

    @BeforeEach
    public void setup() {
        webBrowser = new WebBrowser();
    }

    @Test
    public void testInitialBrowserState() {
        Assertions.assertNull(webBrowser.currentURL);
        Assertions.assertTrue(webBrowser.history().isEmpty());
    }

    @Test
    public void testVisitNewPage() throws MalformedURLException {
        URL url1 = new URL("http://example.com");
        webBrowser.visit(url1);
        Assertions.assertEquals(url1, webBrowser.currentURL);
    }

    @Test
    public void testBackFunctionality() throws MalformedURLException, NoSuchElementException {
        URL url1 = new URL("http://example.com");
        URL url2 = new URL("http://example.org");
        webBrowser.visit(url1);
        webBrowser.visit(url2);

        Assertions.assertEquals(url2, webBrowser.currentURL);
        Assertions.assertEquals(url1, webBrowser.back());
    }

    @Test
    public void testForwardFunctionality() throws MalformedURLException, NoSuchElementException {
        URL url1 = new URL("http://example.com");
        URL url2 = new URL("http://example.org");
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.back();

        Assertions.assertEquals(url1, webBrowser.currentURL);
        Assertions.assertEquals(url2, webBrowser.forward());
    }

    @Test
    public void testHistoryFunctionality() throws MalformedURLException {
        URL url1 = new URL("http://example.com");
        URL url2 = new URL("http://example.org");
        webBrowser.visit(url1);
        webBrowser.visit(url2);

        SinglyLinkedList<URL> history = webBrowser.history();
        Assertions.assertFalse(history.isEmpty());
        Assertions.assertEquals(url2, history.getFirst()); // Assuming getFirst() returns the most recent URL
    }

    @Test
    public void testVisitNullPage() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            webBrowser.visit(null);
        });
    }
    @Test
    public void testWebBrowserWithHistory() throws MalformedURLException {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        URL url1 = new URL("http://example.com");
        URL url2 = new URL("http://example.org");
        URL url3 = new URL("http://example.net");

        //url3 is the most recent visited，url1 is the least visited
        history.insertFirst(url1);
        history.insertFirst(url2);
        history.insertFirst(url3);
        WebBrowser webBrowser = new WebBrowser(history);
        // 检查当前 URL 是否设置为历史记录中的第一个元素
        Assertions.assertEquals(url3, webBrowser.currentURL);
        //TEST SIZE OF HISTORY LIST
        Assertions.assertEquals(2, webBrowser.backStackSize());
        // 检查后退栈是否包含剩余的历史记录
        Assertions.assertEquals(url2, webBrowser.back());
    }
}
