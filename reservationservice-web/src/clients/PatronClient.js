import { client } from './RestClient'

export const getPatrons = async () => {
  const response = await client.get(`/api/patron/allWithDetails`);
  return response.data
};

export const getPatron = async (id) => {
  const response = await client.get(`/api/patron/${id}`);
  return response.data
};

export const addPatron = async (patron) => {
  const response = await client.post(`/api/patron`, patron);
  return response.data
};

export const updatePatron = async (patron) => {
  const response = await client.post(`/api/patron`, patron);
  return response.data
};
