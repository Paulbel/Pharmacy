package by.pharmacy.tag.pagination;

import by.pharmacy.tag.VisualisationTag;
import by.pharmacy.tag.exception.CustomTagException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class PaginationTag extends VisualisationTag {
    private static final String START_TAG = "<ul class=\"pagination\">";
    private static final String LEFT_ARROW = "<a href=\"FrontController?command=%s&page=%d\">«</a></li>";
    private static final String RIGHT_ARROW = "<a href=\"FrontController?command=%s&page=%d\">»</a>";
    private static final String NAVIGATION_BUTTON = "<a href=\"FrontController?command=%s&page=%d\">%d</a></li>";
    private static final String DISABLED_LI = "<li class=\"disabled\">";
    private static final String ACTIVE_LI = "<li class=\"active\">";
    private static final String LI = "<li>";
    private static final String CLOSE_TAG = "</ul>";
    private static final long serialVersionUID = 8093638926414399024L;
    private String command;
    private int currentPage;
    private int pageNumber;


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print(START_TAG);
            if (currentPage != 1) {
                out.print(LI);
            } else {
                out.print(DISABLED_LI);
            }

            return SKIP_BODY;
        } catch (IOException e) {
            throw new CustomTagException(e);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (this.currentPage < this.pageNumber) {
                out.print(LI);
            } else {
                out.print(DISABLED_LI);
            }
            out.print(String.format(RIGHT_ARROW, command, currentPage + 1));
            out.print(CLOSE_TAG);
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new CustomTagException(e);
        }
    }

    @Override
    protected String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(LEFT_ARROW, command, currentPage - 1));

        for (int number = 1; number <= pageNumber; number++) {
            if (currentPage == number) {
                stringBuilder.append(ACTIVE_LI);
            } else {
                stringBuilder.append(LI);
            }
            stringBuilder.append(String.format(NAVIGATION_BUTTON, command, number, number));
        }
        return String.valueOf(stringBuilder);
    }
}
