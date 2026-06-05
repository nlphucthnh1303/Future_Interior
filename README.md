# ASM Future Interior Project G2

## Giới thiệu

Dự án `ASM_Future-Interior_Project-G2` là một ứng dụng web thương mại điện tử nội thất xây dựng bằng Spring Boot.
Ứng dụng hỗ trợ front-end hiển thị bằng Thymeleaf/Tiles JSP, quản lý dữ liệu với PostgreSQL, và có nhiều tính năng chính như đăng nhập/đăng ký, sản phẩm, giỏ hàng, đơn hàng, email, xác thực và bảo mật.

## Công nghệ chính

- Java 21
- Spring Boot 3.0.8
- Spring Web, Spring Data JPA, Spring Security
- Spring Boot Starter Mail
- Spring Boot Starter OAuth2 Client & Resource Server
- PostgreSQL
- H2 (runtime chỉ để test)
- Thymeleaf
- Apache Tiles JSP
- JWT (io.jsonwebtoken)
- MapStruct
- Springdoc OpenAPI
- Lombok
- Apache POI
- reCAPTCHA

## Cấu trúc chính của dự án

- `src/main/java/com/spring/main`: mã nguồn Java chính
- `src/main/resources`: cấu hình, static assets và templates HTML
- `src/test/java`: mã kiểm thử
- `pom.xml`: cấu hình Maven và dependencies

## Cài đặt

1. Cài JDK 21
2. Cài Maven hoặc dùng `mvnw` có sẵn trong dự án
3. Tạo database PostgreSQL và tùy chỉnh cấu hình kết nối trong `src/main/resources/application.properties` hoặc `application-postgres.properties`
4. Nếu cần, cấu hình email, OAuth2, và reCAPTCHA trong file cấu hình

## Chạy ứng dụng

Ở thư mục gốc dự án, chạy lệnh:

```powershell
./mvnw clean package
./mvnw spring-boot:run
```

Hoặc với Maven cài đặt sẵn:

```powershell
mvn clean package
mvn spring-boot:run
```

## Cấu hình database

Có thể dùng `application-postgres.properties` để cấu hình PostgreSQL. Ví dụ:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Tính năng chính

- Đăng nhập/đăng ký người dùng
- Quản lý sản phẩm và danh mục
- Giỏ hàng và thanh toán đơn giản
- Email xác thực và phục hồi mật khẩu
- Xác thực bảo mật JWT và OAuth2
- Hỗ trợ upload file và xuất dữ liệu
- API OpenAPI/Swagger bằng Springdoc

## Tài nguyên front-end

- `src/main/resources/templates`: trang HTML hiển thị giao diện
- `src/main/resources/static`: tài nguyên tĩnh như CSS, JS, hình ảnh

## Triển khai

Dự án đóng gói dưới dạng `war`, có thể triển khai trên Tomcat hoặc môi trường Java web app tương thích.

## Ghi chú

- Dự án hiện dùng `spring-boot-starter-tomcat` với scope `provided` để đóng gói WAR.
- Nếu cần chạy trên môi trường nội bộ hoặc deploy lên server, hãy đảm bảo cấu hình `application.properties` phù hợp.
- Với mục đích phát triển, `spring-boot-devtools` đã được thêm để hỗ trợ reload nhanh.

## Liên hệ

Nếu cần mở rộng thêm các tính năng ecommerce như quản lý order, thanh toán VNPAY/MOMO, hoặc API REST tách riêng, hãy bổ sung tiếp các controller và service tương ứng.
