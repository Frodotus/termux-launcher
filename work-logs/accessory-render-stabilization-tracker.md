# Accessory Render Stabilization Tracker

Goal: keep blur/grain/opacity and launcher features, while stabilizing rendering and reducing flicker/stale overlay state.

- [x] Step 1: Introduce an explicit Accessory Render Controller path in `TermuxActivity` for accessory visibility/background/blur/AZ+app row state.
- [x] Step 2: Route all accessory visibility mutations through the controller (remove ad-hoc direct `setVisibility()` writes).
- [x] Step 3: Split terminal-domain and accessory-domain state updates so terminal redraw does not carry accessory mutations.
- [x] Step 4: Normalize keyboard/open-close transitions into one state transition entrypoint and one post-layout sync.
- [x] Step 5: Enforce FX visibility invariants (if accessory stack hidden, all AZ/page-indicator FX must be reset+gone).
- [x] Step 6: Reduce overlapping live-blur regions while preserving visual options (selective static tint fallback per region).
- [x] Step 7: Stabilize insets/margin sequencing against upstream behavior with minimal dynamic edge-mode churn.
- [x] Step 8: Add debug diagnostics for accessory/terminal state snapshots on keyboard transitions.
- [x] Step 9: Profile frame timing (`gfxinfo`/frame metrics) for keyboard open/close and heavy terminal output scenarios.
- [x] Step 10: Harden with regression checklist and finalize cleanup/refactor pass.

## Regression Checklist (Step 10)

- [x] Keyboard open/close triggers one synchronized accessory render pass via scheduled sync pipeline.
- [x] Accessory hidden state forcibly clears AZ/page indicator FX state.
- [x] Terminal overlay layers (monet/blur/grain) respect accessory bottom inset to reduce overlap region churn.
- [x] Toolbar toggle and toolbar height updates route through controller sync and invariant checks.
- [x] Accessory render snapshots are logged with reason + timing delta for runtime diagnosis.
