package com.example.demo.servlet;

import com.example.demo.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wan
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年08月20日 11:26:27
 */
@WebServlet("/HelloNovel")
public class HelleNovel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String novelName = (String) request.getSession().getAttribute("novelName");
        int pageNum = (int) request.getSession().getAttribute("pageNum");
        System.out.println("当前页数："+pageNum);
        List<String> list = FileUtil.getNovel(novelName, pageNum);
        request.getSession().setAttribute("pageNum",pageNum+1);
        request.setAttribute("novels",list);

        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String search = (String) request.getParameter("search");
        String path = FileUtil.getPath(search);
        if (null == path) {
//            PrintWriter out = response.getWriter();
//            out.flush();
//            out.println("<script>");
//            out.println("alert('小说未找到，请重新输入！');");
//            out.println("history.back();");
//            out.println("</script>");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else {
            request.getSession().setAttribute("novelName",search);

            if (null == request.getSession().getAttribute("pageNum")) {
                request.getSession().setAttribute("pageNum",1);
            }
            int pageNum = (int)request.getSession().getAttribute("pageNum");
            request.setAttribute("pageNum",pageNum+1);
            doGet(request,response);
        }
    }
}
