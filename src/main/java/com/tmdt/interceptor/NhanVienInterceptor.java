package com.tmdt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class NhanVienInterceptor implements Interceptor {
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
        if (loggedIn == null || !loggedIn) {
            return "login"; // global result ở file struts.xml
        }
        if ((int) session.getAttribute("level") == 0) {
            return "home"; // cấp độ quyền = 0 thì đá về trang chủ
        }
        return invocation.invoke();
    }
}
