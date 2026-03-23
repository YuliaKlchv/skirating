package at.skirating.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="bewertung")

public class Bewertung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int punkte;

    private String kommentar;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "skigebiet_id")

    private Skigebiet skigebiet;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }

    public int getPunkte() { return punkte; }
    public void setPunkte(int punkte) { this.punkte = punkte; }

    public String getKommentar() { return kommentar; }
    public void setKommentar(String kommentar) { this.kommentar = kommentar; }

    public Skigebiet getSkigebiet() { return skigebiet; }
    public void setSkigebiet(Skigebiet skigebiet) { this.skigebiet = skigebiet; }

}

