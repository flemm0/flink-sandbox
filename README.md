# Flink Sandbox

A quick-start Apache Flink development environment with Docker Compose.

## Quick Start

### 1. Start Flink Cluster

```bash
make up
```

This starts the Flink cluster with:
- **JobManager** (Flink Web UI): http://localhost:8081
- **TaskManager**: Automatically connects to the JobManager

### 2. Build and Submit Job

**Option A: One command (recommended)**
```bash
make run
```
This starts the cluster, builds the JAR, and submits the job automatically.

**Option B: Step by step**
```bash
make build    # Compile the Flink job
make submit   # Submit the job to the cluster
```

## Monitoring Jobs

### Web UI
Open http://localhost:8081 in your browser to:
- View running/completed jobs
- Monitor job metrics and performance
- Check task manager status
- View job execution plans

### Logs
View TaskManager logs:
```bash
make logs
```

## Available Commands

| Command | Description |
|---------|-------------|
| `make up` | Start Flink cluster |
| `make down` | Stop Flink cluster |
| `make build` | Build the Flink job JAR |
| `make submit` | Submit job to Flink (builds if needed) |
| `make run` | Start cluster + build + submit (all-in-one) |
| `make clean` | Clean build artifacts |
| `make rebuild` | Clean + build |
| `make logs` | View TaskManager logs |
| `make help` | Show all available commands |

## Project Structure

```
.
├── docker-compose.yml    # Flink cluster configuration
├── Makefile              # Convenience commands
├── pom.xml               # Maven project configuration
└── src/
    └── main/
        ├── java/com/example/flink/DataStreamJob.java
        └── resources/log4j2.properties
```

## Requirements

- Docker and Docker Compose
- Maven (for building the job)
- Make (optional, for convenience commands)

## Notes

- The Flink cluster uses Flink 1.17 (as defined in `docker-compose.yml`)
- The job JAR is automatically copied into the JobManager container and submitted
- Jobs can be monitored and cancelled via the Web UI at http://localhost:8081
