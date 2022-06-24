# SimpleRESTService
Simple Rest API по работе с банковским счетом
Maven, SpringWeb, Spring Data JPA, PostgreSQL

Доступны следующие операции, ответ выдается в виде JSON:

1.Узнать баланс по ID пользователя;
Пример запроса:
###
GET http://localhost:8080/accounts/2
 
2.Снятие заданной суммы с баланса пользователя;
Пример запроса:
###
POST http://localhost:8080/accounts/take
Content-Type: application/json

{
  "id": 7, "amount": 1200
}

3.Пополнение баланса на заданную сумму;
Пример запроса:
###
POST http://localhost:8080/accounts/put
Content-Type: application/json

{
  "id": 8, "amount": 555
}

4.Получение списка операций по ID пользователя, с возможностью выборки списка операций за выбранный диапазон времени, 
как с ограничением по датам, так и без.
Примеры запросов:
###
GET http://localhost:8080/accounts/7/operations?dateFrom=22.06.2022&dateTo=24.06.2022
###
GET http://localhost:8080/accounts/7/operations?dateFrom=22.06.2022
###
GET http://localhost:8080/accounts/7/operations?dateTo=22.06.2022
###
GET http://localhost:8080/accounts/7/operations


База данных из двух таблиц: Account, Operations:

![image](https://user-images.githubusercontent.com/71542264/175496357-b20762e1-cd81-42a1-9b31-e2636bf3ed2a.png)

