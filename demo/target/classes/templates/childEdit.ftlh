<#import "parts/common.ftlh" as c>

<@c.page>

Child editor

<div>
    <form action="/childChange" method="post">
        <input          type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input          type="hidden" name="childId" value="${child.id}" />
        <input required type="text" name="firstName" placeholder="Имя" value="${child.firstName}" />
        <input required type="text" name="lastName" placeholder="Фамилия" value="${child.lastName}" />
        <input          type="text" name="patronymicName" placeholder="Отчество" value="${child.patronymicName}">
        <input required type="number" min="0" step="1" max="99" name="age" placeholder="Возраст" value="${child.age}" />
        <select name="edInstId" ${hasEducInst?then("required","")}>
            <option ${hasEducInst?then("","selected")} disabled value = "">Выберите адрес Учебного заведения</option>
            <#list edInsts as edInst>
            <option ${hasEducInst?then(child.educationalInstitution?contains(edInst.number)?string("selected",""),"")} value="${edInst.id}">${edInst.number}</option>
            <#else>
            <option disabled>No Education Institutions</option>
            </#list>
        </select>
        <p><button type="submit">Сохранить</button></p>
    </form>
</div>

<div>
    <a href="/childrenList">К полному списку</a>
</div>

</@c.page>