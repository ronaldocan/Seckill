package org.raw;

import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/22.
 */
public class MyHandlerMapping implements HandlerMapping
{
    public HandlerExecutionChain getHandler(HttpServletRequest httpServletRequest) throws Exception {
        String methd = httpServletRequest.getMethod();
        String url = httpServletRequest.getRequestURI();
        if(url.startsWith("rona")){
            if (methd.equalsIgnoreCase("get")) {
                return null;
            } else if (methd.equalsIgnoreCase("post")) {
                return null;
            }
            else
                return null;
        }
        return null;
    }
}
