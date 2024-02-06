package com.anbai.sec.vuls.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(
			ServletRequest req, ServletResponse resp,
			FilterChain chain  // 可能有上游，可能有下游
	) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		// 创建HttpServletRequestWrapper，包装原HttpServletRequest对象，示例程序只重写了getParameter方法，
		// 应当考虑如何过滤：getParameter、getParameterValues、getParameterMap、getInputStream、getReader
		//      baidu.com/comment?       title=" <script> alert(1) </script> "
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				// 获取参数值
				String value = super.getParameter(name);        // title

				// " script> alert(1) /script> "
				// 简单转义参数值中的特殊字符
				return value.replace("&", "&amp;").replace("<", "&lt;").replace("'", "&#039;");
			}
		};

		chain.doFilter(requestWrapper, resp);        /* 到链条的下一个。也就是  下游的      doFilter(  ServletRequest req, ServletResponse resp, FilterChain chain  ) */
	}

	@Override
	public void destroy() {

	}

}
