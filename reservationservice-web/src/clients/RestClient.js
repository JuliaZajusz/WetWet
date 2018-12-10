import axios from 'axios'

export const client = axios.create({
  // responseType: 'json'
  headers: {
    'Authorization': 'Bearer ' + localStorage.getItem('TOKEN'),
  },
})

