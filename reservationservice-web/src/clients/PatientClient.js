import { client } from './RestClient'

export const getPatients = async () => {
    const response = await client.get(`/api/patient/all`);
    return response.data
};