import axios from 'axios'

export const client = axios.create({
  // responseType: 'json'
})

export const getHello = async () => {
  const response = await client.get(`/api`);
  return response.data
};
