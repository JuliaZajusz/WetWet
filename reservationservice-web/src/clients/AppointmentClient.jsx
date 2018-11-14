import { client } from './RestClient'

export const getAppointments = async () => {
  const response = await client.get(`/api/appointment/all`);
  return response.data
};
