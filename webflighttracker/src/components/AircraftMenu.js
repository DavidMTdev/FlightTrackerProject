import { useState, useEffect } from 'react'
import { lookUpRaw } from 'geojson-places'

const AircraftMenu = props => {
  const [locations, setLocations] = useState({})

  useEffect(() => {
    const geoLocation = props.aircrafts.map(item =>
      getLocation(item.flight.latitude, item.flight.longitude)
    )

    const countLocation = getCountLocation(geoLocation)

    setLocations(countLocation)
  }, [props.aircrafts])

  const getLocation = (lat, lng) => {
    return lookUpRaw(lat, lng)?.features[0].properties.admin
  }

  const getCountLocation = loc => {
    let countLocation = new Object()

    loc.map(el => (countLocation[el] = 0))

    for (const key in countLocation) {
      const q = loc.filter(i => key == `${i}`)
      countLocation[key] = q.length
    }

    return countLocation
  }

  const showLocations = object => {
    let c = []
    for (const key in object) {
      c.push(
        <div className='loc'>
          <div>{key}</div>
          <div>{object[key]}</div>
        </div>
      )
    }

    return c
  }

  return (
    <div className='locations'>
      {locations ? showLocations(locations) : null}
    </div>
  )
}

export default AircraftMenu
