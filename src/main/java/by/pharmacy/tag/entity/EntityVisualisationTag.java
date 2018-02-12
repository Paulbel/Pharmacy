package by.pharmacy.tag.entity;

import by.pharmacy.tag.VisualisationTag;
import by.pharmacy.tag.exception.CustomTagException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public abstract class EntityVisualisationTag extends VisualisationTag {
    private static final long serialVersionUID = -8095288295985204971L;
    private final static String TAG_BODY = "<a href = %s>%s";
    private final static String CLOSE_TAG = "</a>";
    protected String command;

    public void setCommand(String command) {
        this.command = command;
    }

    protected String createHref() {
        if (command == null) {
            return "#";
        } else {
            return "\"FrontController?command=" + command + "\"";
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(String.format(TAG_BODY, createHref(), getBody()));
            return SKIP_BODY;
        } catch (IOException e) {
            throw new CustomTagException(e);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(CLOSE_TAG);
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new CustomTagException(e);
        }
    }
}
