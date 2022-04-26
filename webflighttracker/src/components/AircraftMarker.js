/* eslint-disable array-callback-return */
/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect, } from 'react'; 
import { Marker, Popup } from 'react-leaflet'
import { Icon } from "leaflet";
import axios from 'axios';

import Plane from '../assets/icon/plane_64x64.png';

const AircraftMarker = () => {
    const [aircrafts, setAircrafts] = useState([]);

    useEffect(() => {
        getAircrafts(); 
    }, []);


    useEffect(() => {
        setTimeout(() => { 
            getAircrafts(); 
        }, 60000);
    }, [aircrafts]);

    const planeIcon = new Icon({
        iconUrl: Plane,
        iconSize: [32, 32]
    });

    const getAircrafts = () => {
        axios.get('http://localhost:8080/aircrafts')
        .then(response => {

            const a = response.data.map(aircraft => { 
                if (aircraft.flight?.history?.latitude && aircraft.flight?.history?.longitude ) {
                    return (
                        {
                            id: aircraft.id, 
                            number: aircraft.number, 
                            flight: {
                                id: aircraft.flight.id,
                                latitude: aircraft.flight.history.latitude,
                                longitude: aircraft.flight.history.longitude
                            },
                            marker: getMarker(aircraft, aircraft.flight.history.latitude, aircraft.flight.history.longitude)
                        }
                    )
                }
            })
            setAircrafts(a.filter(i => i !== undefined));
        })
    }

    const getMarker = (item, lat, lng) => {
        return (
        <Marker key={item?.id} position={[lat, lng]} icon={planeIcon} >
            <Popup>
                Aircraft {item?.number}
            </Popup>
        </Marker>
        )
    }

    return (
        <>
        {aircrafts.map(aircraft => (aircraft?.marker))}    
        </>
    );
};

export default AircraftMarker;