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

Username: admin
Password: admin


Change these in the application seed/setup code before any non-demo use.

---

## Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Java |
| UI | Swing (`JFrame` and related components) |
| IDE | NetBeans (Ant project layout) |
| Design | OOP domain model (`Person` hierarchy, election entities) |
| Assets | Party logos (PNG); optional Pillow used to generate logos |

---

## Project Structure
SistemiZgjedhorShqiptar-Voto25/
├── README.md
├── AlbaniaEMS/                          # NetBeans project root
│   ├── build.xml
│   ├── manifest.mf
│   ├── nbproject/
│   ├── README.md
│   ├── ELECTIONS MANAGEMENT SYSTEM (EMS) - Report.pdf
│   ├── PROJECT PROPOSAL.pdf
│   ├── Presentation Elections Management System (EMS).pdf
│   └── src/
│       ├── class diagram/
│       │   └── Class Diagram.png
│       ├── Icons/                       # UI icons
│       └── ElectionManagementSystem/
│           ├── main.java                # Entry point & initial data
│           ├── Person.java              # Base type
│           ├── Administrator.java
│           ├── Voter.java               # Includes hasVoted state
│           ├── Candidate.java
│           ├── Constituency.java
│           ├── ElectionControl.java
│           ├── ElectionNews.java
│           ├── ElectionResultsTable.java
│           ├── ResultTabulationOverall.java
│           ├── WelcomePage.java         # Main GUI (admin + voter)
│           ├── SplashScreen.java
│           ├── VoterVerify.java
│           └── *Symbol.png              # Party logos
└── bin/                                 # Compiled classes (if present)


---

## Getting Started

### Requirements

- **JDK 11+**  
- **NetBeans IDE** (recommended for this Ant/NetBeans layout)

### Run with NetBeans

1. Clone the repository  
2. Open `AlbaniaEMS` in NetBeans: **File → Open Project**  
3. **Run → Run Project (F6)**  
4. From the Welcome page, sign in as **Administrator** or **Voter**

### Notes

- Initial voters, candidates, and constituencies are seeded from `main.java`  
- Party logo PNGs must remain on the classpath / package path expected by the UI  

---

## Design Highlights

| Concern | Approach in this project |
|---------|---------------------------|
| **Domain model** | Clear entities: Person, Administrator, Voter, Candidate, Constituency, Election control/news/results |
| **Election integrity** | Start gate + single-vote enforcement via voter state |
| **UI structure** | Splash → Welcome; separate admin and voter workflows |
| **Results** | Tabulation components for counts and overall view |
| **Documentation** | Class diagram, proposal, report, and presentation under `AlbaniaEMS/` |

Class diagram: `AlbaniaEMS/src/class diagram/Class Diagram.png`

---

## Skills Demonstrated

| Area | Evidence |
|------|----------|
| **Java OOP** | Inheritance (`Person`), encapsulation of voter/candidate/admin state |
| **Desktop UI** | Multi-panel Swing application with role-based flows |
| **Process modeling** | Election start/end, registration, vote casting, results |
| **Business rules** | One vote per voter; admin-controlled election window |
| **Project delivery** | NetBeans project structure, assets, reports, and presentation |

---

## Documentation

| File | Purpose |
|------|---------|
| `AlbaniaEMS/PROJECT PROPOSAL.pdf` | Project proposal |
| `AlbaniaEMS/ELECTIONS MANAGEMENT SYSTEM (EMS) - Report.pdf` | Written report |
| `AlbaniaEMS/Presentation Elections Management System (EMS).pdf` | Presentation |
| `AlbaniaEMS/src/class diagram/Class Diagram.png` | Class diagram |

---

## Author

**Kristina Spahi**  
Computer Engineer · Data Analyst · Full-Stack Developer  

- Email: 26spahikristi@gmail.com  
- GitHub: [github.com/ProjectsALB](https://github.com/ProjectsALB)

---

## License

Developed for educational and portfolio use.  
Not intended for production election systems without security, audit, and legal hardening.
