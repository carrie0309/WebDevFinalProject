/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author NicolasZHANG
 */
@WebFilter(filterName = "AntiSqlInjectionFilter", urlPatterns = "/*")
public class AntiSqlInjectionFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     *
     * @param arg0
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

    /**
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //System.out.println("============================SQL"+sql);
        //有sql关键字，跳转到error.html
        if (sqlValidate(sql)) {
            throw new IOException("There are illegal characters in your input!");
            //String ip = req.getRemoteAddr();
        } else {
            chain.doFilter(req, res);
        }
    }

    //效验
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.contains(badStrs[i])) {
                return true;
            }
        }
        return false;
    }
}
