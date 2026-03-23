package at.skirating.backend.controller;

import at.skirating.backend.model.Bewertung;
import at.skirating.backend.model.Skigebiet;
import at.skirating.backend.service.SkigebietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController  /**Ich bin ein Web-Controller, antworte mit JSON**/
@RequestMapping("/skigebiete") /**Alle URLs fangen mit /skigebiete an"**/
@CrossOrigin(origins = "http://localhost:5173")  /** Ich erlaube Anfragen von React (Port 3000) **/
public class SkigebietController {

    private final SkigebietService service;

    public SkigebietController(SkigebietService service) {
        this.service = service;
    }

    @GetMapping /** Daten lesen http get**/
    public List<Skigebiet> alle() {
        return service.alleSkigebiete();
    }

    @PostMapping/**daten erstellen **/
    public Skigebiet erstellen(@RequestBody Skigebiet skigebiet) {
        return service.speichern(skigebiet);
    }

    @GetMapping("/{id}")
    public Skigebiet eins(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void loeschen(@PathVariable Long id) {
        service.loeschen(id);
    }

    @PostMapping("/{id}/bewertung")
    public Bewertung bewertung(@PathVariable Long id, /** nimmt die id aus dem URl **/
                               @RequestBody Bewertung bewertung) { /** Requestbody nimmt dir aten aus dem HTTP body**/
        return service.bewertungHinzufuegen(id, bewertung);
    }

    @GetMapping("/{id}/durchschnitt")
    public double durchschnitt(@PathVariable Long id) {
        return service.durchschnitt(id);
    }

    @PostMapping("/csv-import")
    public ResponseEntity<String> csvImportieren(@RequestParam("datei") MultipartFile datei) {
        try {
            service.csvImportieren(datei );
            return ResponseEntity.ok("Import erfolgreich!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fehler: " + e.getMessage());
        }
    }
}