package com.tmdt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/** *NotLoggedInInterceptor, 
 * Kiểm tra xem người dùng đã đăng nhập hay chưa, nếu đã đăng nhập thì ko thể vào action login và register
*/
public class LoggedInInterceptor implements Interceptor {
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            return "home"; // global result ở file struts.xml
        }
        return invocation.invoke();
    }

}
