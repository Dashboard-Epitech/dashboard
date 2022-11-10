import React from 'react';
import {
  Stack,
  HStack,
} from '@chakra-ui/react';
import Weather from '../widget/Weather';

function testWeather() {
  const weather01d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "01d"
  }
  const weather01n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "01n"
  }
  const weather02d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "02d"
  }
  const weather02n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "02n"
  }
  const weather03d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "03d"
  }
  const weather03n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "03n"
  }
  const weather04d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "04d"
  }
  const weather04n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "04n"
  }
  const weather09d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "09d"
  }
  const weather09n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "09n"
  }
  const weather10d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "10d"
  }
  const weather10n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "10n"
  }
  const weather11d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "11d"
  }
  const weather11n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "11n"
  }
  const weather13d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "13d"
  }
  const weather13n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "13n"
  }
  const weather50d = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "50d"
  }
  const weather50n = {
    city: "Paris",
    temp: "13",
    pressur: "1017",
    wind: "10",
    icon: "50n"
  }

  return (
    <Stack>
      <HStack>
        <Weather size={1} weather={weather01d}/>
        <Weather size={1} weather={weather01n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather02d}/>
        <Weather size={1} weather={weather02n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather03d}/>
        <Weather size={1} weather={weather03n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather04d}/>
        <Weather size={1} weather={weather04n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather09d}/>
        <Weather size={1} weather={weather09n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather10d}/>
        <Weather size={1} weather={weather10n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather11d}/>
        <Weather size={1} weather={weather11n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather13d}/>
        <Weather size={1} weather={weather13n}/>
      </HStack>
      <HStack>
        <Weather size={1} weather={weather50d}/>
        <Weather size={1} weather={weather50n}/>
      </HStack>
    </Stack>
  );
}

export default testWeather;
