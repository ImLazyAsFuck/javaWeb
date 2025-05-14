<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liên Hệ</title>
</head>
<body>
    <header>
        <h1>Liên Hệ Với Chúng Tôi</h1>
    </header>
    
    <nav>
        <a href="/">Trang Chủ</a>
        <a href="/about">Giới Thiệu</a>
        <a href="/contact" class="active">Liên Hệ</a>
    </nav>
    
    <main>
        <h2>Thông Tin Liên Hệ</h2>
        
        <div class="contact-info">
            <p><strong>Địa chỉ:</strong> 123 Đường ABC, Quận XYZ, TP. Hồ Chí Minh</p>
            <p><strong>Điện thoại:</strong> (028) 1234 5678</p>
            <p><strong>Email:</strong> info@example.com</p>
            <p><strong>Giờ làm việc:</strong> Thứ Hai - Thứ Sáu: 8:00 - 17:30</p>
        </div>
        
        <div class="contact-form">
            <h3>Gửi Tin Nhắn Cho Chúng Tôi</h3>
            <form action="#" method="post">
                <div class="form-group">
                    <label for="name">Họ và tên:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="subject">Chủ đề:</label>
                    <input type="text" id="subject" name="subject">
                </div>
                
                <div class="form-group">
                    <label for="message">Nội dung:</label>
                    <textarea id="message" name="message" rows="5" required></textarea>
                </div>
                
                <button type="submit">Gửi tin nhắn</button>
            </form>
        </div>
        
        <p>Quay lại <a href="/">trang chủ</a> hoặc xem thêm <a href="/about">thông tin về chúng tôi</a>.</p>
    </main>
    
    <footer>
        <p>© 2025 - Trang Web Demo</p>
    </footer>
</body>
</html>
