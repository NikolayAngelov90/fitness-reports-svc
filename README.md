# Fitness Reports Service

The **Fitness Reports Service** is a microservice designed to generate and manage reports for the overall fitness application. It works in tandem with the main application by processing data (e.g., subscription statistics, workout participation, revenue figures) and providing insights into gym performance and user activities. The service leverages Spring Boot for rapid development and integrates with Kafka for asynchronous message processing.

## Overview

This microservice is responsible for:
- Generating detailed reports on gym subscriptions, group workouts, and payment transactions.
- Consuming data streams via Kafka to update reports in real time.
- Persisting report data in a MySQL database for historical analysis and future reference.
- Providing endpoints for the main application (or admin panels) to retrieve up-to-date reports.

## Key Features

### Report Generation
- **Data Aggregation:** Aggregates information from various sources within the fitness ecosystem.
- **Real-Time Updates:** Uses Kafka to receive and process real-time data updates.
- **Historical Analysis:** Stores reports in a MySQL database to facilitate long-term analysis.

### Technology Stack
- **Spring Boot 3.4.3:** Rapid application development and dependency management.
- **Spring Data JPA:** Simplified database interactions with MySQL.
- **Spring Kafka:** Consuming Kafka topics for asynchronous report updates.
- **Spring Validation:** Ensures that incoming data meets required formats and constraints.
