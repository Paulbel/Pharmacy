package by.pharmacy.tag;

import by.pharmacy.entity.Drug;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {
    private static final long serialVersionUID = 5035643944923937961L;
    private final static String OUTPUT_BODY = "<td><a href = \"FrontController\">%s</a></td>";
    private Drug drug;

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        try {
            out.write(String.format(OUTPUT_BODY, drug.getName()));
            return SKIP_BODY;
        } catch (IOException e) {
            throw new CustomTagException(e);
        }
    }
}
