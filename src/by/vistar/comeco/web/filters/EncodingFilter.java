package by.vistar.comeco.web.filters;


import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam!=null && !"".equals(encodingParam)){
            encoding=encodingParam;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html; charset=" + encoding);
        servletResponse.setCharacterEncoding(encoding);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }
}
