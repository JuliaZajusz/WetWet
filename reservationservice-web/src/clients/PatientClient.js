import { client } from './RestClient'

export const getPatients = async () => {
    const response = await client.get(`/api/patient/all`);
    return response.data
};

export const getPatronPets = async (id) => {
  const response = await client.get(`/api/patient/all/${id}`);
  return response.data
};


export const getPatient = async (id) => {
  const response = await client.get(`/api/patient/${id}`);
  return response.data
};

export const getPatientAppointments = async (id) => {
  const response = await client.get(`/api/appointment/all/${id}`);
  return response.data
};
