# 🗳️ Election Management System — Albania (VOTO-AL 2025)

A Java Swing desktop application for managing Albanian national elections. Built with NetBeans, it covers the full election lifecycle — from voter registration and candidate management to vote casting and result tabulation.

---

## 🖥️ Features

- **Voter Registration** — Auto-generated voter IDs in format `AL-001`, `AL-002`, ...
- **Single-Vote Enforcement** — Once a voter casts their vote, they cannot log in again
- **5 Albanian Political Parties** with custom logos:
  - 🔴 PS — Partia Socialiste e Shqipërisë
  - 🔵 PD — Partia Demokratike e Shqipërisë
  - 🟤 LSI — Lëvizja Socialiste për Integrim
  - 🟠 PDIU — Partia për Drejtësi, Integrim dhe Unitet
  - 🟣 PRS — Partia Republikane e Shqipërisë
- **Automatic Election News** — 8 news items are auto-published when the election starts
- **Admin Panel** — Manage voters, candidates, constituencies, news, and election control
- **Result Tabulation** — Live vote counts and overall turnout report
- **Splash Screen** with progress bar on startup

---

## 🏛️ Electoral Zones

| Qarku | Bashkia |
|-------|---------|
| Q-TI (Tiranë) | B-TI-1, B-TI-2 |
| Q-DR (Durrës) | B-DR-1 |
| Q-SH (Shkodër) | B-SH-1 |

---

## 👤 Default Admin Credentials

```
Username: admin
Password: admin
```

---

## 🚀 Getting Started

### Requirements
- Java JDK 11 or higher
- NetBeans IDE (recommended)

### Run
1. Clone the repository
2. Open the project in NetBeans: `File → Open Project`
3. Build and run: `Run → Run Project (F6)`
4. Login as **Administrator** or **Voter** from the Welcome Page

---

## 📁 Project Structure

```
src/
└── ElectionManagementSystem/
    ├── main.java                  # Entry point, initial data
    ├── Person.java                # Base class
    ├── Administrator.java         # Admin credentials & login
    ├── Voter.java                 # Voter with hasVoted flag
    ├── Candidate.java             # Candidate with party & votes
    ├── Constituency.java          # Electoral zone (Qark/Bashki)
    ├── ElectionControl.java       # Start/end election logic
    ├── ElectionNews.java          # News model
    ├── ElectionResultsTable.java  # Results display
    ├── ResultTabulationOverall.java
    ├── WelcomePage.java           # Main GUI (admin + voter panels)
    ├── SplashScreen.java          # Startup splash screen
    ├── VoterVerify.java           # Voter verification panel
    └── [Party logo PNGs]          # PSSymbol, PDSymbol, LSISymbol...
```

---

## 🔐 How Voting Works

1. Admin starts the election from the **Election Control** panel
2. Voter logs in with their ID (e.g. `AL-005`)
3. Voter selects one candidate per assembly (Qark + Bashki)
4. After clicking **VOTE**, a confirmation appears and the voter is redirected to the home page
5. If the same voter tries to log in again → access is blocked

---

## 🛠️ Built With

- **Java** (Swing / JFrame)
- **NetBeans** IDE
- **Pillow** (Python) — used to generate party logo images

---

## 📸 Screenshots

> *(Add screenshots of the Welcome Page, Voter Panel, and Results here)*

---

## 📄 License

This project was developed for educational purposes.
