//导入必需的 java 库
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//实现 Filter 类
public class CorsFilter implements Filter  {
    public void  init(FilterConfig config) throws ServletException {

    }
    public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.setHeader("Access-Control-Allow-Origin",httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        httpResponse.setHeader("Access-Control-Max-Age", "0");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,authorization,mbs_token");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("XDomainRequestAllowed","1");
        /*if("OPTIONS"==httpRequest.getMethod()){
            chain.doFilter(request, response);
            System.exit(0);
        }*/
        chain.doFilter(request, response);
    }
    public void destroy( ){
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
    }
}