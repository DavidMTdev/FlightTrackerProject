import { useState, useEffect } from 'react'
import { MapContainer, TileLayer } from 'react-leaflet'
import Select from 'react-select'

import axios from 'axios'

import AircraftMarker from './components/AircraftMarker'
import AircraftMenu from './components/AircraftMenu'

function App() {
  const [times, setTimes] = useState([])
  const [aircrafts, setAircrafts] = useState([])

  useEffect(() => {
    getTimes()
    getAircrafts('http://localhost:8080/aircrafts')
    // setTimeout(() => {
    //   getTimes()
    //   getAircrafts()
    // }, 60000)
  }, [])

  const getTimes = () => {
    axios.get('http://localhost:8080/history/times').then(response => {
      const a = response.data.map(time => ({
        value: Date.parse(time),
        label: time
      }))
      setTimes(a)
    })
  }

  const getAircrafts = url => {
    axios.get(url).then(response => {
      const a = response.data.map(aircraft => {
        if (
          aircraft.flight?.history?.latitude &&
          aircraft.flight?.history?.longitude
        ) {
          return {
            id: aircraft.id,
            number: aircraft.number,
            flight: {
              id: aircraft.flight.id,
              latitude: aircraft.flight.history.latitude,
              longitude: aircraft.flight.history.longitude
            }
          }
        }
      })

      setAircrafts(a.filter(i => i !== undefined))
    })
  }

  const handleChange = event => {
    console.log(event.value)
    getAircrafts(`http://localhost:8080/aircrafts/time/${event.value}`)
  }

  return (
    <div className='container-app'>
      <div className='container-menu'>
        <Select
          className='basic-single'
          classNamePrefix='select'
          name='time'
          options={times}
          onChange={e => handleChange(e)}
        />
        {aircrafts ? <AircraftMenu aircrafts={aircrafts} /> : null}
      </div>
      <div className='container-map'>
        <MapContainer center={[51.505, -0.09]} zoom={5} scrollWheelZoom={false}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
          />
          {aircrafts ? <AircraftMarker aircrafts={aircrafts} /> : null}
        </MapContainer>
      </div>
    </div>
  )
}

export default App
