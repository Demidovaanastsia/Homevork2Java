<#import "parts/common.ftlh" as c>

<@c.page>

Parent editor

<div>
    <form action="/parentChange" method="post">
        <input          type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input          type="hidden" name="parentId" value="${parent.id}" />
        <input required type="text" name="firstName" value="${parent.firstName}" />
        <input required type="text" name="lastName" value="${parent.lastName}" />
        <input          type="text" name="patronymicName" value="${parent.patronymicName}" />
        <select name="addressId" required>
            <option disabled value = "">Выберите адрес проживания</option>
            <#list addresses as address>
            <option ${parent.addressParent?contains(address.title)?string("selected","")} value="${address.id}">${address.title}</option>
            <#else>
            <option disabled>No addresses</option>
            </#list>
        </select>
        <button type="submit">Save</button>
    </form>
</div>


<br>
<p>
<div>
    Have children
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list children as child>
            <tr>
                <td>${child.id}</td>
                <td>${child.firstName} ${child.lastName} ${child.patronymicName}</td>
                <td>${child.age}</td>
                <td><a href="/childChange/${child.id}">edit</td>
            </tr>
        <#else>
            <tr>
                <td></td>
                <td>No</td>
                <td>Children</td>
                <td></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</p>


<br>
<p>
    <div>
        <form action="/addChild" method="post">
            <input          type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input          type="hidden" name="parentId" value="${parent.id}" />
            <select name="childId" required>
                <option disabled value = "" selected>Список детей</option>
                <#list allChildren as child>
                <option value="${hasChild?then(children?seq_contains(child)?string("","${child.id}"),"${child.id}")}"
                ${hasChild?then(children?seq_contains(child)?string("disabled",""),"")}>
                ${child.firstName} ${child.lastName} ${child.patronymicName} </option>
                <#else>
                <option disabled>No Children</option>
                </#list>
            </select>
            <button type="submit">Add child</button>
        </form>
    </div>
</p>

<div>
    <a href="/parentsList">К полному списку</a>
</div>

</@c.page>