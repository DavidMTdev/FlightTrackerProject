import { useState, useEffect } from 'react'
import { MapContainer, TileLayer } from 'react-leaflet'
import Select from 'react-select'

import axios from 'axios'

import AircraftMarker from './components/AircraftMarker'

const { lookUpRaw } = require('geojson-places')

function App() {
  const [times, setTimes] = useState([])
  const [aircrafts, setAircrafts] = useState([])

  useEffect(() => {
    getTimes()
    getAircrafts()
    // setTimeout(() => {
    //   getTimes()
    //   getAircrafts()
    // }, 60000)
  }, [aircrafts])

  const getTimes = () => {
    axios.get('http://localhost:8080/history/times').then(response => {
      const a = response.data.map(time => ({ value: time, label: time }))
      setTimes(a)
    })
  }

  const getAircrafts = () => {
    axios.get('http://localhost:8080/aircrafts').then(response => {
      const a = response.data.map(aircraft => {
        if (
          aircraft.flight?.history?.latitude &&
          aircraft.flight?.history?.longitude
        ) {
          const geoCode = lookUpRaw(
            aircraft.flight.history.latitude,
            aircraft.flight.history.longitude
          ).features[0].properties.admin

          return {
            id: aircraft.id,
            number: aircraft.number,
            flight: {
              id: aircraft.flight.id,
              latitude: aircraft.flight.history.latitude,
              longitude: aircraft.flight.history.longitude,
              position: geoCode
            }
          }
        }
      })

      setAircrafts(a.filter(i => i !== undefined))
    })
  }

  return (
    <div className='container-app'>
      <div className='container-menu'>
        <Select
          className='basic-single'
          classNamePrefix='select'
          name='time'
          options={times}
        />
        <div></div>
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
