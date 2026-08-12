# Election Management System — VOTO-AL 2025

**Java · Swing · Object-Oriented Design**  
Desktop application for managing a simulated Albanian national election cycle

![Java](https://img.shields.io/badge/Java-Swing-orange)
![IDE](https://img.shields.io/badge/IDE-NetBeans-blue)
![Domain](https://img.shields.io/badge/Domain-Election%20Management-darkgreen)

A NetBeans-based Java Swing application that models the election lifecycle: voter registration, candidate and constituency management, controlled vote casting, news updates, and result tabulation.

---

## Overview

The system supports two roles — **Administrator** and **Voter** — through a shared Welcome interface. Administrators configure the election, manage entities, and control start/end. Voters authenticate with a generated ID, cast a single ballot per assembly level, and are blocked from voting again once their ballot is recorded.

The application is designed as an educational / portfolio project that demonstrates GUI design, domain modeling, and election process rules in pure Java (no external database).

---

## Features

| Area | Capability |
|------|------------|
| **Voter registration** | Auto-generated voter IDs (`AL-001`, `AL-002`, …) |
| **Single-vote rule** | After voting, the same voter cannot log in again |
| **Parties** | Five Albanian parties with custom logos (PS, PD, LSI, PDIU, PRS) |
| **Election control** | Start / end election from the admin panel |
| **News** | Automatic publication of election news when the election starts |
| **Admin panel** | Voters, candidates, constituencies, news, and process control |
| **Results** | Live vote counts and overall turnout-style reporting |
| **UX** | Splash screen with progress bar on startup |

---

## Electoral Zones

| Qarku | Bashkia |
|-------|---------|
| Q-TI (Tiranë) | B-TI-1, B-TI-2 |
| Q-DR (Durrës) | B-DR-1 |
| Q-SH (Shkodër) | B-SH-1 |

Voters select candidates at the relevant assembly levels (qark / bashki) according to the configured constituencies.

---

## Political Parties

| Code | Party |
|------|--------|
| PS | Partia Socialiste e Shqipërisë |
| PD | Partia Demokratike e Shqipërisë |
| LSI | Lëvizja Socialiste për Integrim |
| PDIU | Partia për Drejtësi, Integrim dhe Unitet |
| PRS | Partia Republikane e Shqipërisë |

Party symbols are included as PNG assets in the source package.

---

## Voting Flow

1. Administrator starts the election from **Election Control**  
2. Voter logs in with their ID (e.g. `AL-005`)  
3. Voter selects one candidate per relevant assembly  
4. On **VOTE**, the system confirms the ballot and returns to the home view  
5. A second login attempt by the same voter is rejected (already voted)

---

## Default Admin Credentials
