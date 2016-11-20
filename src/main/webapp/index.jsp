<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Servlet based Web API</title>
</head>
<body bgcolor="#e6e6fa">
<div class="parent">
    <form action="result" method="get">
        <table class="out" align="center">
            <tr>
                <td bgcolor="#fa8072" align="center">
                    Servlet based Web API
                </td>
            </tr>
            <tr>
                <td>
                    <table class="inner">
                        <tr>
                            <td>Text:</td>
                            <td><input type="text" name="q" value="" title=""></td>
                        </tr>
                        <tr>
                            <td>Chars Limit:</td>
                            <td><input type="number" name="limit" min="0" title=""></td>
                        </tr>
                        <tr>
                            <td>String Length:</td>
                            <td><input type="number" name="length" min="0" title=""></td>
                        </tr>
                        <tr>
                            <td>Include Meta Data?</td>
                            <td align="center">
                                <select name="includeMetaData" title="">
                                    <option value="true">Yes</option>
                                    <option value="false">No</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input id="button" type="submit" value="Show results">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>