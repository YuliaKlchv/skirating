import { useState, useEffect } from 'react'

// TypeScript Interface — Skigebiet nasıl görünür?
interface Skigebiet {
  id: number
  name: string
  land: string
  ort: string
  bewertungen: Bewertung[]
}

interface Bewertung {
  id: number
  punkte: number
  kommentar: string
}

function App() {
  // State — React'ın hafızası
  const [skigebiete, setSkigebiete] = useState<Skigebiet[]>([])
  const [name, setName] = useState('')
  const [land, setLand] = useState('')
  const [ort, setOrt] = useState('')

  // Backend'den veri çek
  useEffect(() => {
    fetch('http://localhost:8080/skigebiete')
      .then(res => res.json())
      .then(data => setSkigebiete(data))
  }, [])

  // Yeni kayak merkezi ekle
  const hinzufuegen = () => {
    fetch('http://localhost:8080/skigebiete', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, land, ort })
    })
      .then(res => res.json())
      .then(neu => setSkigebiete([...skigebiete, neu]))
  }

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h1>🎿 SkiRating</h1>

      {/* Form — Neues Skigebiet */}
      <div style={{ marginBottom: '2rem' }}>
        <h2>Neues Skigebiet hinzufügen</h2>
        <input placeholder="Name" value={name}
          onChange={e => setName(e.target.value)} />
        <input placeholder="Land" value={land}
          onChange={e => setLand(e.target.value)} />
        <input placeholder="Ort" value={ort}
          onChange={e => setOrt(e.target.value)} />
        <button onClick={hinzufuegen}>Hinzufügen</button>
      </div>

      {/* Liste */}
      <h2>Alle Skigebiete</h2>
      {skigebiete.map(ski => (
        <div key={ski.id} style={{
          border: '1px solid #ccc',
          padding: '1rem',
          marginBottom: '1rem',
          borderRadius: '8px'
        }}>
          <h3>{ski.name}</h3>
          <p>{ski.land} — {ski.ort}</p>
        </div>
      ))}
    </div>
  )
}

export default App