<#import "parts/common.ftlh" as c>

<@c.page>
Login page

    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Sign In"/></div>
    </form>

</@c.page>