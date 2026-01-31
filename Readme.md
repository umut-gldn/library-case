# 📚 Library Management System

## 🚀 Docker ile Kurulum ve Çalıştırma

Projeyi ayağa kaldırmak için sırasıyla aşağıdaki adımları uygulayın:

### 1. Ortam Değişkenlerini Hazırla

Örnek dosyadaki ayarları kullanmak için `.env.example` dosyasının kopyasını oluşturun:

```bash
cp .env.example .env
```

> Windows kullanıcıları dosyayı manuel olarak kopyalayıp ismini `.env` yapabilir.

### 2. Uygulamayı Başlat

```bash
docker compose up --build
```

Durdurmak ve temizlemek için:

```bash
docker compose down -v
```

## 🔗 Erişim

Uygulama açıldığında API test arayüzüne buradan ulaşabilirsiniz:

**Swagger UI:** http://localhost:8080/swagger-ui/index.html

## 📋 API Endpoints

### Books (`/api/books`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/books` | Tüm kitapları listele |
| GET | `/api/books/{id}` | ID'ye göre kitap getir |
| POST | `/api/books` | Yeni kitap ekle |
| PUT | `/api/books/{id}` | Kitap güncelle |
| DELETE | `/api/books/{id}` | Kitap sil |
| GET | `/api/books/starting-with?letter=A` | Belirli harfle başlayan kitaplar |
| GET | `/api/books/after-year?year=2020` | Belirli yıldan sonra yayınlanan kitaplar |
| GET | `/api/books/search?title=clean` | Google Books API'den kitap ara |

### Authors (`/api/authors`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/authors` | Tüm yazarları listele |

### Publishers (`/api/publishers`)

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/publishers` | Tüm yayınevlerini listele |
| GET | `/api/publishers/with-books?limit=2` | Yayınevlerini kitaplarıyla birlikte getir |

## 📝 Örnek Kayıt Verileri (POST /api/books)

Aşağıdaki JSON verilerini Swagger veya Postman üzerinden direkt kullanabilirsiniz.

```json
{
  "title": "Clean Code",
  "price": 540.0,
  "ISBN13": "9780132350884",
  "publicationYear": 2008,
  "publisherName": "Prentice Hall",
  "authorNameSurname": "Robert C. Martin"
}
```

```json
{
  "title": "Kürk Mantolu Madonna",
  "price": 95.5,
  "ISBN13": "9789753638029",
  "publicationYear": 1943,
  "publisherName": "Yapı Kredi Yayınları",
  "authorNameSurname": "Sabahattin Ali"
}
```

```json
{
  "title": "Atomic Habits",
  "price": 350.0,
  "ISBN13": "9780735211292",
  "publicationYear": 2018,
  "publisherName": "Penguin Random House",
  "authorNameSurname": "James Clear"
}
```
