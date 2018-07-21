package bigpic.bean.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTag extends SimpleTagSupport {
    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatedTime = sdf.format(now);
        out.println(formatedTime);
    }
}
