# MyRetail RESTful services

RESTful services that can retrieve product and price details by ID and update the product price as well.

Prerequisites:
--------------
1. JDK 1.8
2. Intellij Idea/ Eclipse IDE
3. Maven 3.8.5
4. Install Mongo DB: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
5. Postman

Setting up:
-------------
1. Create collection in MongoDB PRODUCT_PRICE and insert the data using the file PRODUCT_PRICE_DML from myretail\data\PRODUCT_PRICE_DML
2. Refer application.properties to see server port and mongoDB port configuration
3. Clone project from Github : https://github.com/GRV1408/Spring-boot-myRetail
4. import/open the project in IDE and run following commond -> mvn clean install
5. To run the application go to MyretailApplication.Java -> right click -> Run MyretailApplication OR run following commond -> mvn spring-boot:run
6. To Test the application Use Postman and import -> MyRetail.postman_collection.json file from myretail\data\ Or Run the test classes from test folder.

Postman Screenshots:
-------------
1. GET - SUCCESS - http://localhost:8080/products/13860428

![img_5.png](img_5.png)

2. PUT - SUCCESS - http://localhost:8080/products/54456119
         Requestbody - {"id": 54456119,"name": "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;","current_price": {"value": 23.49,"currency_code": "USD"}}

![img_6.png](img_6.png)
3. GET - FAIL

![img_7.png](img_7.png)
4. PUT - FAIL

![img_8.png](img_8.png)

![img_9.png](img_9.png)