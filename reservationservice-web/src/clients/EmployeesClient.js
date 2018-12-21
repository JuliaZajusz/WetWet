import { client } from './RestClient';

export const getEmployeesList = async () => {
  const response = await client.get(`/api/employee/all`);
  return response.data
}

export const getEmployee = async (id) => {
  const response = await client.get(`/api/employee/${id}`);
  return response.data
};

