package at.skirating.backend.model;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
@Table(name="skigebiet") /***Tabellen name in DB**/

public class Skigebiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /**id wird automatisch hochgezählt**/
    private Long id;

    private String name;
    private String land;
    private String ort;

    @JsonManagedReference
    @OneToMany(mappedBy = "skigebiet" , cascade = CascadeType.ALL) /***SkiGebiet hat viele bewertungen***/

    private List<Bewertung> bewertungen = new ArrayList<>();

    // Getter and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }

    public List<Bewertung> getBewertungen() { return bewertungen; }
    public void setBewertungen(List<Bewertung> bewertungen) { this.bewertungen = bewertungen; }
}
