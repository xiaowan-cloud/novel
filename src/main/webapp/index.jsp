<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>小说</title>
    <link rel="stylesheet" type="text/css" href="css/comment.css">
</head>
<body>
    <table>
        <tr><td>
            <form action="HelloNovel" method="post">
                <div>小说搜索：</div>
                <input type="text" name="search" width="600px" align="center">
                <input type="submit" value="确定">
            </form>
        </td></tr>
        <%
            List<String> list = (List)request.getAttribute("novels");
            if (null != list) {
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
        %>
        <tr><td><div><%= list.get(i)%></div></td></tr>
        <%
            }
             }
              }
            if (null != request.getSession().getAttribute("novelName")) {
        %>

        <tr>
            <td>
                <form action="HelloNovel" method="get">
                    <input type="submit" value="下一页">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>