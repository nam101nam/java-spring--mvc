<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Trang JSP Demo</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <!-- jQuery -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            <style>
                body {
                    background-color: #f7f9fc;
                    font-family: "Segoe UI", sans-serif;
                }

                header {
                    background-color: #0d6efd;
                    color: white;
                    padding: 15px 0;
                    text-align: center;
                }

                footer {
                    background-color: #212529;
                    color: #ccc;
                    text-align: center;
                    padding: 10px 0;
                    position: fixed;
                    bottom: 0;
                    width: 100%;
                }

                .card {
                    border-radius: 12px;
                    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
                }

                .btn-custom {
                    background-color: #ffc107;
                    border: none;
                    color: #000;
                    transition: 0.3s;
                }

                .btn-custom:hover {
                    background-color: #e0a800;
                }
            </style>
        </head>

        <body>
            <header>
                <h1>👋 Chào mừng đến Website đầu tiên của Nam</h1>
            </header>

            <main class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="card p-4 text-center">
                            <h3 class="mb-3 text-primary">Website bán laptop</h3>



                            <a class="btn btn-custom mt-3" id="btnClick" href="/admin/user">Cùng khám phá nào</a>
                        </div>
                    </div>
                </div>
            </main>

            <footer>
                <p>© 2025 JSP Demo by HoidanIT</p>
            </footer>


        </body>

        </html>