import { client } from './RestClient';

export const getEmployeesList = async () => {
  const response = await client.get(`/api/employee/all`);
  return response.data
}
