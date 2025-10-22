# Roadmap

This roadmap keeps VIN decoding, recall intelligence, and platform tooling moving in lockstep. Targets are grouped by theme so related work reinforces each other.

## 1. VIN Intelligence

- **Adaptive decoding hints** – Allow `decode()` to accept optional market/region hints and surface them through `VehicleData.raw_data` for partners that supply partial VINs.
- **Offline enrichment** – Extend the WMI dataset with common drivetrain/body-style heuristics so offline fallback delivers more than manufacturer/year.
- **Caching strategy** – Formalize cache interfaces (pluggable backends, TTL defaults) shared between VIN decoding and recall calls.

## 2. Recall Experience

- **Bulk recall endpoints** – Add helpers for the `/recalls` listing API with pagination controls; align payload structure with `RecallRecord` so consumers reuse a single model.
- **Recall severity scoring** – Derive a lightweight severity signal (stop-driving, fire risk, OTA availability) and expose it as computed fields on `RecallRecord`.
- **Change monitoring** – Ship a script that snapshots recall results for a VIN list and flags deltas, feeding regression matrices and alerting workflows.

## 3. Integration Tooling

- **Language parity** – Mirror the new recall helpers in the Java and Android modules so mobile clients stay in sync with Python capabilities.
- **Telemetry hooks** – Standardize logging/metrics decorators for decode + recall calls (latency, cache hits, error buckets) to support fleet monitoring.
- **Sandbox tests** – Expand unit and contract tests to cover mocked recall responses and offline fallbacks together.

## 4. Docs & Distribution

- **Living usage examples** – Keep `docs/USAGE.md` examples executable by wiring them into a smoke-test script that runs in CI.
- **Release notes discipline** – Update `CHANGELOG.md` with recall milestones and integration guidance before each tag.
- **Partner playbooks** – Draft vertical-specific guides (e.g., fleet, insurance) showing how VIN + recall data flow end-to-end, referencing the regression matrix where relevant.

Progress across themes should be coordinated in weekly check-ins—feature work in one area (e.g., severity scoring) should feed documentation updates and new regression coverage immediately.
