# -----------------------
# Config
# -----------------------
MVN := mvn
JOBMANAGER_CONTAINER := jobmanager
FLINK_JOB_JAR := target/my-flink-project-0.1.jar
JOB_NAME := com.example.flink.jobs.DataStreamJob

# -----------------------
# Phony targets
# -----------------------
.PHONY: help up down compile pkg clean submit rebuild run

# -----------------------
# Help
# -----------------------
help:
	@echo "Available targets:"
	@echo "  up                     - Start the Flink cluster (docker compose up -d)"
	@echo "  down                  - Stop the Flink cluster (docker compose down)"
	@echo "  compile               - Compile the Maven project"
	@echo "  test                  - Run tests"
	@echo "  package               - Package project into a JAR file"
	@echo "  install               - Install JAR to local Maven repository"
	@echo "  clean                 - Clean build artifacts"
	@echo "  verify                - Verify project"
	@echo "  submit                - Submit the job to Flink (builds if needed)"
	@echo "  rebuild               - Clean and recompile project"
	@echo "  run                   - Start cluster, build JAR, and submit job"
	@echo "  logs                  - Print taskmanager logs"
	@echo "  local-shell           - Start jshell with compiled classes"
	@echo "  local-shell-with-deps - Start jshell with classes and dependencies"

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
compile:
	@echo "Compiling Maven project..."
	$(MVN) compile

test: compile
	@echo "Running tests..."
	$(MVN) test

package: test
	@echo "Packaging project into a JAR file..."
	$(MVN) package

install: package
	@echo "Installing JAR to local Maven repository..."
	$(MVN) install

clean:
	@echo "Cleaning Maven build artifacts..."
	$(MVN) clean

verify:
	@echo "Verifying project..."
	$(MVN) verify

rebuild:
	@echo "Cleaning and recompiling project..."
	$(MVN) clean compile

download-dep-jars:
	@echo "Downloading project dependencies..."
	$(MVN) dependency:copy-dependencies -DoutputDirectory=target/dependency

# -----------------------
# Job submission
# -----------------------
submit: compile
	docker cp $(FLINK_JOB_JAR) $(JOBMANAGER_CONTAINER):/job.jar
	docker exec $(JOBMANAGER_CONTAINER) flink run -c $(JOB_NAME) /job.jar
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

# -----------------------
# Local development shell
# -----------------------
local-shell:
	jshell --class-path target/classes

local-shell-with-deps:
	jshell --class-path "target/classes:target/dependency/*"
