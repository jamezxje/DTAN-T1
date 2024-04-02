Car Rental System
=================
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Dependencies](#dependencies)
- [Usage](#usage)
  - [How to use](#how-to-use)
  - [How to contribute](#how-to-contribute)
  - [How to deploy](#how-to-deploy)
- [Contributors](#contributors)
- [Screenshots](#screenshots)
- [Demo](#demo)

## Introduction
This is a car rental system that allows users to rent cars and post a car for rent.

<br>

System has 3 roles: 
- Customer (User): User must login into the system with existed account to rent a car, add to favorite or view history of booking or transaction.
- Car's owner: This role can post their car for rent. They will receive the booking request from customer and can decide approve the request, reject and mark as customer return the car. Besides, Car Owner must have the wallet balance to payment car fee for system, it's auto and the fee must have
- System Admin: This role can manager user, manager car request - valid the car and decide it qualify for rent or not. Also, Admin can post the Article as new, introduction, etc, ...

<br>

The system is built using Spring Boot 3, Spring Data JPA, and Spring Security 6 and Thymeleaf.


## Features
- Admin
  - Setting for System
  - Car request management
  - User management
  - Deposit management
  - Statistics (Request, Income, Revenue, Booking)
  - Update profile
- Car owner
  - Car management
  - Booking request management
  - Request to deposit/with draw
  - Statistics (Request, Income, Revenue, Booking)
  - Update profile
  - Make deposit
- Customer
  - Rent a car
  - View booking history
  - Update profile
  - Make deposit

## Requirements
  ### Dependencies
  - [Java] Java 21
    - [Maven] Maven 3.0+
    - [Dependency] 
      - [MySQL] Mysql Driver 8.0+
      - [Spring Boot] Spring Boot 3.2.3
        - [Spring Data JPA] Spring Data JPA
        - [Spring Security] Spring Security
        - [Spring Boot DevTools] Spring Boot DevTools
        - [Spring Boot Starter Test] Spring Boot Starter Test
        - [Spring Boot Starter Thymeleaf] Spring Boot Starter Thymeleaf
        - [Spring Boot Starter Web] Spring Boot Starter Web
      - Lombok

## Contributors

- Hieupq
- Datlt
- Duytx
- Duydn

## Screenshots