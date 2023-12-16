# :hibiscus: Ecommerce

E-commerce project developed using Spring Boot and React.js.

## Used Technologies

* Back-end: Spring (Boot, Data, Security), JPA / Hibernate, PostgreSQL, JUnit, Mockito
* Front-end: TypeScript, React.js, Redux, Jest, Bootstrap, CSS
* Security: JWT, OAuth2 Google, Facebook, Github
* REST API, GraphQL API
* AWS: EC2, S3, Min.io, PostgreSQL RDS
* Server Build: Maven
* Client Build: npm, yarn, webpack
* SMTP server: smt4dev

## Features

* Authentication with JWT and Email validation.
* Authentication with Google, Facebook or Github
* Customers can search for the product according to the specified criteria.
* Customers can add and delete products from the shopping cart.
* Customers can order the products in the shopping cart.
* Customers can change their password and view their orders.
* Admin can add or modify a product.
* Admin can change the data of any user.
* Admin can view orders of all users.

## Run the application

To build the backend and its postgres, you should run the following command:

```bash
docker-compose build --no-cache
```

To run the builds created before, you should run the following command:

```bash
docker-compose up
```

Navigate to the <http://localhost:3000> to see the webpage

> **NOTE:**
>
> Admin user for the webpage -> admin@gmail.com / admin
> Regular user for the webpage -> test123@test.com / admin
>
> Admin user for the min.io(bucket) -> admin / S1ngu1ar
>
> Admin user for postgres_app -> postgres / root

## Docker images running

| Docker image           | Port expose    |
|:-----------------------|:---------------|
| ecommerce_front        | 3000           |
| ecommerce_back         | 8080           |
| ecommerce_bucket       | 9001           |
| smtp4dev               | 8081           |
| ecommerce_postgres_app | 5432           |

## Swagger Documentation

<http://localhost:8080/swagger-ui.html>

## Running Docker in Windows
In order to run Docker in Windows properly, you need to install Linux on Windows (WSL). The Docker engine used in windows runs inside this Linux.
To install WSL on Windows 10 or higher, you should open a Power Shell as administrator an run this command:
```powershell
wsl --install
```
This  method of installation has been improved a lot with the latest Windows Updates. In order to do it so easily, you need to have your Windows 10/11 up-to-date. Anyway you can check [Microsoft's WSL installation guide](https://learn.microsoft.com/en-us/windows/wsl/install)

This installs an Ubuntu Linux distribution by default.
Once the installation proccess is finished, it is very recommended you upgrade the linux distribution too:
```bash
sudo apt update && sudo apt -y upgrade
```

Notice you should enter now the docker commands in an Ubuntu terminal, not Window's PowerShell or CMD.
To get an Ubuntu terminal you can:
 - [Windows 10 and 11] From the Windows Start Menu, select 'Ubuntu'
 - [Windows 11] Open the ***Terminal application*** from the start menu, then click on the down-arrow  button (v) in the menu bar and select Ubuntu
    - [Windows 10] You can do the previous point in Windows 10, installing [Windows Terminal](https://apps.microsoft.com/store/detail/windows-terminal/9N0DX20HK701?hl=es-es&gl=es&rtc=1=) first, which is shipped by default on Windows 11.
- [Windows 10 and 11] Installing [ConEmu](https://conemu.github.io/), an alternative terminal with very useful functions.


## Screenshots

|                    Menu page                     |                     Product page                      |
|:------------------------------------------------:|:-----------------------------------------------------:|
| ![Menu page](https://i.ibb.co/dcp56tb/menu.jpg)  | ![Product page](https://i.ibb.co/1dBjdMy/product.jpg) |

|                         Email template                         |                      List of users                       |
|:--------------------------------------------------------------:|:--------------------------------------------------------:|
| ![Email template](https://i.ibb.co/bmKTLPJ/email-template.jpg) | ![List of users](https://i.ibb.co/T88cFZt/all-users.jpg) |

|                      List of orders                       |                       User order page                       |
|:---------------------------------------------------------:|:-----------------------------------------------------------:|
| ![List of orders](https://i.ibb.co/T88cFZt/all-users.jpg) | ![User order page](https://i.ibb.co/4f7F0hk/all-orders.jpg) |

|                      User profile page                       |                       Add perfume page                        |
|:------------------------------------------------------------:|:-------------------------------------------------------------:|
| ![User profile page](https://i.ibb.co/KDF3FZX/user-page.jpg) | ![Add perfume page](https://i.ibb.co/KGKhJxR/add-perfume.jpg) |

|                    Edit perfume list                    |                        Edit perfume page                        |
|:-------------------------------------------------------:|:---------------------------------------------------------------:|
| ![Edit perfume list](https://i.ibb.co/fkFSnFy/edit.jpg) | ![Edit perfume page](https://i.ibb.co/cTddspr/edit-perfume.jpg) |
