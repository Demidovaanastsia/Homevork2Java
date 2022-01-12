<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Фильтр детей по имени</div>

    <form method="get" action="childrenList">
        <input type="text" name="filter">
        <button type="submit">Найти</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Educ.Inst</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list children as child>
            <tr>
                <td>${child.id}</td>
                <td>${child.firstName} ${child.lastName} ${child.patronymicName}</td>
                <td>${child.age}</td>
                <td>${child.getEducationalInstitution()}</td>
                <td><a href="/childChange/${child.id}">edit</td>
            </tr>
        <#else>
            <tr>
                <td></td>
                <td></td>
                <td>No Children</td>
                <td></td>
                <td></td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <div>Добавить нового ребенка</div>

    <div>
        <form method="post">
            <input          type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input required type="text" name="firstName" placeholder="Имя" />
            <input required type="text" name="lastName" placeholder="Фамилия" />
            <input          type="text" name="patronymicName" placeholder="Отчество">
            <input required type="number" min="0" step="1" max="99" name="age" placeholder="Возраст" />
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