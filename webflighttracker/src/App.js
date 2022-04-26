import { MapContainer, TileLayer} from 'react-leaflet'

import AircraftMarker from './components/AircraftMarker';

function App() {

  return (
    <MapContainer center={[51.505, -0.09]} zoom={5} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <AircraftMarker />
    </MapContainer>
  );
}

export default App;
