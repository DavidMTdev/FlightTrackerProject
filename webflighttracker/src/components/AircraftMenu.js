import { useState, useEffect } from 'react'
import { lookUpRaw } from 'geojson-places'

const AircraftMenu = props => {
  const [locations, setLocations] = useState([])

  useEffect(() => {
    const l = props.aircrafts.map(item =>
      getLocation(item.flight.latitude, item.flight.longitude)
    )

    const l2 = l

    const count = l.filter(i => l2.indexOf(i) !== -1);
    console.log(count);
    setLocations(l)
  }, [props.aircrafts])

  const getLocation = (lat, lng) => {
    return lookUpRaw(lat, lng)?.features[0].properties.admin
  }

  return <div>AircraftMenu</div>
}

export default AircraftMenu
