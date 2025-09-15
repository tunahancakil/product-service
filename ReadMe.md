# ProductService API

This project is a Spring Boot application for managing products, orders, order items.

## Requirements

- Java 17+
- Gradle
- MySQL

## API Endpoints

### Order

- `GET /orders` — List all orders
- `GET /orders/{id}` — Get order by ID
- `POST /orders` — Create a new order
- `PUT /orders/{id}` — Update an order
- `DELETE /orders/{id}` — Delete an order

### OrderItem

- `GET /order-items` — List all order items
- `GET /order-items/{id}` — Get order item by ID
- `POST /order-items` — Create a new order item
- `DELETE /order-items/{id}` — Delete an order item

### Product

- `GET /products` — List all products
- `GET /products/{id}` — Get product by ID
- `POST /products` — Create a new product

## Usage

1. Clone the repository.
2. Configure your database in `application.properties`.
3. Build and run the project:

   ```sh
   ./gradlew bootRun