package bigpic.bean.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ButtonTag extends SimpleTagSupport {
    private int index;
    private String openStyle;
    private String deleteStyle;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setOpenStyle(String openStyle) {
        this.openStyle = openStyle;
    }

    public void setDeleteStyle(String deleteStyle) {
        this.deleteStyle = deleteStyle;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<a href='album/photo.jsp?photo=" + index + "' class='" + openStyle + "'>Open</a>");
        out.println("<a href='RemovePhotoServlet?photo=" + index + "' class='" + deleteStyle + "'>Delete</a>");
    }
}
