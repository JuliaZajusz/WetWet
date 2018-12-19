import { client } from './RestClient'

export const getAppointments = async () => {
  const response = await client.get(`/api/appointment/all`);
  return response.data
};
export const saveAppointments = async (appointment) => {
    const response = await client.post(`/api/appointment`, appointment);
    return response.data
};

export const deleteAppointment = async (appointment) => {
  const response = await client.delete(`/api/appointment/${appointment}`);
  return response.data
};

