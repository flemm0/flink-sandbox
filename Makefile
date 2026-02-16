# -----------------------
# Config
# -----------------------
# Use fixed JAR path so "make compile" works on first run (no existing JAR)
FLINK_JOB_JAR := target/my-flink-project-0.1.jar
JOBMANAGER_CONTAINER := jobmanager

# -----------------------
# Phony targets
# -----------------------
.PHONY: help up down compile pkg clean submit rebuild run

# -----------------------
# Help
# -----------------------
help:
	@echo "Available targets:"
	@echo "  up       - Start the Flink cluster (docker compose up -d)"
	@echo "  down     - Stop the Flink cluster (docker compose down)"
	@echo "  compile  - Compile the Flink job JAR"
	@echo "  pkg      - Same as compile"
	@echo "  clean    - Clean build artifacts"
	@echo "  submit   - Submit the job to Flink (builds if needed)"
	@echo "  rebuild  - Clean + compile"
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
compile: $(FLINK_JOB_JAR)

pkg: compile

$(FLINK_JOB_JAR):
	mvn clean package -DskipTests

clean:
	mvn clean

rebuild: clean compile

# -----------------------
# Job submission
# -----------------------
submit: compile
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
