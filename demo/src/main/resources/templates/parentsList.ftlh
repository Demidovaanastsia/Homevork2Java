<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Фильтр родителей по имени</div>

    <form method="get" action="parentsList">
        <input type="text" name="filter">
        <button type="submit">Найти</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list parents as parent>
            <tr>
                <td>${parent.id}</td>
                <td>${parent.firstName} ${parent.lastName} ${parent.patronymicName}</td>
                <td>${parent.addressParent}</td>
                <td><a href="/parentChange/${parent.id}">edit</td>
            </tr>
        <#else>
            <tr>
                <td></td>
                <td>No</td>
                <td>Parents</td>
                <td></td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <div>Добавить нового родителя</div>

    <div>
        <form method="post">
            <input          type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input required type="text" name="firstName" placeholder="Имя" />
            <input required type="text" name="lastName" placeholder="Фамилия" />
            <input          type="text" name="patronymicName" placeholder="Отчество">
            <select name="addressId" required>
                <option selected disabled value = "">Выберите адрес проживания</option>
                <#list addresses as address>
                <option value="${address.id}" label="${address.title}" />
                <#else>
                <option disabled>No addresses</option>
                </#list>
            </select>
            <p><button type="submit">Добавить</button></p>
        </form>
    </div>

    <br>
    <div>
        <a href="/">Вернуться назад</a>
    </div>

    <br>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="submit" value="Sign Out"/>
        </form>
    </div>

</@c.page>