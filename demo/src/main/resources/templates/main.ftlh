<#import "parts/common.ftlh" as c>

<@c.page>

<div>Hello, user</div>

<div>
    <a href="/parentsList">Parents</a>
    <br>
    <a href="/childrenList">Children</a>
</div>

<div>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>

</@c.page>