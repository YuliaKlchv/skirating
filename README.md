# 🎿 SkiRating

Skigebiet Bewertungsapp — Full Stack Projekt

## Technologien
- Backend: Java, Spring Boot, PostgreSQL
- Frontend: React, TypeScript, Vite

## Starten
1. PostgreSQL starten
2. Backend: IntelliJ → BackendApplication.java ▶
3. Frontend: npm run dev

## API Endpunkte
- GET /skigebiete → alle anzeigen
- POST /skigebiete → neu erstellen
- POST /skigebiete/{id}/bewertung → Bewertung hinzufügen
- GET /skigebiete/{id}/durchschnitt → Durchschnitt berechnen
- POST /skigebiete/csv-import → CSV importieren
