# Ứng Dụng Bán Nông Sản – Nối Gần Người Mua và Người Bán

## Nhóm sinh viên thực hiện
- **21110476 - Nguyễn Thị Ánh Huyền**  
- **22110414 - La Nguyễn Phúc Thành**

---

## Giới thiệu

Trong bối cảnh nhu cầu sử dụng công nghệ để kết nối sản phẩm nông nghiệp với người tiêu dùng ngày càng tăng cao, việc xây dựng một **ứng dụng bán nông sản** không chỉ mang ý nghĩa học thuật mà còn là một giải pháp thực tiễn hướng đến phát triển nông nghiệp số.

Thay vì lựa chọn các đề tài đã quen thuộc, nhóm chúng em quyết định tập trung vào **lĩnh vực nông sản** – một lĩnh vực tưởng chừng truyền thống nhưng đang khát khao đổi mới trong thời đại chuyển đổi số.

> Nhiều nông dân và hợp tác xã vẫn gặp khó khăn trong việc tiếp cận khách hàng, quảng bá sản phẩm và tìm đầu ra ổn định. Trong khi đó, người tiêu dùng lại có nhu cầu tìm mua nông sản sạch, rõ nguồn gốc, nhưng lại thiếu thông tin và kênh tiếp cận đáng tin cậy.

Khoảng trống này chính là động lực để nhóm xây dựng **một ứng dụng di động giúp kết nối trực tiếp người mua và người bán**, giảm thiểu trung gian, tăng hiệu quả tiêu thụ và minh bạch hóa thị trường.

Đây cũng là cơ hội để nhóm áp dụng kiến thức đã học về:
- Lập trình di động
- Quản lý cơ sở dữ liệu
- Thiết kế trải nghiệm người dùng
- Tích hợp các công nghệ hiện đại

Ứng dụng không chỉ là sản phẩm công nghệ, mà còn là một **nỗ lực đóng góp nhỏ vào việc nâng tầm giá trị nông sản Việt Nam** trong bối cảnh hội nhập và chuyển mình số.

---

## Nội dung chính

### 1. Mô tả chung

Ứng dụng bán nông sản là một hệ thống **thương mại điện tử đơn giản**, được thiết kế nhằm kết nối **người nông dân hoặc nhà cung cấp nông sản** với **người tiêu dùng**.

Tính năng chính:
- Tìm kiếm sản phẩm theo tên, danh mục hoặc nhà cung cấp
- Xem chi tiết sản phẩm (hình ảnh, giá, đơn vị tính, nguồn gốc)
- Thêm sản phẩm vào giỏ hàng và đặt mua trực tuyến
- Quản lý đơn hàng và theo dõi tình trạng giao hàng
- Lưu địa chỉ giao nhận và đánh giá sản phẩm
- Gợi ý sản phẩm theo mức độ phổ biến hoặc khuyến mãi

---

### 2. Công nghệ sử dụng

#### **Frontend (Mobile App)**
- **Nền tảng**: Android Studio
- **Ngôn ngữ**: Java
- **Thư viện**:
  - `Retrofit` – xử lý HTTP request/response
  - `SharedPreferences` – lưu trữ cục bộ token và thông tin người dùng

#### **Backend (API Server)**
- **Ngôn ngữ**: Java
- **Framework**: Spring Boot
- **Thiết kế API RESTful** để giao tiếp với mobile app

#### **Cơ sở dữ liệu**
- **Hệ quản trị**: Microsoft SQL Server
- **Kiểu dữ liệu**: Quan hệ (Relational Database)
- **Đặc điểm**: Cấu trúc rõ ràng, tối ưu truy vấn, dễ mở rộng

### 3. Hướng dẫn cài đặt Backend cho EcoVeggieApp

## Yêu cầu tiên quyết

Trước khi bắt đầu, hãy đảm bảo đã cài đặt các phần mềm sau:

* **Java Development Kit (JDK):** Phiên bản 17 hoặc cao hơn.
* **Spring Tool Suite 4 (STS4):** Đây là một môi trường phát triển tích hợp (IDE) dựa trên Eclipse, được tùy chỉnh cho phát triển ứng dụng Spring Boot.
* **Maven:** Công cụ quản lý dự án và build. STS4 thường tích hợp sẵn Maven, nhưng nên đảm bảo nó được cài đặt và cấu hình đúng.
* **Microsoft SQL Server:** Cơ sở dữ liệu sẽ sử dụng. Đảm bảo SQL Server đã được cài đặt và đang chạy.
* **SQL Server Management Studio (SSMS) (tùy chọn):** Công cụ này giúp quản lý cơ sở dữ liệu SQL Server dễ dàng hơn.

## Các bước cài đặt

1.  **Import dự án vào STS4:**
    * Mở STS4.
    * Chọn **File** -> **Import...**.
    * Trong cửa sổ Import, chọn **Maven** -> **Existing Maven Projects** và nhấn **Next**.
    * Nhấn vào nút **Browse...** và chọn thư mục chứa file `pom.xml` của dự án backend EcoVeggieApp.
    * STS4 sẽ tự động phát hiện dự án. Nhấn **Finish**.

2.  **Cấu hình cơ sở dữ liệu:**
    * Tìm và mở file `src/main/resources/application.properties` trong Project Explorer của STS4.
    * Kiểm tra và chỉnh sửa các thông tin kết nối cơ sở dữ liệu cho phù hợp với cấu hình SQL Server:

        ```properties
        spring.application.name=EcoVeggieApp
        server.port=9080
        spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=ecoveggieApp;encrypt=true;trustServerCertificate=true
        spring.datasource.username=sa
        spring.datasource.password=05052004
    
        **Lưu ý:**
        * Đảm bảo `spring.datasource.url`, `spring.datasource.username`, và `spring.datasource.password` khớp với thông tin đăng nhập và tên cơ sở dữ liệu SQL Server.

3.  **Cấu hình Maven Dependencies:**
    * Mở file `pom.xml` để xem các dependencies của dự án. Các dependencies cần thiết cho kết nối SQL Server và Spring Data JPA đã được bao gồm:

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <scope>runtime</scope>
        </dependency>
        ```

    * STS4 sẽ tự động tải xuống các dependencies này. Nếu không, Có thể chuột phải vào dự án trong Project Explorer, chọn **Maven** -> **Update Project...** và tích vào **Force Update of Snapshots/Releases** rồi nhấn **OK**.

4.  **Chạy ứng dụng:**
    * Trong Project Explorer, tìm class chính của ứng dụng Spring Boot (thường có annotation `@SpringBootApplication`, ví dụ: `EcoVeggieAppApplication.java`).
    * Chuột phải vào class này, chọn **Run As** -> **Maven Build**.
    * STS4 sẽ bắt đầu build và chạy ứng dụng backend. Có thể theo dõi tiến trình trong cửa sổ Console.
    * Khi ứng dụng khởi động thành công, thấy các log thông báo, bao gồm cổng server đang chạy (trong trường hợp này là `9080`).

## Các bước tiếp theo

* **Tạo và quản lý cơ sở dữ liệu:** Đảm bảo cơ sở dữ liệu `ecoveggieApp` đã được tạo trong SQL Server. Có thể sử dụng SSMS để thực hiện việc này.
* **Xây dựng các API:** Tiếp tục phát triển các API (endpoints) cần thiết cho ứng dụng EcoVeggieApp.
* **Kiểm thử API:** Sử dụng các công cụ như Postman hoặc Swagger để kiểm thử các API đã xây dựng.

