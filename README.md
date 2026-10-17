# Mock flow implementation

This branch implements the initial mock flow for the Wallet Connector prototype:

- Kotlin + Spring Boot
- Simple REST API:
  - POST /api/v1/verifications
  - GET /api/v1/verifications/{id}
  - POST /api/v1/verifications/{id}/presentations
- In-memory H2 persistence (easy to run locally). Switch to PostgreSQL by configuring application-postgres.yml and activating the profile.

Next steps you can ask me to do:
- Add integration tests for the REST API
- Add a simple web UI for the mock wallet simulator
- Replace H2 with PostgreSQL configuration and migration scripts
- Add validation and error handling
