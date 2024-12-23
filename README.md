
# Server Mobile Project

## Prerequisites
- **Docker & Docker Compose** installed on your machine.
- **Java 21** (if running locally).
- **Maven** (if running locally).

---

## Running the Application

### Using Docker
#### **Build**
```bash
docker compose build app
```

#### **Start**
```bash
docker compose up -d
```

#### **Down**
```bash
docker compose down
```

#### **Check Logs**
```bash
docker logs springboot_app
docker logs springboot_mysql_db
```

### Access the Application
- **API**: [http://localhost:8080/api](http://localhost:8080/api)
- **MySQL**: `localhost:3306`
    - **Username**: `root`
    - **Password**: `root`

---

### To Rebuild the App Image
```bash
docker compose build app
```
