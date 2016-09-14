import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by svanlaar on 13/09/2016.
 */
public class jSoup  {


    @Test
    public void jsoup_test_check_title() throws IOException {
        Document doc = Jsoup.connect("http://www.hotels.com").get();
        String title = doc.title();

        System.out.println("Title is " + title);
        Assert.assertTrue(title.contains("Hotels.com"));
    }


    @Test
    public void jsoup_bit_more_complex() throws IOException {
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            System.out.println("\nlink : " + link.attr("href"));
            System.out.println("text : " + link.text());
        }
    }

}
