# -----------------------
# Config
# -----------------------
FLINK_JOB_JAR := $(shell \
	ls -1 target/*.jar 2>/dev/null | grep -v 'original-' | head -n 1 \
)
JOBMANAGER_CONTAINER := jobmanager

# -----------------------
# Phony targets
# -----------------------
.PHONY: help up down build clean submit rebuild run

# -----------------------
# Help
# -----------------------
help:
	@echo "Available targets:"
	@echo "  up       - Start the Flink cluster (docker compose up -d)"
	@echo "  down     - Stop the Flink cluster (docker compose down)"
	@echo "  build    - Compile the Flink job JAR"
	@echo "  clean    - Clean build artifacts"
	@echo "  submit   - Submit the job to Flink (builds if needed)"
	@echo "  rebuild  - Clean + build"
	@echo "  run      - Start cluster, build JAR, and submit job"

# -----------------------
# Docker
# -----------------------
up:
	docker compose up -d
	@echo "Flink cluster started. UI: http://localhost:8081"

down:
	docker compose down

# -----------------------
# Build
# -----------------------
build: $(FLINK_JOB_JAR)

$(FLINK_JOB_JAR):
	mvn clean package -DskipTests

clean:
	mvn clean

rebuild: clean build

# -----------------------
# Job submission
# -----------------------
submit: build
	docker cp $(FLINK_JOB_JAR) $(JOBMANAGER_CONTAINER):/job.jar
	docker exec $(JOBMANAGER_CONTAINER) flink run /job.jar
	@echo "Job submitted to Flink cluster."

# -----------------------
# One-command workflow
# -----------------------
run: up submit

# -----------------------
# Job monitoring
# -----------------------
logs:
	@echo "Printing taskmanager logs... Press Ctrl+C to exit."
	docker compose logs -f taskmanager
