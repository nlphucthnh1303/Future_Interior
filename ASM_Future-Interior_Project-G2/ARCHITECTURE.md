# Modernization Plan for Future Interior E-commerce

## 1. Backend Architecture (Spring Boot)

### Mục tiêu
- Tách biệt RESTful API và front-end.
- Xây dựng backend theo chuẩn Layered Architecture.
- Hỗ trợ JWT + OAuth2, validation, global exception handling, mapping DTO.

### Cấu trúc thư mục đề xuất
```
src/main/java/com/spring/main
 ├── config
 │     ├── SecurityConfig.java
 │     ├── SwaggerConfig.java
 ├── controller
 │     ├── auth
 │     │    ├── AuthController.java
 │     │    ├── OAuthController.java
 │     ├── product
 │     │    ├── ProductController.java
 │     ├── order
 │     │    ├── OrderController.java
 │     ├── cart
 │     │    ├── CartController.java
 ├── service
 │     ├── auth
 │     │    ├── AuthService.java
 │     │    ├── AuthServiceImpl.java
 │     ├── product
 │     │    ├── ProductService.java
 │     │    ├── ProductServiceImpl.java
 │     ├── order
 │     │    ├── OrderService.java
 │     │    ├── OrderServiceImpl.java
 │     ├── payment
 │     │    ├── PaymentService.java
 ├── repository
 │     ├── ProductRepository.java
 │     ├── OrderRepository.java
 │     ├── UserRepository.java
 ├── dto
 │     ├── request
 │     │    ├── LoginRequest.java
 │     │    ├── CheckoutRequest.java
 │     ├── response
 │     │    ├── ProductResponse.java
 │     │    ├── OrderResponse.java
 ├── exception
 │     ├── GlobalExceptionHandler.java
 │     ├── ApiError.java
 │     ├── ResourceNotFoundException.java
 ├── security
 │     ├── jwt
 │     │    ├── JwtTokenProvider.java
 │     │    ├── JwtAuthenticationFilter.java
 │     ├── user
 │     │    ├── CustomUserDetailsService.java
 │     │    ├── UserPrincipal.java
 ├── util
 │     ├── ResponseUtils.java
 └── Application.java
```

### Các layer chính
- `controller`: Nhận request, trả response JSON.
- `service`: Xử lý nghiệp vụ, transaction, validation business.
- `repository`: truy vấn DB qua Spring Data JPA.
- `dto`: tách domain entity khỏi payload trao đổi với front-end.
- `exception`: xử lý lỗi toàn cục.
- `security`: auth/authorization JWT + OAuth2.

## 2. Dependencies đề xuất cho `pom.xml`

### Spring core
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `spring-boot-starter-security`
- `spring-boot-starter-oauth2-client`
- `spring-boot-starter-oauth2-resource-server`
- `spring-boot-starter-mail`
- `spring-boot-starter-actuator`

### DB / mapping
- `org.postgresql:postgresql`
- `org.mapstruct:mapstruct`
- `org.mapstruct:mapstruct-processor`
- `com.fasterxml.jackson.datatype:jackson-datatype-jsr310`

### JWT
- `io.jsonwebtoken:jjwt-api`
- `io.jsonwebtoken:jjwt-impl`
- `io.jsonwebtoken:jjwt-jackson`

### API documentation
- `org.springdoc:springdoc-openapi-starter-webmvc-ui`

### Utility
- `org.projectlombok:lombok`
- `org.apache.commons:commons-lang3`
- `com.vladmihalcea:hibernate-types-52` (nếu cần JSON field hoặc array mapping)

## 3. Front-end stack đề xuất

### Chọn: `Next.js` + `React` + `TypeScript`

Lý do:
- Hỗ trợ SSR/SSG, tốt cho SEO nội thất.
- Dễ xây dựng UI minimal, responsive, mobile-first.
- Tách rõ ràng với backend Spring Boot qua REST API.
- Có hệ sinh thái lớn cho design system, animation, performance.

### Thư viện front-end đề xuất
- `next` + `react` + `react-dom`
- `typescript`
- `tailwindcss` hoặc `chakra-ui`
- `axios`
- `react-query` hoặc `swr`
- `react-hook-form` + `yup`
- `next-auth` hoặc custom OAuth flow nếu dùng Google/Facebook

## 4. Tiếp theo

### Bước 3: Tính năng e-commerce cần bổ sung
- Auth JWT + OAuth2
- Filter sản phẩm theo kích thước, chất liệu, màu sắc, khoảng giá, loại nội thất
- Giỏ hàng, checkout, đơn hàng, trạng thái vận chuyển
- Payment integration: VNPAY / Momo / Paypal
- Review, rating, wishlist
- Admin product management, order tracking, báo cáo doanh thu

### Bước 4: Database và API
- Thêm bảng: `product_images`, `product_variants`, `product_reviews`, `shipping_address`, `payment_transaction`, `order_status_history`
- API mẫu: `/api/v1/products`, `/api/v1/products/{id}`, `/api/v1/orders`, `/api/v1/orders/{id}`

> Tôi đã tổ chức lại `pom.xml` để hướng dự án về kiến trúc backend REST hiện đại và chuẩn bị template backend cho API tách rời.
