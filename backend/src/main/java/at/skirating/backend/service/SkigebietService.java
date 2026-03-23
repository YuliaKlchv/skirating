package at.skirating.backend.service;

import at.skirating.backend.model.Bewertung;
import at.skirating.backend.model.Skigebiet;
import at.skirating.backend.repository.BewertungRepository;
import at.skirating.backend.repository.SkigebietRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class SkigebietService {

    private final SkigebietRepository skigebietRepository;
    private final BewertungRepository bewertungRepository;

    public SkigebietService(SkigebietRepository skigebietRepository,
                            BewertungRepository bewertungRepository) {
        this.skigebietRepository = skigebietRepository;
        this.bewertungRepository = bewertungRepository;
    }

    public List<Skigebiet> alleSkigebiete() {
        return skigebietRepository.findAll();
    }

    public Skigebiet speichern(Skigebiet skigebiet) {
        return skigebietRepository.save(skigebiet);
    }

    public Skigebiet findById(Long id) {
        return skigebietRepository.findById(id).orElseThrow();
    }

    public void loeschen(Long id) {
        skigebietRepository.deleteById(id);
    }

    public Bewertung bewertungHinzufuegen(Long skigebietId, Bewertung bewertung) {
        Skigebiet ski = findById(skigebietId);
        bewertung.setSkigebiet(ski);
        return bewertungRepository.save(bewertung);
    }

    public double durchschnitt(Long skigebietId) {
        List<Bewertung> bewertungen = bewertungRepository.findBySkigebietId(skigebietId);
        return bewertungen.stream()
                .mapToInt(Bewertung::getPunkte)
                .average()
                .orElse(0.0);
    }

    public void csvImportieren(MultipartFile datei) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(datei.getInputStream())
        );
        String zeile;
        boolean ersteZeile = true;
        while ((zeile = reader.readLine()) != null) {
            if (ersteZeile) { ersteZeile = false; continue; } // Header überspringen
            String[] teile = zeile.split(",");
            Bewertung b = new Bewertung();
            b.setPunkte(Integer.parseInt(teile[1]));
            b.setKommentar(teile[2]);
            Skigebiet ski = skigebietRepository.findById(Long.parseLong(teile[0])).orElseThrow();
            b.setSkigebiet(ski);
            bewertungRepository.save(b);
        }
    }
}