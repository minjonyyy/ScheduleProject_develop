# 🗓️ 일정 관리 앱 _ JPA 적용

### 🪄 [<트러블 슈팅 및 회고>](https://velog.io/@minjonyyy/Spring-JPA%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85) 보러가기



***

### <단계별 요구사항>

### 0️⃣ LV.0 API 명세서 / ERD / SQL 쿼리문 작성

**1. API 명세서**
https://documenter.getpostman.com/view/41327981/2sAYXCjJ7q

**2. ERD**
<img src="https://velog.velcdn.com/images/minjonyyy/post/93d6a32e-cfa7-4362-86ca-fc46789c98d3/image.png">

**3. SQL**

```sql
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    contents TEXT,
    num_of_comments BIGINT DEFAULT 0,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    task_id BIGINT,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES task(id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

```

