**English version below.**

# Einkaufslisten-App

Dies ist eine **Android-App**, mit der Nutzer mehrere Einkaufslisten erstellen und verwalten können.

## Anforderungen
- **Minimale Android-Version**: Android 7.0 (API 24)
- **Empfohlene Android-Version**: Android 13 (API 33)

## Funktionsumfang
Diese App ermöglicht das Erstellen und Verwalten mehrerer Einkaufslisten. Jede Liste kann individuell benannt und mit Produkten befüllt werden. Produkte sowie ganze Listen lassen sich jederzeit wieder löschen.

## Verwendete Technologien
- **Jetpack Compose** – UI-Entwicklung mit deklarativer Programmierung
- **MVVM-Architektur** – Trennung von UI und Datenlogik
- **Room** – Persistente Speicherung von Daten in einer SQLite-Datenbank
- **Navigation Component** – Navigation zwischen verschiedenen Ansichten mit `NavController`
- **Scaffold** – Strukturierung der Benutzeroberfläche

Das Projekt wurde mit **Android Studio** entwickelt.

## Weiterentwicklungsmöglichkeiten
- **UI-Verbesserung**: Optik und Bedienbarkeit intuitiver gestalten
- **Zentrale Datenbank**: Synchronisation mehrerer Nutzer ermöglichen
- **Daten-Repository**: Flexible Speicherung durch mehrere Speicheroptionen
- **Internationalisierung**: Einheitliche Texte und Übersetzungen durch Ressourcenverwaltung
- **Datenbank-Migration**: Aktuell wird die Datenbank bei Änderungen zurückgesetzt, eine echte Migration ist noch nicht implementiert
- **Fehlerbehandlung**: Derzeit gibt es keine umfassende Fehlerbehandlung
- **Tests**: Es wurden noch keine automatisierten Tests implementiert

## 📜 Lizenz
Dieses Projekt steht unter der **Unlicense** und ist gemeinfrei. Siehe die Datei [LICENSE](LICENSE) für Details.


# Shopping List App

This is an **Android app** that allows users to create and manage multiple shopping lists.

## Requirements
- **Minimum Android Version**: Android 7.0 (API 24)
- **Recommended Android Version**: Android 13 (API 33)

## Features
This app allows users to create and manage multiple shopping lists. Each list can be individually named and filled with products. Products, as well as entire lists, can be deleted at any time.

## Technologies Used
- **Jetpack Compose** – UI development with declarative programming
- **MVVM Architecture** – Separation of UI and data logic
- **Room** – Persistent data storage using an SQLite database
- **Navigation Component** – Navigation between views using `NavController`
- **Scaffold** – Structuring the user interface

The project was developed using **Android Studio**.

## Future Improvements
- **UI Enhancement**: Improve visuals and user experience
- **Central Database**: Enable synchronization for multiple users
- **Data Repository**: Support multiple storage options
- **Database Migration**: Currently, the database is reset on every change, as the app is still in development. A proper migration system has not yet been implemented.  
- **Error Handling**: No comprehensive error handling is currently implemented
- **Testing**: No automated tests have been implemented yet 

## 📜 License
This project is released under the **Unlicense** and is in the public domain. See the [LICENSE](LICENSE) file for details.  
