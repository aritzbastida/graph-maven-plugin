package org.kuali.maven.plugins.graph.dot;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.maven.plugins.graph.dot.html.copy.Br;
import org.kuali.maven.plugins.graph.dot.html.copy.Font;
import org.kuali.maven.plugins.graph.dot.html.copy.HtmlUtils;
import org.kuali.maven.plugins.graph.dot.html.copy.Img;
import org.kuali.maven.plugins.graph.dot.html.copy.Table;
import org.kuali.maven.plugins.graph.dot.html.copy.TableCell;
import org.kuali.maven.plugins.graph.dot.html.copy.TableRow;
import org.kuali.maven.plugins.graph.dot.html.enums.Align;
import org.kuali.maven.plugins.graph.dot.html.enums.CellAlign;
import org.kuali.maven.plugins.graph.dot.html.enums.Scale;

public class HtmlUtilTest {

    HtmlUtils h = new HtmlUtils();

    @Test
    public void testBr() {
        String expected = "<br align=\"LEFT\"/>";
        Br br = new Br(Align.LEFT);
        Assert.assertEquals(expected, h.toHtml(br));
    }

    @Test
    public void testImg() {
        String expected = "<img scale=\"BOTH\" src=\"http://www.yahoo.com\"/>";
        Img img = new Img(Scale.BOTH, "http://www.yahoo.com");
        String s = h.toHtml(img);
        Assert.assertEquals(expected, s);
    }

    @Test
    public void testFont() {
        String expected = "<font color=\"black\" point-size=\"14\"><br align=\"LEFT\"/></font>";
        Br br = new Br(Align.LEFT);
        Font font = new Font("black", 14);
        font.setContent(h.toHtml(br));
        String s = h.toHtml(font);
        Assert.assertEquals(expected, s);
    }

    @Test
    public void testCell() {
        String expected = "<td align=\"LEFT\">http://www.yahoo.com</td>";
        TableCell cell = new TableCell("http://www.yahoo.com");
        cell.setAlign(CellAlign.LEFT);
        String s = h.toHtml(cell);
        Assert.assertEquals(expected, s);
    }

    @Test
    public void testRow() {
        String expected = "<tr><td align=\"LEFT\">http://www.yahoo.com</td></tr>";
        TableCell cell = new TableCell("http://www.yahoo.com");
        cell.setAlign(CellAlign.LEFT);
        TableRow row = new TableRow(cell);
        String s = h.toHtml(row);
        Assert.assertEquals(expected, s);
    }

    @Test
    public void testTable() {
        String expected = "<table><tr><td align=\"LEFT\">http://www.yahoo.com</td></tr></table>";
        TableCell cell = new TableCell("http://www.yahoo.com");
        cell.setAlign(CellAlign.LEFT);
        TableRow row = new TableRow(cell);
        Table table = new Table(row);
        String s = h.toHtml(table);
        Assert.assertEquals(expected, s);
    }
}
