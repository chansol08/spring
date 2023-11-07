<%--
  Created by IntelliJ IDEA.
  User: chans
  Date: 2023-11-06 ~
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>member list</title>

    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>

    <script type="text/javascript">
        function deleteFn(number) {
            location.href = "/memberDelete.do?number=" + number;
        }

        function btnClick() {
            $.ajax({
                url: "<c:url value='/memberAjaxList.do' />",
                type: "get",
                dataType: "json",
                success: resultHtml,
                error: function() {
                    alert("error")
                }
            });
        }

        //btnClick callback function
        function resultHtml(data) {
            console.log(data);

            let html="<table class='table table-bordered'>";
            html += "<tr>";
            html += "<td>번호</td>";
            html += "<td>아이디</td>";
            html += "<td>비밀번호</td>";
            html += "<td>이름</td>";
            html += "<td>나이</td>";
            html += "<td>이메일</td>";
            html += "<td>전화번호</td>";
            html += "</tr>";
            $.each(data, function(index, obj) {
                html += "<tr>";
                html += "<td>" + obj.number + "</td>";
                html += "<td>" + obj.id + "</td>";
                html += "<td>" + obj.password + "</td>";
                html += "<td>" + obj.name + "</td>";
                html += "<td>" + obj.age + "</td>";
                html += "<td>" + obj.email + "</td>";
                html += "<td>" + obj.phone + "</td>";
                html += "</tr>";
            });
            html += "</table>";

            $("#list").html(html);
        }
    </script>
</head>
<body>
<table class="table table-bordered">
    <tr>
        <td>번호</td>
        <td>아이디</td>
        <td>비밀번호</td>
        <td>이름</td>
        <td>나이</td>
        <td>이메일</td>
        <td>전화번호</td>
        <td></td>
    </tr>
    <c:forEach var="member" items="${members}">
        <tr>
            <td>
                    ${member.number}
            </td>
            <td>
                <a href="/memberContent.do?number=${member.number}">${member.id}</a>
            </td>
            <td>
                    ${member.password}
            </td>
            <td>
                    ${member.name}
            </td>
            <td>
                    ${member.age}
            </td>
            <td>
                    ${member.email}
            </td>
            <td>
                    ${member.phone}
            </td>
            <td>
                <input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(${member.number})" />
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8" align="right">
            <input type="button" value="회원가입" class="btn btn-primary"
                   onclick="location.href='/memberRegister.do'"/>
        </td>
    </tr>
    <tr>
        <td colspan="8">
            <input type="button" value="Ajax 회원 리스트 보기" onclick="btnClick()" />
        </td>
    </tr>
</table>
<div id="list"></div>
</body>
</html>