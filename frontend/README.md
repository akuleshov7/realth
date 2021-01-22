# Frontend for REALTH

## Building
To build all code run `gradlew build`.
To run local dev server, run `gradlew :frontend:browserDevelopmentRun` and go to `localhost:3000` in your browser.

We use webpack via kotlin-js gradle plugin to bundle all resources. Additional configuration resides under `webpack.config.d` directory
as per kotlin-js docs.
