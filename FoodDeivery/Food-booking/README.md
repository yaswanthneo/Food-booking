# Food-booking
Food Delivery Application using SpringBoot, APIs, React, My SQL

basic POST, PUT, GET, DELETE for all

4 users: Admin, Restaurant, Customer, DeliveryPerson

Tables: 

Restaurant:
id, name, phone, address, email, password

Customer:
id, name, phone, address, email, password

Delivery Person:
id, name, phone, email, password

Item:
id, name, description, price, category, quantity, preparationTime

Cart:
id, item names, item quantity, item price, total price

Order:
id, item names, item quantity, item price, total price, customer id, customer name, customer phone, customer address, 
orderStatus(accept/reject/sent for delivery-----by restaurant)
deliveryStatus(accept delivery/reject delivery/ delivered ----- by deliveryPerson)


ADMIN:
login with default username and password
logout
have default username and password in security configuration
can view all restaurants
can view all customers
can view all delivery persons
can delete restaurants


RESTAURANTS:
has id, name, phone, address
email and password for login

can signup
can login
can logout
can update their own details
can delete themselves
can view their own profile

Manage menu:
can add items
can update items
can delete items
can view items

can view delivery person details who will accept the order

once customer orders,
can view the order table with all details
they have to accept or reject the order


CUSTOMER:
has id, name, phone, address, 
email and password for login

can signup
can login
can logout
can update their own profile
can delete their own profile

view all restaurants
view all items

search restaurants/items by name

add item to cart
make payment/place order, order will be successfull

Once the restaurant receives order, they will accept/reject the order, it will be shown in customer side as OrderStatus.
Once the delivery person accepts their order, they will get the delivery person's details


DELIVERY PERSON:
has id, name, phone
email and password for delivery

can signup
can login
can logout 
can update their own profile
can delete their own profile

can view the order table 
can update status for order as accept/reject/delivered

