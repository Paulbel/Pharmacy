package by.pharmacy.tag;

import javax.servlet.jsp.tagext.TagSupport;


public abstract class VisualisationTag extends TagSupport {
    private static final long serialVersionUID = 1637833248727599146L;

    protected abstract String getBody();
}
