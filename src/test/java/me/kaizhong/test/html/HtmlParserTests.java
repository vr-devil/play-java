package me.kaizhong.test.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class HtmlParserTests {

    @Test
    public void testParse() throws IOException {
        Document doc = Jsoup.connect("https://jsoup.org/").get();
        Assertions.assertTrue(StringUtils.isNotBlank(doc.title()));
        Assertions.assertEquals("jsoup Java HTML Parser, with the best of HTML5 DOM methods and CSS selectors.", doc.title());

        Elements tags = doc.getElementsByTag("meta");
        Assertions.assertTrue(tags.size() > 0);

        Optional<Element> descTag = tags.stream()
                .filter(e -> Objects.equals(e.attr("name"), "description"))
                .findFirst();

        Assertions.assertTrue(descTag.isPresent());

        String description = descTag.map(e -> e.attr("content")).orElse("");
        Assertions.assertTrue(StringUtils.isNotBlank(description));
        Assertions.assertEquals("Open source Java HTML parser, with the best of HTML5 DOM methods and CSS selectors, for easy data extraction.", description);
    }
}
