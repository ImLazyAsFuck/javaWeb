<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giới Thiệu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            max-width: 1200px;
            margin: 0 auto;
        }
        header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 1em 0;
        }
        nav {
            background-color: #333;
            overflow: hidden;
        }
        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        nav a:hover {
            background-color: #ddd;
            color: black;
        }
        .active {
            background-color: #4CAF50;
        }
        main {
            padding: 20px;
        }
        .team-member {
            margin-bottom: 20px;
            border-bottom: 1px solid #eee;
            padding-bottom: 20px;
        }
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Về Chúng Tôi</h1>
    </header>
    
    <nav>
        <a href="/">Trang Chủ</a>
        <a href="/about" class="active">Giới Thiệu</a>
        <a href="/contact">Liên Hệ</a>
    </nav>
    
    <main>
        <h2>Giới Thiệu Về Chúng Tôi</h2>
        <p>Chúng tôi là một đội ngũ chuyên nghiệp với nhiều năm kinh nghiệm trong lĩnh vực phát triển phần mềm. Sứ mệnh của chúng tôi là cung cấp các giải pháp công nghệ tốt nhất cho khách hàng.</p>
        
        <h3>Đội Ngũ Của Chúng Tôi</h3>
        
        <div class="team-member">
            <h4>Nguyễn Văn A</h4>
            <p>Giám đốc điều hành với hơn 10 năm kinh nghiệm trong ngành công nghệ thông tin.</p>
        </div>
        
        <div class="team-member">
            <h4>Trần Thị B</h4>
            <p>Trưởng phòng phát triển với chuyên môn về Java và Spring Framework.</p>
        </div>
        
        <div class="team-member">
            <h4>Lê Văn C</h4>
            <p>Chuyên viên thiết kế UX/UI với khả năng tạo ra những giao diện người dùng trực quan và thân thiện.</p>
        </div>
        
        <p>Hãy <a href="/contact">liên hệ với chúng tôi</a> để biết thêm thông tin hoặc quay lại <a href="/">trang chủ</a>.</p>
    </main>
    
    <footer>
        <p>© 2025 - Trang Web Demo</p>
    </footer>
</body>
</html>
