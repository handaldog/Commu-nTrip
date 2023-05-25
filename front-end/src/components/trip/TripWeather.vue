<template>
  <div class="border weather">
    <div class="item">
      <b-img :src="`https://openweathermap.org/img/wn/${weatherData.weathericon}@2x.png`" />
      <div>현재 습도는 {{ weatherData.humidity }}%이고, 온도는 {{ weatherData.temp }}도입니다.</div>
      <div>
        현재 풍향은 {{ weatherData.winddeg }}도 이고, 풍속은 {{ weatherData.windspeed }}m/s입니다.
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'TripWeather',
  components: {},
  data() {
    return {
      weatherData: {
        humidity: '',
        temp: '',
        weathericon: '',
        desc: '',
        winddeg: '',
        windspeed: '',
      },
    };
  },
  created() {
    let lat = localStorage.getItem('lat');
    let lon = localStorage.getItem('lon');
    axios
      .get(
        `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${process.env.VUE_APP_OPEN_WEATHER_KEY}`
      )
      .then(({ data }) => {
        console.log(data);
        this.weatherData.humidity = data.main.humidity;
        this.weatherData.temp = Math.round(data.main.temp - 273);
        this.weatherData.weathericon = data.weather[0].icon;
        this.weatherData.desc = data.weather[0].description;
        this.weatherData.winddeg = data.wind.deg;
        this.weatherData.windspeed = data.wind.speed;
      });
  },
  methods: {},
};
</script>

<style scoped>
.weather {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.item {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-bottom: 10px;
}
</style>
