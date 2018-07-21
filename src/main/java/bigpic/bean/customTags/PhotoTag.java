package bigpic.bean.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PhotoTag extends SimpleTagSupport {
    private int index;
    private String style;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<img class='"+style+"' src='album/photo.jsp?photo="+index+"'>");
    }
}
