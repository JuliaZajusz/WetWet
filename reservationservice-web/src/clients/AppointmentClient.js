import {client} from './RestClient'

export const getAppointments = async () => {
  const response = await client.get(`/api/appointment/all`);
  return response.data
};
export const saveAppointments = async (appointment) => {
    const response = await client.post(`/api/appointment`, appointment);
    return response.data
};

