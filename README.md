# 주문 애플리케이션 데모

> API 문서 : https://documenter.getpostman.com/view/10215031/UVyn2e3H

### 기능

1. 회원가입
2. 로그인 / 로그아웃
3. 상품 조회
4. 상품 주문
5. 주문 내역 조회

<br><br>

## 👷 Project Structure

### `/`
``` 
.
├── app-web [WEB 애플리케이션]
│   └── src
│       ├── main
│       └── test
│
├── app-web-order [Scheduler 애플리케이션 : 주문 처리 Scheduler]
│   └── src
│       ├── main
│       └── test
│
├── domains [Domain 패키지]
│   └── src
│       ├── main
│       └── test
│
└── test-tool [테스트 스크립트 디렉터리]

```

### `app-web/`

```
.
├── apps [애플리케이션 메인(서비스) 패키지]
│   ├── login
│   │   ├── dto
│   │   ├── service
│   │   └── web
│   ├── member
│   │   ├── dto
│   │   ├── service
│   │   └── web
│   ├── order
│   │   ├── dto
│   │   ├── service
│   │   └── web
│   └── product
│       ├── dto
│       ├── repository
│       ├── service
│       └── web
├── global [애플리케이션 공용 설정 관련 패키지 (애플리케이션 종속)]
│   ├── login
│   ├── security
│   └── web
└── modules [외부 모듈 패키지 (이후 외부로 추출할 수 있는 애플리케이션 독립적인 패키지)]
    ├── kafka
    └── validator
```

### `app-web-order/`

```
.
├── apps [애플리케이션 메인(서비스) 패키지]
│   └── order
│       └── scheduler
├── global [애플리케이션 공용 설정 관련 패키지 (애플리케이션 종속)]
└── modules [외부 모듈 패키지 (이후 외부로 추출할 수 있는 애플리케이션 독립적인 패키지)]
    └── kafka
```

<br><br>

## 👷 공통

### 1. (공통) Security
> 패키지 : (app-web) `/global/security`

```
└── security
    ├── ToyAccessDeniedException.java     :: 커스텀 Exception 클래스
    ├── ToyAccessDeniedHandler.java       :: 커스텀 AccessDeniedHandler 클래스
    ├── ToyAuthenticationEntryPoint.java  :: 커스텀 AuthenticationEntryPoint 클래스
    ├── ToyAuthenticationException.java   :: 커스텀 Exception 클래스
    ├── ToyAuthenticationFilter.java      :: 커스텀 인증 필터
    └── ToyAuthenticationToken.java       :: 커스텀 인증 토큰
```

| HTTP METHOD | 설명 (인증/인가 조건) |
| --- | --- |
| POST | 인증된 사용자 <br><br> **예외 PATH **<br>/members : 회원가입<br>/login : 로그인<br>/h2 : h2 웹 |
| GET | 모두 허용<br><br>** 예외 PATH **<br>/orders/** : 주문 내역 조회 (혹은 추후 생길 주문 관련 GET 메서드) |
| PUT | 인증된 사용자 |
| DELETE | 관리자 |
| 그 외 | 모두 허용 |

<br>

**인증 방식 :: ToyAuthenticationFilter**

1. cookie 에서 session ID 값을 추출합니다.
2. session ID 값을 통해 세션 정보를 추출(확인)합니다.
    1. 현재는 Memory 기반으로 Session 을 관리합니다. ( `LoginService > SessionMemberLoginService` )
3. 세션 정보가 유효한 사용자(= 인증된 사용자)는 SecurtyContext 에 정보를 저장하고 다음 filter 로 넘깁니다.

<br><br>

### 2. (공통) Response 처리

> 패키지 : (app-web) `/global/web` <br>
> 핵심 클래스 : `ApiResponseBodyControllerAdvice`, `ExceptionControllerAdvice`, `ApiResponseBody` <br>

\* @ControllerAdvice 기반으로 구현하였습니다. 

공통 Response 형태는 다음과 같습니다.

```json
{
    "result": "boolean",
    "message": "String",
    "data": "T"
}
```

```
{
    "result": false,
    "message": "상품의 수량은 음수 값이 될 수 없습니다.",
    "data": []
}

{
    "result": false,
    "message": "상품의 ID는 음수 값이 될 수 없습니다. | 상품의 수량은 음수 값이 될 수 없습니다. | ...",
    "data": []
}
```


<br><br>

## 👷 상세 기능

> 주요 기능만 작성하였습니다.

<br>

### 1. (상세 기능) 로그인

> 패키지 : (app-web) `/apps/login` <br>
> 핵심 클래스 : `SessionMemberLoginService`, `MemberLoginApiController` <br>
> API PATH : `POST /login` <br>
> API URL : https://documenter.getpostman.com/view/10215031/UVyn2e3H#3e4dcea8-a029-4304-8f31-826e1065bb60

\* 현재 로그인은 세션/쿠키 기반으로 동작하며, Password 인증으로 진행됩니다. ( → `SessionMemberLoginService` ) <br>
\* 로그인 정보는 In-Memory 기반으로 관리합니다. <br>
\* **확장성을 고려합니다.**

**로그인 서비스 인터페이스**

<img src="https://user-images.githubusercontent.com/35790290/160271922-6863ef3e-5c3b-411b-8b62-cc16f7ce3cb0.png" width=50%>

**로그인 매니저 인터페이스**

<img src="https://user-images.githubusercontent.com/35790290/160271951-0e89e2fb-f243-4409-b3b8-d12c8dde75a4.png" width=50%>


<br><br>

### 2. (상세 기능) 상품 주문/처리

> 패키지 : (app-web) `/apps/orders` <br>
> 핵심 클래스 : `Order`, `OrderItem`, `OrderRequestService`, `OrderScheduler`, `OrderHandlerManager` <br>
> API PATH : `POST /orders` <br>
> API URL : https://documenter.getpostman.com/view/10215031/UVyn2e3H#d06f678e-adb8-4526-82f4-358ffe94c629

\* Order (주문서) : 구매자 정보, 주문서 상태 값, 주문서 상세 내역(OrderItem) 값을 관리합니다. <br>
\* OrderItem (주문 상세 내역) : 구매 상품, 구매 수량 값을 관리합니다. <br>
\* **동시성/확장성을 고려합니다.**

**상품 주문은 아래와 같이 동작합니다. (상품 주문은 총 2단계(접수, 처리)로 구현하였습니다.)**

1. **상품 주문 시 Queue(DB) 에 삽입 (동시성 고려)**
2. **접수된 주문서를 순차적으로 처리합니다.**
    1. **처리 시 검증, 로깅, 상품 수량 감소 등이 처리됩니다.**
    2. **필요에 따라 전처리, 후처리 인터페이스를 구현합니다.**

**전처리 인터페이스 / 기본 구현체**

<img src="https://user-images.githubusercontent.com/35790290/160272078-303bcf46-11ca-4a09-9a5c-053177163759.png" width=50%>

```java
public interface OrderPreHandler {

    /**
     * 해당 주문서의 처리 여부를 결정합니다.
     * */
    boolean supports(Order order);

    /**
     * 해당 주문서를 처리합니다.
     */
    boolean preHandle(Order order);
}
```

<br><br>

**후처리 인터페이스 / 기본 구현체**

<img src="https://user-images.githubusercontent.com/35790290/160272077-a2b3be96-cc4a-4975-b189-51ee948478d0.png" width=50%>

```java
public interface OrderPostHandler {

    /**
     * 해당 주문서의 처리 여부를 결정합니다.
     * */
    boolean supports(Order order);

    /**
     * 해당 주문서를 처리합니다.
     */
    void postHandle(Order order);
}
```




<br><br>
