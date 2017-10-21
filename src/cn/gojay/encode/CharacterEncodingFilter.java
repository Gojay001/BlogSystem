package cn.gojay.encode;

import javax.servlet.*;
import java.io.IOException;

/**
 * 字符编码过滤器
 * 处理字符编码问题
 */
public class CharacterEncodingFilter implements Filter {
    private String encoding = null;
    private FilterConfig filterConfig;

    /**
     * 初始化配置
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        this.encoding = config.getInitParameter("encoding");
    }

    /**
     *实现销毁方法
     */
    @Override
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    /**
     * 进行具体的过滤
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (encoding != null) {
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html;charset=" + encoding);
        }
        chain.doFilter(request, response);
    }
}
