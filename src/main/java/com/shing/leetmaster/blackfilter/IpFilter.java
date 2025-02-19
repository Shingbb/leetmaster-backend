package com.shing.leetmaster.blackfilter;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * IP 过滤器
 * 该过滤器用于获取请求中的客户端 IP 地址，并将其存储到请求属性中，供后续使用。
 * 在过滤器中，我们处理了获取真实 IP 地址的逻辑，特别是在代理服务器后面的情况。
 */
// todo 取消注释开启
//@Component  // 将此过滤器注册为 Spring 管理的 Bean
public class IpFilter implements Filter {

    // 使用 SLF4J 日志记录客户端的 IP 地址
    private static final Logger logger = LoggerFactory.getLogger(IpFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，可以用来做过滤器的初始化配置
        logger.info("IpFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 获取客户端的真实 IP 地址
        String clientIp = getClientIp(httpRequest);

        // 将 IP 地址存储到请求属性中，供后续业务逻辑使用
        httpRequest.setAttribute("clientIp", clientIp);

        // 记录日志，可以帮助调试
        logger.info("Client IP: {}", clientIp);

        // 将请求传递给下一个过滤器或处理器
        chain.doFilter(request, response);
    }

    /**
     * 获取客户端的真实 IP 地址
     * @param request HttpServletRequest 对象
     * @return 客户端的 IP 地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        // 如果 X-Forwarded-For 头部为空或包含 unknown，尝试从 X-Real-IP 获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        // 如果 X-Real-IP 为空或包含 unknown，最终从请求的 remoteAddr 获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 如果是通过负载均衡等代理来访问，X-Forwarded-For 可能包含多个 IP，取第一个非空的 IP 地址
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    @Override
    public void destroy() {
        // 销毁方法，可以用来释放资源
        logger.info("IpFilter destroyed");
    }
}